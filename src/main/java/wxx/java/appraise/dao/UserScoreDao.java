package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.PartExcel;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;

import java.util.List;
import java.util.Map;

@Repository
public interface UserScoreDao {

    List<Map> queryByGradeId(User user);

    Map queryByScoreId(User user);

    List<Map> queryByScoreIdPast(User user);

    void appraise(@Param("list") List<UserScore> list,
                  @Param("month")Integer month,@Param("year")Integer year);

    //查询当前月整体数据
    List<Map> queryScore(User user);
    //查询以前月份整体数据
    List<Map> queryScorePast(User user);

    //查询当前月详细数据
    List<Map> query(User user);
    //查询以前月份详细数据
    List<Map> queryPast(User user);

    void add(List<UserScore> list);

    void del(UserScore userScore);

    //本月数据
    List<PersonalExcel> excel1(User user);
    //以往数据
    List<PersonalExcel> excel2(User user);
    //本月数据
    List<String> detail(@Param("id") Integer id,@Param("nameList")List<String> nameList);
    //以往数据
    List<String> detailPast(@Param("user") User user,@Param("uid")Integer uid,
                            @Param("nameList")List<String> nameList);

    //根据打分人
    List<PartExcel> part0(User user);
    //根据被打分人
    List<PartExcel> part1(User user);

    //以往数据根据打分人
    List<PartExcel> partPast0(User user);
    //以往数据根据被打分人
    List<PartExcel> partPast1(User user);

    void backups();

    void delete();

}
