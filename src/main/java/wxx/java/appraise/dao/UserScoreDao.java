package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;

import java.util.List;
import java.util.Map;

@Repository
public interface UserScoreDao {

    List<Map> queryByGradeId(User user);

    void appraise(@Param("list") List<UserScore> list,@Param("month")Integer month);

    List<Map> queryScore(User user);

    List<Map> query(User user);

    void add(List<UserScore> list);

    void del(UserScore userScore);

    List<PersonalExcel> excel1(User user);

    void backups();

    void delete();

}
