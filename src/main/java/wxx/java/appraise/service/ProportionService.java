package wxx.java.appraise.service;

import wxx.java.appraise.entity.Proportion;

import java.util.List;
import java.util.Map;

public interface ProportionService {

    void add(Proportion proportion);

    Proportion queryLastTime(Proportion proportion);

    List<Map> queryLastTwoTimes(Proportion proportion);
}
