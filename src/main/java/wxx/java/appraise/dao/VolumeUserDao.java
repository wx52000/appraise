package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Volume;

@Repository
public interface VolumeUserDao {

    void addDesigner(Volume volume);

    void addChecker(Volume volume);

    void addExcelDesigner(ExcelProject excelProject);

    void addExcelChecker(ExcelProject excelProject);
    void del(Volume volume);
}
