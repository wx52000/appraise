package wxx.java.appraise.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;

import java.util.List;
import java.util.Map;

public interface UserScoreService {

    PageInfo<Map> queryByGradeId(User user);

    void appraise(@Param("list") List<UserScore> list);

    List<Map> queryScore(User user);

    List<Map> query(User user);

    List<List<String>> detail();

    void add(List<UserScore> list);

    void del(UserScore userScore);

    List<PersonalExcel> excel1(User user);

    List<Map> selectByGradeId(User user);

    void backups();

    void delete();

}
