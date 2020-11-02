package wxx.java.appraise.service;

import wxx.java.appraise.entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> query();

    void add(Department department);

    void del(Integer id);

    Integer queryName(String name);
}
