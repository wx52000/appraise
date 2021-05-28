package wxx.java.appraise.service;

import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;
import wxx.java.appraise.result.Result;

import java.util.List;
import java.util.Map;

public interface VolumeService {

    void addFormPro(Project project);

    Map queryById(Integer id);

    List<Map<String, String>> queryByProjectId(Project project);

    List<Map<String,String>> queryByDate(Map map);

    void upd(Volume volume);

    void add(Volume volume);

    List<Map> queryVolume(String user,String volume);

    Result queryByNumber(Volume volume);

  List<Map<String, String>> personalVolume(Map<String,String> map);
}
