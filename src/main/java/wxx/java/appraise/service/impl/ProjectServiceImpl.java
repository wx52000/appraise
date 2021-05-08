package wxx.java.appraise.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.*;
import wxx.java.appraise.entity.*;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ProjectService;
import wxx.java.appraise.tools.StringUtils;
import wxx.java.appraise.tools.Time;

import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;
    private UserDao userDao;
    private UserPositionDao userPositionDao;
    private ProjectUserDao projectUserDao;
    private ProjectTecDao projectTecDao;
    private TechnologyDao technologyDao;
    private VolumeDao volumeDao;
    private VolumeUserDao volumeUserDao;
    private DepartmentDao departmentDao;
    @Autowired
    public void setProjectDao(ProjectDao projectDao){
        this.projectDao = projectDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setUserPositionDao(UserPositionDao userPositionDao){
        this.userPositionDao = userPositionDao;
    }
    @Autowired
    public void setProjectUserDao(ProjectUserDao projectUserDao){
        this.projectUserDao = projectUserDao;
    }
    @Autowired
    public void  setProjectTecDao(ProjectTecDao projectTecDao){
        this.projectTecDao = projectTecDao;
    }
    @Autowired
    public void setTechnologyDao(TechnologyDao technologyDao){
        this.technologyDao = technologyDao;
    }
    @Autowired
    public void setVolumeDao(VolumeDao volumeDao){
        this.volumeDao = volumeDao;
    }
    @Autowired
    public void setVolumeUserDao(VolumeUserDao volumeUserDao){
        this.volumeUserDao = volumeUserDao;
    }
    @Autowired
    public void  setDepartmentDao(DepartmentDao departmentDao){
        this.departmentDao = departmentDao;
    }
    @Override
    public void add(Project project) {
        projectDao.add(project);
        projectUserDao.addGeneral(project);
        projectTecDao.add(project);
        projectUserDao.add(project);
        for (Volume volume : project.getVolumes()) {
            volume.setPid(project.getId());
            volumeDao.add(volume);
            volumeUserDao.addDesigner(volume);
            volumeUserDao.addChecker(volume);
        }
    }

    @Override
    public void addNumber(Project project) {
        projectDao.addNumber(project);
    }

  @Override
    public void upd(Project project) {
        projectDao.upd(project);
        project.setPowerId(2);
        if (project.getGeneral() != null){
            projectUserDao.delGeneral(project);
            projectUserDao.addGeneral(project);
        }
        project.getProjectTec().remove(null);
        if (!project.getProjectTec().isEmpty()) {
            projectTecDao.del(project);
            projectTecDao.add(project);
        }
        project.getProjectUsers().remove(null);
        if (!project.getProjectUsers().isEmpty()) {
            projectUserDao.del(project);
            projectUserDao.add(project);
        }
    }

    @Override
    public void updState(Integer id) {
        projectDao.updState(id);
    }

  @Override
  public void spider(Project project) {
    projectDao.spider(project);
  }

  @Override
    public List<Map> addExcel(ExcelProject excelProject) throws ParseException {
    projectDao.addExcel(excelProject);
    List<Map> newUser = new ArrayList<>();
    if (excelProject.getTec() != null) {
      excelProject.setTid(technologyDao.queryByName(excelProject.getTec()));
      projectTecDao.addExcel(excelProject);
      if (excelProject.getTid() == null) {
        Technology technology = new Technology();
        technology.setName(excelProject.getTec());
        technology.setDid(14);
        technologyDao.add(technology);
        excelProject.setTid(technology.getId());
      }
    }
//        if (excelProject.getPrincipal() != null){
//            User user = new User();
//            user.setName(excelProject.getPrincipal());
//            user.setTid(excelProject.getTid());
//            excelProject.setPrincipalId(userDao.selectByName(user));
//            projectUserDao.addPrincipal(excelProject);
//        }
    if (excelProject.getPlannedPublicationDate() != null && excelProject.getPlannedPublicationDate() != "") {
      if (StringUtils.isInteger(excelProject.getPlannedPublicationDate())) {
        excelProject.setPlannedPublicationDate(Time.timeToDate(excelProject.getPlannedPublicationDate()));
      }
    }
    if (excelProject.getActualPublicationDate() != null && excelProject.getActualPublicationDate() != "") {
      if (StringUtils.isInteger(excelProject.getActualPublicationDate())) {
        excelProject.setActualPublicationDate(Time.timeToDate(excelProject.getActualPublicationDate()));
      }
    }
    return  newUser;
  }
//        if (excelProject.getProfessionalDate() != null && excelProject.getProfessionalDate() !="") {
//            if (StringUtils.isInteger(excelProject.getProfessionalDate())){
//            excelProject.setProfessionalDate(Time.timeToDate(excelProject.getProfessionalDate()));
//            }
//        }
//        if (excelProject.getWithdrawalDate() != null && excelProject.getWithdrawalDate() !="") {
//            if (StringUtils.isInteger(excelProject.getWithdrawalDate())) {
//                excelProject.setWithdrawalDate(Time.timeToDate(excelProject.getWithdrawalDate()));
//            }
//        }
//        if (excelProject.getCheckerCompletionDate() != null && excelProject.getCheckerCompletionDate() !="") {
//            if (StringUtils.isInteger(excelProject.getCheckerCompletionDate())) {
//                excelProject.setCheckerCompletionDate(Time.timeToDate(excelProject.getCheckerCompletionDate()));
//            }
//        }
//        if (excelProject.getPrincipalCompletionDate() != null && excelProject.getPrincipalCompletionDate() !="") {
//            if (StringUtils.isInteger(excelProject.getPrincipalCompletionDate())) {
//                excelProject.setPrincipalCompletionDate(Time.timeToDate(excelProject.getPrincipalCompletionDate()).toString());
//            }
//        }
//        if (excelProject.getHeadmanCompletionDate() != null && excelProject.getHeadmanCompletionDate() !="") {
//            if (StringUtils.isInteger(excelProject.getHeadmanCompletionDate())) {
//                excelProject.setHeadmanCompletionDate(Time.timeToDate(excelProject.getHeadmanCompletionDate()).toString());
//            }
//        }
//        volumeDao.addExcelVolume(excelProject);
//        if (excelProject.getDesigner() != null && excelProject.getDesigner() !=""){
//            User user = new User();
//            user.setTid(excelProject.getTid());
//            user.setName(excelProject.getDesigner());
//            Integer designerId = userDao.selectByName(user);
//            if (designerId != null) {
//                excelProject.setDesignerId(designerId);
//            }else {
//                user.setPid(4);
//                user.setDid(departmentDao.selectByTec(user.getTid()));
//                user.setUsername(StringUtils.generateString(3) + Time.getDate());
//                user.setPaw("1234");
//                if (user.getDid() == null){
//                    user.setDid();
//                }
//                userDao.add(user);
//                Map map = new HashMap();
//                map.put("username" , user.getUsername());
//                map.put("name", user.getName());
//                newUser.add(map) ;
//                excelProject.setDesignerId(user.getId());
//            }
//            volumeUserDao.addExcelDesigner(excelProject);
//        }
//        if (excelProject.getChecker() != null && excelProject.getChecker() !=""){
//            User user = new User();
//            user.setTid(excelProject.getTid());
//            user.setName(excelProject.getChecker());
//            Integer checkerId = userDao.selectByName(user);
//            if (checkerId != null) {
//                excelProject.setCheckerId(checkerId);
//            }else {
//                user.setPid(4);
//                user.setDid(departmentDao.selectByTec(user.getTid()));
//                user.setUsername(StringUtils.generateString(3)+ Time.getDate());
//                user.setPaw("1234");
//                userDao.add(user);
//                Map map = new HashMap();
//                map.put("username" , user.getUsername());
//                map.put("name", user.getName());
//                newUser.add(map) ;
//                excelProject.setCheckerId(user.getId());
//            }
//            volumeUserDao.addExcelChecker(excelProject);
//        }

//        return newUser;
//    }

    @Override
    public Map queryById(Integer id) {
        Map map = projectDao.queryById(id);
        return map;
    }

    @Override
    public List<Map> queryByAdmin(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByAdmin(user);
    }

    @Override
    public List<Map> queryByGeneral(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByGeneral(user);
    }

    @Override
    public List<Map> queryByPrincipal(User user) {
        user = userDao.queryById(user.getId());
        Calendar calendar = Calendar.getInstance();
        Integer month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long min = calendar.getTimeInMillis();
        calendar.set(Calendar.MONTH,++month);
        long max = calendar.getTimeInMillis();
        user.setMonthMin(min);
        user.setMonthMax(max);
        return projectDao.queryByPrincipal(user);
    }

    @Override
    public List<Map> queryProByPrincipal(User user) {
      user = userDao.queryById(user.getId());
      return projectDao.queryProByPrincipal(user);
    }

  @Override
    public List<Map> queryByDesigner(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByDesigner(user);
    }

    @Override
    public List<Map> queryByChecker(User user) {
        user = userDao.queryById(user.getId());
        return projectDao.queryByChecker(user);
    }

    @Override
    public Result queryByHeadman(User user) {
        user = userDao.queryById(user.getId());
        if(userPositionDao.queryByUserId(user).contains(11)){
            return Result.ok(projectDao.queryByHeadman(user));
        }
        else return Result.build(111,"您并非组长，暂时没有权限");
    }

    @Override
    public List<ExcelProject> queryExcel(Integer month) {
        User user = new User();
        user.setThisMonth(month);
        Calendar calendar1 = Calendar.getInstance();
        if (calendar1.get(Calendar.MONTH) == 0){
          if (month == 0){
            user.setThisMonth(12);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else if (month == -1){
            user.setThisMonth(11);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else {
            user.setThisMonth(month);
            user.setThisYear(calendar1.get(Calendar.YEAR));
          }
        }
        else if (calendar1.get(Calendar.MONTH) == 1){
          if (month == 0){
            user.setThisMonth(12);
            user.setThisYear(calendar1.get(Calendar.YEAR)-1);
          }
          else {
            user.setThisMonth(month);
            user.setThisYear(calendar1.get(Calendar.YEAR));
          }
        }
        else user.setThisMonth(month);
        calendar1.set(Calendar.MONTH,month-1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        long min = calendar1.getTimeInMillis();
        calendar1.set(Calendar.MONTH,month);
        long max = calendar1.getTimeInMillis();
        user.setMonthMin(min);
        user.setMonthMax(max);
        user.setSqlDate(user.getThisYear() + "-" + user.getThisMonth() + "-31");
        List<Map> maps = projectDao.queryExcel(user);
        List<ExcelProject> list = new ArrayList<>();
        for (Map map : maps){
            ExcelProject excelProject = new ExcelProject();
            excelProject.setNumber(map.get("number").toString());
            excelProject.setVolumeName(map.get("volumeName").toString());
            excelProject.setProjectName(map.get("projectName").toString());
            if (map.get("tec") != null) {
                excelProject.setTec(map.get("tec").toString());
            }
//            if (map.get("planned_publication_date") != null) {
//                if (StringUtils.isInteger(map.get("planned_publication_date").toString())){
//                    excelProject.setPlannedPublicationDate(Time.longToString(map.get("planned_publication_date").toString()));
//                }else
                    excelProject.setPlannedPublicationDate(map.get("planned_publication_date").toString());
//            }
//            if (map.get("actual_publication_date") != null) {
//                if (StringUtils.isInteger(map.get("actual_publication_date").toString())) {
//                    excelProject.setActualPublicationDate(Time.longToString(map.get("actual_publication_date").toString()));
//                }else
                    excelProject.setActualPublicationDate(map.get("actual_publication_date").toString());
                    excelProject.setPlanned_shot_date(map.get("planned_shot_date").toString());
//            }
//            if (map.get("professional_date") != null) {
//                if (StringUtils.isInteger(map.get("professional_date").toString())) {
//                    excelProject.setProfessionalDate(Time.longToString(map.get("professional_date").toString()));
//                }else
//                    excelProject.setProfessionalDate(map.get("professional_date").toString());
//            }
//            if (map.get("withdrawal_date") != null) {
//                if (StringUtils.isInteger(map.get("withdrawal_date").toString())) {
//                    excelProject.setWithdrawalDate(Time.longToString(map.get("withdrawal_date").toString()));
//                }else
//                    excelProject.setWithdrawalDate(map.get("withdrawal_date").toString());
//            }
            if (map.get("designer") != null) {
                excelProject.setDesigner(map.get("designer").toString());
            }
//            if (map.get("shot_date") != null) {
//                if (StringUtils.isInteger(map.get("shot_date").toString())) {
//                    excelProject.setShotDate(Time.longToString(map.get("shot_date").toString()));
//                }else
                    excelProject.setShotDate(map.get("shot_date").toString());
//            }
            Calendar calendar = Calendar.getInstance();
            //主要用于月份判断,所有不需要再加1
            Integer nowMonth = calendar.get(Calendar.MONTH);
            //判断当前月为1月或2月时下载上一年的数据
            if (nowMonth == 1 ){
                if (month == 12 || month == 11)
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            }
            if (nowMonth == 2 ){
                if (month == 12)
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            }
            int week ;
            if (nowMonth.equals(month)) {
                week = calendar.get(Calendar.WEEK_OF_MONTH);
            }else {
                week = calendar.getActualMaximum(month);
            }
            if (map.get("designerList") != null && map.get("designerList") != null) {
                String []designerData = map.get("designerList").toString().split("##");
                int designerLength;
                if (designerData.length >= 6){
                    designerLength = 6;
                }else {
                    designerLength = designerData.length;
                }
                for (int i = 0 ; i < designerLength ; i++ ) {
                    if(designerData[i] != null && !designerData[i].equals("")) {
                        String[] report = designerData[i].split("\\$",-1);
                        if (report.length >= 1 ) {
                            calendar.setTimeInMillis(Long.parseLong(report[2]));
                          System.out.println(calendar.get(Calendar.MONTH));
                            if (calendar.get(Calendar.MONTH)+1 == month) {
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 1) {
                                    excelProject.setDesignerOne(report[0]);
                                    excelProject.setDesignerOneRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 2) {
                                    excelProject.setDesignerTwo(report[0]);
                                    excelProject.setDesignerTwoRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                                    excelProject.setDesignerThree(report[0]);
                                    excelProject.setDesignerThreeRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                                    excelProject.setDesignerFour(report[0]);
                                    excelProject.setDesignerFourRemark(report[1]);
                                }
                                if (calendar.get(Calendar.WEEK_OF_MONTH) == 5) {
                                    excelProject.setDesignerFive(report[0]);
                                    excelProject.setDesignerFiveRemark(report[1]);
                                }
                            } else if (calendar.get(Calendar.MONTH) == month - 1) {
                                excelProject.setDesignerLastMonth(report[0] + "%");
                                break;
                            }
                        }
                    }
                }
            }
            if (map.get("checker") != null) {
                excelProject.setChecker(map.get("checker").toString());
            }
            if (map.get("checker_date") != null) {
                if (StringUtils.isInteger(map.get("checker_date").toString())) {
                    excelProject.setCheckerCompletionDate(Time.longToString(map.get("checker_date").toString()));
                }else
                    excelProject.setCheckerCompletionDate(map.get("checker_date").toString());
            }
            if (map.get("checkerList") != null && map.get("checkerList") != null) {
                String []checkerData = map.get("checkerList").toString().split("##");
                int checkLength;
                if (checkerData.length >= 3){
                    checkLength = 3;
                }else {
                    checkLength = checkerData.length;
                }
                for (int i = 0 ; i < checkLength ; i++ ) {
                    if (checkerData[i] != null) {
                        String[] report = checkerData[i].split("\\$", -1);
                        calendar.setTimeInMillis(Long.parseLong(report[2]));
                        if (calendar.get(Calendar.MONTH)+1 == month) {
                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                                excelProject.setCheckerNowWeek(report[0]);
                                excelProject.setCheckerRemark(report[1]);
                            }
                            else {
                              if (excelProject.getCheckerLastWeek() == null)
                                excelProject.setCheckerLastWeek(report[0]);
                            }
                        }else if (calendar.get(Calendar.MONTH) == month - 1) {
                            excelProject.setCheckerLastMonth(report[0] + "%");
                            break;
                        }
                    }
                    }
                }
            if (map.get("principal") != null) {
                excelProject.setPrincipal(map.get("principal").toString());
            }
//            if (map.get("complete_time") != null) {
//                if (StringUtils.isInteger(map.get("complete_time").toString())){
//                    excelProject.setPrincipalCompletionDate(Time.longToString(map.get("complete_time").toString()));
//                }else
//                    excelProject.setPrincipalCompletionDate(map.get("complete_time").toString());
//            }
            if (map.get("principalList") != null && map.get("principalList") != null) {
                String []principalData = map.get("principalList").toString().split("##");
                int principalLength;
                if (principalData.length >= 3){
                    principalLength = 3;
                }else {
                    principalLength = principalData.length;
                }
                for (int i = 0 ; i < principalLength ; i++ ) {
                    if(principalData[i] != null) {
                        String[] report = principalData[i].split("\\$", -1);
                        calendar.setTimeInMillis(Long.parseLong(report[2]));
                        if (calendar.get(Calendar.MONTH) == month) {
                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
                                excelProject.setPrincipalNowWeek(report[0]);
                                excelProject.setPrincipalRemark(report[1]);
                            }
                            else if (excelProject.getPrincipalLastWeek() != null)
                                excelProject.setPrincipalLastWeek(report[0]);
                            }
                        }
                    }
                }
//            excelProject.setHeadman("");
//            if (map.get("headman_date") != null) {
//                if (StringUtils.isInteger(map.get("headman_date").toString())) {
//                    excelProject.setHeadmanCompletionDate(Time.longToString(map.get("headman_date").toString()));
//                }else
//                    excelProject.setHeadmanCompletionDate(map.get("headman_date").toString());
//            }
//            if (map.get("headmanList") != null && map.get("headmanList") != null) {
//                String []headmanData = map.get("headmanList").toString().split("##");
//                int headmanLength;
//                if (headmanData.length >= 3){
//                    headmanLength = 3;
//                }else {
//                    headmanLength = headmanData.length;
//                }
//                for (int i = 0 ; i < headmanLength ; i++ ) {
//                    if (headmanData != null) {
//                        String[] report = headmanData[i].split("\\$", -1);
//                        calendar.setTimeInMillis(Long.parseLong(report[2]));
//                        if (calendar.get(Calendar.MONTH) == month) {
//                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week) {
//                                excelProject.setHeadmanNowWeek(report[0]);
//                                excelProject.setHeadmanRemark(report[1]);
//                            }
//                            if (calendar.get(Calendar.WEEK_OF_MONTH) == week - 1) {
//                                excelProject.setHeadmanLastWeek(report[0]);
//                            }
//                        }
//                    }
//                }
//            }
            list.add(excelProject);
        }
        return list;
    }

  @Override
  public Result drawLine(Integer id) {
    return Result.ok(projectDao.drawLine(id));
  }

  @Override
  public Result queryAll() {
    return Result.ok(projectDao.queryAll());
  }

  @Override
  public Result queryPrincipal(Integer id) {
    return Result.ok(projectDao.queryPrincipal(id));
  }
}
