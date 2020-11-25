package wxx.java.appraise.service;

import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.TecPartExcel;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.TechnologyExcel;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

public interface TecScoreService {


    List<Map> queryByGradeId(Integer id);

    void appraise(@Param("list") List<TecScore> list);

    List<Map> queryScore(User user);

    List<Map> query(User user);

    List<List<String>> detail();

    List<TechnologyExcel> excel(User user);

    List<TecPartExcel> part(List<Map> toData);

    void backups();

    void delete();
}
