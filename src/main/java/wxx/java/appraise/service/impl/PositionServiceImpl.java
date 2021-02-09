package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.PositionDao;
import wxx.java.appraise.service.PositionService;

import java.util.List;
import java.util.Map;

@Service
public class PositionServiceImpl implements PositionService {
  private PositionDao positionDao;
  @Autowired
  private void setPositionDao(PositionDao positionDao){
    this.positionDao = positionDao;
  }
  @Override
  public List<Map> query() {
    return positionDao.query();
  }
}
