package wxx.java.appraise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import wxx.java.appraise.service.GradeScoreService;
import wxx.java.appraise.service.GradeTecService;
import wxx.java.appraise.service.TecScoreService;
import wxx.java.appraise.service.UserScoreService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;

//定时任务，用于清空评价表
@Configuration
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {

    private GradeScoreService gradeScoreService;
    private GradeTecService gradeTecService;
    private UserScoreService userScoreService;
    private TecScoreService tecScoreService;

    //此处为每月自动更新时间
    public static final Integer setDay = 25;
    private static final String setTime =  "0 0 0 25 * ?";

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
    @Scheduled(cron = setTime)
    public void  reset(){
//        userScoreService.backups();
//        tecScoreService.backups();
//        userScoreService.delete();
//        tecScoreService.delete();
      System.out.println("运行定时重置任务");
    }

  @Scheduled(cron = "0 0 0 * * ?")
  public void  spider(){
//      String[] cmdArr = new String[]{"G:","/c","cd G:\\python_project\\zpepdi\\zpepdi\\zpepdi","/C",
//        "pyhton start_scrapy.py"};
    try {
      Process process = Runtime.getRuntime().exec("C:\\Users\\admin\\Desktop\\评价系统\\spider.bat");
//      Process process = Runtime.getRuntime().exec("C:\\Users\\Administrator\\Desktop\\spider.bat");
//      Process process = Runtime.getRuntime().exec(cmdArr);
      InputStream fis=process.getInputStream();
      InputStreamReader isr=new InputStreamReader(fis);
      //用缓冲器读行
      BufferedReader br=new BufferedReader(isr);
      String line=null;
      //直到读完为止
      while((line=br.readLine())!=null)
      {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
      Method[] methods = BatchProperties.Job.class.getMethods();
      int defaultPoolSize = 3;
      int corePoolSize = 0;
      if (methods != null && methods.length > 0) {
        for (Method method : methods) {
          Scheduled annotation = method.getAnnotation(Scheduled.class);
          if (annotation != null) {
            corePoolSize++;
          }
        }
        if (defaultPoolSize > corePoolSize)
          corePoolSize = defaultPoolSize;
      }
    scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
    }
}
