package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import wxx.java.appraise.dao.ProportionDao;
import wxx.java.appraise.entity.Proportion;
import wxx.java.appraise.service.ProportionService;
import wxx.java.appraise.tools.Time;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProportionServiceImpl implements ProportionService {
    private ProportionDao proportionDao;
    @Autowired
    public void  setProportionDao(ProportionDao proportionDao){
        this.proportionDao = proportionDao;
    }


    @Override
    public void add(@RequestBody Proportion proportion) {
      if (proportion.getRemarks() == null){
        proportion.setRemarks("");
      }
        proportion.setDate(Time.getLongDate());
        proportionDao.add(proportion);
    }

    @Override
    public Proportion queryLastTime(@RequestBody Proportion proportion) {
        proportion = proportionDao.queryLastTime(proportion);
        if (proportion != null && proportion.getDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(proportion.getDate());
            //当查出的汇报记录时间与当前时间相同时，返回1 否则返回0
            if (calendar.get(Calendar.WEEK_OF_YEAR) == Time.getNowWeek()) {
                proportion.setState(1);
            } else {
                proportion.setState(0);
            }
        }else {
            proportion = new Proportion();
            proportion.setState(0);
        }
        return proportion;
    }

    @Override
    public List<Map> queryLastTwoTimes(Proportion proportion) {
        return proportionDao.queryLastTwoTimes(proportion);
    }

}
