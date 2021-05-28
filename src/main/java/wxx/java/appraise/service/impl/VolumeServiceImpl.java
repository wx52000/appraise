package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.dao.VolumeDao;
import wxx.java.appraise.dao.VolumeUserDao;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.Volume;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.VolumeService;

import java.util.*;

@Service
@Transactional
public class VolumeServiceImpl implements VolumeService {
    private VolumeDao volumeDao;
    private VolumeUserDao volumeUserDao;
    private UserDao userDao;
    @Autowired
    public void  setVolumeDao(VolumeDao volumeDao){
        this.volumeDao = volumeDao;
    }
    @Autowired
    public void  setVolumeUserDao(VolumeUserDao volumeUserDao){
        this.volumeUserDao = volumeUserDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }
    @Override
    public void addFormPro(Project project) {

    }

    @Override
    public Map queryById(Integer id) {
        return volumeDao.queryById(id);
    }

  @Override
  public List<Map<String, String>> queryByProjectId(Project project) {
        Map map = new HashMap();
        map.put("id", project.getId());

        if (project.getPickerDate() != null) {
          map.put("maxDate", project.getPickerDate().get(1));
          map.put("minDate", project.getPickerDate().get(0));
        }
        return volumeDao.queryByProjectId(map);

  }

  @Override
  public List<Map<String, String>> queryByDate(Map map) {
    return volumeDao.queryByDate(map);
  }

  @Override
    public void upd(Volume volume) {
        volumeDao.upd(volume);
        if(volume.getDesigner() != null ){
            volume.setPower(4);
            volumeUserDao.del(volume);
            volumeUserDao.addDesigner(volume);
        }
        if(volume.getChecker() != null ){
            volume.setPower(5);
            volumeUserDao.del(volume);
            volumeUserDao.addChecker(volume);
        }
    }

    @Override
    public void add(Volume volume) {
        Date date = new Date();
        volume.setCreatorDate(date.getTime());
        volumeDao.add(volume);
        volumeUserDao.addDesigner(volume);
        volumeUserDao.addChecker(volume);
    }

  @Override
  public List<Map> queryVolume(String user, String volume) {
    return volumeDao.queryVolume(user,volume);
  }

  @Override
  public Result queryByNumber(Volume volume) {
      User user = userDao.queryById(volume.getId());
      volume.setName(user.getName());
    return Result.ok(volumeDao.queryByNumber(volume));
  }

  @Override
  public List<Map<String, String>> personalVolume(Map<String, String> map) {
    User user = userDao.queryById(Integer.valueOf(map.get("id")));
    map.put("name",user.getName());
    return volumeDao.personalVolume(map);
  }
}
