package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    Map login(@Param("username")String username,@Param("paw")String paw);

    User queryById(Integer id);

    void add(User user);

    void addExcel(List<ExcelData> list);

    void del(Integer id);

    void upd(User user);

    List<Map> query(User user);

    List<Map> queryByTid(Integer id);

    List<Map> queryNotSelf(User user);

    List<Map> queryNotScore(User user);

    List<Map> queryByTec(Integer id);
    //用于设总选择时模糊查询
    List<Map> queryByName(User user);
    //用于excel导入时的精确查询
    Integer selectByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(User user);

    List<Map> queryPrincipal(Integer id);

}
