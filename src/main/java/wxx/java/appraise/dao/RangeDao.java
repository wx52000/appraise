package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Range;

import java.util.Map;

@Repository
public interface RangeDao {

    Range query();

    void update(Range range);

    Map queryDate();

    void updateDate(Range range);
}
