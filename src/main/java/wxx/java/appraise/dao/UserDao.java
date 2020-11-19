package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserOut;

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
    //查询有权限打分的人员名单
    List<String> queryGrade();
    //根据工号排序
    List<Map> queryAll();

    Map queryToupd(Integer id);

    //根据专业查询
    List<Map> queryByTid(Integer id);

    //根据专业查询，用于前端transfer的数据
    List<Map> queryByT(Integer id);

    List<Map> queryNotSelf(User user);

    List<Map> queryNotScore(User user);
    //已进行评价的人员名单
    List<UserOut> queryAppraise(User user);
    //没有进行评价的人员名单
    List<UserOut> queryNotAppraise(User user);
    //没有被评价过的人员名单
    List<UserOut> queryNotScored(User user);
    //没有对专业级进行打分的人员名单
    List<UserOut> queryNotTecApp(User user);

    List<Map> queryByTec(Integer id);
    //用于设总选择时模糊查询
    List<Map> queryByName(User user);
    //用于excel导入时的精确查询
    Integer selectByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(User user);

    List<Map> queryPrincipal(Integer id);

}
