package wxx.java.appraise.service;

import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Technology;

import java.util.List;
import java.util.Map;

public interface TechnologyService {

    List<Technology> query(Integer id);

    List<Technology> queryNotUser();

    List<Map> evaluate(Integer id);

    Integer queryByName(String name);

    void add(Technology technology);

    Integer addString(String name,Integer d);

    void del(Integer id);

    List<Technology> queryToSelected();

    List<Map> queryById(Project project);
}
