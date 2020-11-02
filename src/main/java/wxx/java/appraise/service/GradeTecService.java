package wxx.java.appraise.service;

import org.apache.ibatis.annotations.Param;
import wxx.java.appraise.entity.GradeTec;

import java.util.List;
import java.util.Set;

public interface GradeTecService {

    void add(GradeTec gradeTec);

    void del(Integer id);

    void addExcel(@Param("list") List<Integer> list , @Param("set") Set<Integer> set);

    void resetState();
}
