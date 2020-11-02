package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;

import java.util.Map;

@Repository
public interface VolumeDao {

    void addFormPro(Project project);

    void addExcelVolume(ExcelProject excelProject);

    Map queryById(Integer id);

    void upd(Volume volume);

    void add(Volume volume);
}
