package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.ReasonDao;
import wxx.java.appraise.service.ReasonService;

import java.util.List;
import java.util.Map;

@Service
public class ReasonServiceImpl implements ReasonService {
  private ReasonDao reasonDao;
  @Autowired
  private void setReasonDao(ReasonDao reasonDao){
    this.reasonDao = reasonDao;
  }

  @Override
  public List<Map> queryByType(Integer type) {
    return reasonDao.queryByType(type);
  }
}

