package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ProjectLog;

@Repository
public interface ProjectLogDao {

    void add(ProjectLog projectLog);


}
