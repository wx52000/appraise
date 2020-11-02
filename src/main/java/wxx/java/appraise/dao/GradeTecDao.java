package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.GradeTec;
import wxx.java.appraise.entity.TecScore;

import java.util.List;
import java.util.Set;

@Repository
public interface GradeTecDao {

    void add(GradeTec gradeTec);

    void del(Integer id);

    void addExcel(@Param("list") List<Integer> list , @Param("set") Set<Integer> set);

    void resetState();

    void updState(List<TecScore> id);

    void delAll();
}
