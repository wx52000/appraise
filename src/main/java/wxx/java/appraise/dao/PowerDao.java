package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Power;

import java.util.List;

@Repository
public interface PowerDao {

    List<Power> query();
}
