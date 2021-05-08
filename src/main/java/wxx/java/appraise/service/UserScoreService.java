package wxx.java.appraise.service;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.PartExcel;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;

import java.util.List;
import java.util.Map;

public interface UserScoreService {

    PageInfo<Map> queryByGradeId(User user);

    List<Map> queryByScoreId(User user);

    void appraise(@Param("list") List<UserScore> list);

    List<Map> queryScore(User user);

    List<Map> query(User user);

    List<List<String>> detail(User user,List<String> userName);

    void add(List<UserScore> list);

    void del(UserScore userScore);

    List<PersonalExcel> excel1(User user);

    List<Map> selectByGradeId(User user);

    List<PartExcel>  part(Integer mode , List<Map> toData,Integer month);

    void backups();

    void delete();

}
