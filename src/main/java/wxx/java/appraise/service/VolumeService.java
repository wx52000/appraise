package wxx.java.appraise.service;

import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;

import java.util.Map;

public interface VolumeService {

    void addFormPro(Project project);

    Map queryById(Integer id);

    void upd(Volume volume);

    void add(Volume volume);
}
