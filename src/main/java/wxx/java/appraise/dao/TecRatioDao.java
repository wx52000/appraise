package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.TecRatio;

import java.util.Map;

@Repository
public interface TecRatioDao {

  Map queryByTec(Integer id);

  void setRatioByTec(TecRatio tec);
}
