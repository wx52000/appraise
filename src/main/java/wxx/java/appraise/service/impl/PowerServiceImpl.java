package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.PowerDao;
import wxx.java.appraise.entity.Power;
import wxx.java.appraise.service.PowerService;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {
    private PowerDao powerDao;
    @Autowired
    public void setPowerDao(PowerDao powerDao){
        this.powerDao = powerDao;
    }
    @Override
    public List<Power> query() {
        return powerDao.query();
    }
}
