package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.DepartmentDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.Department;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;
    private UserDao userDao;
    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }
    @Override
    public List<Department> query() {
        return departmentDao.query();
    }

  @Override
  public List<Department> queryNotUser() {
      return departmentDao.queryNotUser();
  }

  @Override
    public void add(Department department) {
        departmentDao.add(department);
    }

  @Override
  public Integer addString(String s) {
    Department department = new Department(s);
    departmentDao.add(department);
    return department.getId();
  }

  @Override
    public void del(Integer id) {
        departmentDao.del(id);
    }

    @Override
    public Integer queryName(String name) {
        return departmentDao.queryName(name);
    }
}
