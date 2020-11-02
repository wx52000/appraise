package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Proportion;

import java.util.List;
import java.util.Map;

@Repository
public interface ProportionDao {

    void add(Proportion proportion);

    Proportion queryLastTime(Proportion proportion);

    List<Map> queryLastTwoTimes(Proportion proportion);

    List<Map> queryLastFiveTimes(Proportion proportion);
}
