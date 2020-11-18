package wxx.java.appraise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxx.java.appraise.service.GradeScoreService;
import wxx.java.appraise.service.GradeTecService;
import wxx.java.appraise.service.TecScoreService;
import wxx.java.appraise.service.UserScoreService;

//定时任务，用于清空评价表
@Configuration
@EnableScheduling
public class ScheduledConfig {
    private GradeScoreService gradeScoreService;
    private GradeTecService gradeTecService;
    private UserScoreService userScoreService;
    private TecScoreService tecScoreService;

    @Autowired
    public  void  setUserScore(GradeScoreService gradeScoreService){
        this.gradeScoreService = gradeScoreService;
    }
    @Autowired
    public void  setTecScoreService(GradeTecService gradeTecService){

        this.gradeTecService = gradeTecService;
    }
    @Autowired
    public void setUserScoreService(UserScoreService userScoreService){
        this.userScoreService = userScoreService;
    }
    @Autowired
    public void setTecScoreService(TecScoreService tecScoreService){
        this.tecScoreService = tecScoreService;
    }
//    @Scheduled(cron =" 0 0 0 20 * ? ")
//    public void  reset(){
//        gradeScoreService.resetState();
//        gradeTecService.resetState();
//        userScoreService.backups();
//        tecScoreService.backups();
//        userScoreService.delete();
//        tecScoreService.delete();
//    }


}
