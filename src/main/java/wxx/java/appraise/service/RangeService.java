package wxx.java.appraise.service;

import wxx.java.appraise.entity.Range;

import java.util.Map;

public interface RangeService {


    Range query();

    void update(Range range);

    Map queryDate();

    void updateDate(Range range);
}
