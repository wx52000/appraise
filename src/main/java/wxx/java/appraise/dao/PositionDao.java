package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PositionDao {

  List<Map> query();
}
