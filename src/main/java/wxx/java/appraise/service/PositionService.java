package wxx.java.appraise.service;

import wxx.java.appraise.entity.Position;
import wxx.java.appraise.result.Result;

import java.util.List;
import java.util.Map;

public interface PositionService {

  List<Map> query();

  List<Map> queryByWeight(Integer id);


  Result update(List<Position> position);
}
