package wxx.java.appraise.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserOut;
import wxx.java.appraise.result.Result;

import java.util.List;
import java.util.Map;

public interface UserService {

    Result login(@Param("username") String username, @Param("paw")String paw);

    void add(User user);

    void addExcel(List<ExcelData> list);

    void del(Integer id);

    void upd(User user);

    PageInfo<Map> query(User user);

    //查询有权限打分的人员名单
    List<String> queryGrade();
    //根据工号排序
    List<Map > queryAll();

    Map queryToupd(Integer id);

    List<Map> queryByTid(Integer id);

    PageInfo<Map> queryNotSelf(User user);
    //用于被打分的数据查询
    Result queryToScore(User user);

    Result queryScoreList(User user);

    PageInfo<Map> queryNotScore(User user);

    Result queryAppriseAll();

    List<UserOut> queryAppraise(User user);
    //没有进行评价的人员名单
    List<UserOut> queryNotAppraise(User user);
    //没有被评价过的人员名单
    List<UserOut> queryNotScored(User user);
    //没有对专业级进行打分的人员名单
    List<UserOut> queryNotTecApp(User user);

    List<Map> queryByTec(Integer id);

    List<Map> queryByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(User user);

    List<Map> queryPrincipal(Integer id);

    List<Map> userAll(Integer mode);

    List<Map> userAllAndState(Integer id);

    List<Map> userAllAndGroup(Integer id,Integer mode);
}
