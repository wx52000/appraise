package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Project;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectUserDao {

    void add(Project project);

    void addGeneral(Project project);

    void delGeneral(Project project);

    void addPrincipal(ExcelProject excelProject);

    List<Map> queryPrincipalById(Integer id);

    void del(Project project);
}
