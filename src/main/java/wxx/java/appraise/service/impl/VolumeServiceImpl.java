package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.VolumeDao;
import wxx.java.appraise.dao.VolumeUserDao;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;
import wxx.java.appraise.service.VolumeService;

import java.util.*;

@Service
@Transactional
public class VolumeServiceImpl implements VolumeService {
    private VolumeDao volumeDao;
    private VolumeUserDao volumeUserDao;
    @Autowired
    public void  setVolumeDao(VolumeDao volumeDao){
        this.volumeDao = volumeDao;
    }
    @Autowired
    public void  setVolumeUserDao(VolumeUserDao volumeUserDao){
        this.volumeUserDao = volumeUserDao;
    }
    @Override
    public void addFormPro(Project project) {

    }

    @Override
    public Map queryById(Integer id) {
        return volumeDao.queryById(id);
    }

  @Override
  public List<Map> queryByProjectId(Project project) {
        Map map = new HashMap();
    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();
    if (calendar1.get(Calendar.MONTH) == 0){
      if (project.getMonth() == 0){
        project.setMonth(12);
        calendar.set(Calendar.YEAR,calendar1.get(Calendar.YEAR)-1);
      }
      else if (project.getMonth() == -1){
        project.setMonth(11);
        calendar.set(Calendar.YEAR,calendar1.get(Calendar.YEAR)-1);
      }
    }
    else if (calendar1.get(Calendar.MONTH) == 1){
      if (project.getMonth() == 0){
        calendar.set(Calendar.YEAR,calendar1.get(Calendar.YEAR)-1);
        project.setMonth(12);
      }
    }
//        Integer month = project.getMonth()-1;
//        calendar.set(Calendar.MONTH,month);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        long min = calendar.getTimeInMillis();
//        calendar.set(Calendar.MONTH, ++month);
//        long max = calendar.getTimeInMillis();
        map.put("id", project.getId());
//        map.put("max", max);
//        map.put("min", min);
        if (project.getPickerDate() != null) {
          map.put("maxDate", project.getPickerDate().get(1));
          map.put("minDate", project.getPickerDate().get(0));
        }
        return volumeDao.queryByProjectId(map);

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
}
