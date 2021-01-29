package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;

import java.util.List;
import java.util.Map;

@Repository
public interface VolumeDao {

    void addFormPro(Project project);

    void addExcelVolume(ExcelProject excelProject);

    Map queryById(Integer id);

    List<Map> queryByProjectId(Map map);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(@Param("user")String user,@Param("volume")String volume);
}
