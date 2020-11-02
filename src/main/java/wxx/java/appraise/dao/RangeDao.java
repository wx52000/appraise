package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Range;

@Repository
public interface RangeDao {

    Range query();

    void update(Range range);
}
