package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectDao {

    void add(Project project);

    void addNumber(Project project);

    void addExcel(ExcelProject excelProject);

    void upd(Project project);

    void updState(Integer id);

    void spider(Project project);

    Map queryById(Integer id);
//管理员查询
    List<Map> queryByAdmin(User user);
    List<Map> queryProByAdmin(User user);
    List<Map> queryVolBYAdmin(User user);
//作为设总查询
    List<Map> queryByGeneral(User user);
//作为主设人查询
    List<Map> queryByPrincipal(User user);
    List<Map> queryProByPrincipal(User user);
//作为设计人查询
    List<Map> queryByDesigner(User user);
//作为互校人查询
    List<Map> queryByChecker(User user);
//作为组长查询
    List<Map> queryByHeadman(User user);

    List<Map> queryExcel(User user);
}
