package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.RangeDao;
import wxx.java.appraise.entity.Range;
import wxx.java.appraise.service.RangeService;

@Service
public class RangeServiceImpl implements RangeService {
    private RangeDao rangeDao;
    @Autowired
    public  void setRangeDao(RangeDao rangeDao){
        this.rangeDao = rangeDao;
    }
    @Override
    public Range query() {
        return rangeDao.query();
    }

    @Override
    public void update(Range range) {
        rangeDao.update(range);
    }
}