package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Position;

import java.util.List;
import java.util.Map;

@Repository
public interface PositionDao {
  List<Map> query();

  List<Map> queryByWeight(Integer id);

  void  update(List<Position> list);

}
