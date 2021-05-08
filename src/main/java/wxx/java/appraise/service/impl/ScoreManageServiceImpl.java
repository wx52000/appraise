package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.ScoreManageDao;
import wxx.java.appraise.entity.ScoreManage;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ScoreManageService;

@Service
@Transactional
public class ScoreManageServiceImpl implements ScoreManageService {
  private ScoreManageDao scoreManageDao;
  @Autowired
  public void setScoreManageDao(ScoreManageDao scoreManageDao){
    this.scoreManageDao = scoreManageDao;
  }
  @Override
  public Result handle(ScoreManage scoreManage) {
    scoreManage.setScoredId(scoreManage.getScoredId().substring(
      scoreManage.getScoredId().lastIndexOf("-")+1));
    if (scoreManage.getState() == null){
      scoreManageDao.del(scoreManage);
    }else {
      scoreManageDao.setScoreMange(scoreManage);
    }
    return Result.ok();
  }
  
}
