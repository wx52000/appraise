package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.TecPartExcel;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.TechnologyExcel;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface TecScoreDao {

    List<Map> queryByGradeId(Integer id);

    Map queryByScoreId(User user);

    List<Map> queryByScoreIdPast(User user);

    void appraise(@Param("list") List<TecScore> list,
                  @Param("month") Integer month , @Param("year")Integer year);

    List<Map> queryScore(User user);

    List<Map> queryScorePast(User user);

    List<Map> query(User user);

    List<Map> queryPast(User user);

    List<String> detail(@Param("id") Integer id,@Param("userList") List<String> userName);

    List<String> detailPast(@Param("tid") Integer tid,@Param("user")User user,
                            @Param("userList") List<String> userName);

    //本月数据
    List<TechnologyExcel> excel(User user);
    //上月数据
    List<TechnologyExcel> excel1(User user);

//    本月根据打分人
    List<TecPartExcel> part(User user);
//以往根据打分人
    List<TecPartExcel> partPast(User user);

    void backups();

    void delete();
}
