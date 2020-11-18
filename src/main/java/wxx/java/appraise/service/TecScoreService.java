package wxx.java.appraise.service;

import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.TechnologyExcel;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

public interface TecScoreService {


    List<Map> queryByGradeId(Integer id);

    void appraise(@Param("list") List<TecScore> list);

    List<Map> queryScore(Integer id);

    List<Map> query(User user);

    List<List<String>> detail();

    List<TechnologyExcel> excel(User user);

    void backups();

    void delete();
}
