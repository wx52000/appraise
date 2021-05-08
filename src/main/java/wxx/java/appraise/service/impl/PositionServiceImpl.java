package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.PositionDao;
import wxx.java.appraise.entity.Position;
import wxx.java.appraise.result.Result;
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

  @Override
  public List<Map> queryByWeight(Integer id) {
    return positionDao.queryByWeight(id);
  }

  @Override
  public Result update(List<Position> position) {
    try {
      positionDao.update(position);
      return  Result.ok();
    }catch (Exception e){
      e.printStackTrace();
      return  Result.build(1000,"修改失败");
    }
  }
}
