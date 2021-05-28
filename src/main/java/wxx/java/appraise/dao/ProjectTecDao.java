package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Project;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectTecDao {

    void add(Project project);

    void addExcel(ExcelProject excelProject);

    List<Map> query(Integer id);

    List<String> queryById(Integer id);

    void del(Project project);
}
