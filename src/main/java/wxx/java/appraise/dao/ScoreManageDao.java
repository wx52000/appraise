package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ScoreManage;

@Repository
public interface ScoreManageDao {

  void setScoreMange(ScoreManage scoreMange);

  void del(ScoreManage scoreManage);
}
