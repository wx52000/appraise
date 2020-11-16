package wxx.java.appraise.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;
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

    Map queryToupd(Integer id);

    List<Map> queryByTid(Integer id);

      PageInfo<Map> queryNotSelf(User user);

    PageInfo<Map> queryNotScore(User user);

    //没有进行评价的人员名单
    List<Map> queryNotAppraise();
    //没有被评价过的人员名单
    List<Map> queryNotScored();
    //没有对专业级进行打分的人员名单
    List<Map> queryNotTecApp();

    List<Map> queryByTec(Integer id);

    List<Map> queryByName(User user);

    List<Integer> queryByUsername(List<ExcelData> list);

    void paw(User user);

    List<Map> queryPrincipal(Integer id);
}
