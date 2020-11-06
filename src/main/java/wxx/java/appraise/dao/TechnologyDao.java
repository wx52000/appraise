package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Technology;

import java.util.List;
import java.util.Map;

@Repository
public interface TechnologyDao {

    List<Technology> query(Integer id);

    List<Technology> queryNotUser();

    //评价页面
    List<Map> evaluate(Integer id);

    Integer queryByName(String name);

    void add(Technology technology);

    void del(Integer id);

    List<Technology> queryToSelected();

    List<Technology> queryByPro(Integer id);

    List<Map> getUser(Integer id);

    List<Map> queryById(Project project);
}
