package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.TecRatioDao;
import wxx.java.appraise.entity.TecRatio;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.TecRatioService;

@Service
@Transactional
public class TecRatioServiceImpl implements TecRatioService {
  private TecRatioDao tecRatioDao;
  @Autowired
  public void setTecRatioDao(TecRatioDao tecRatioDao){
    this.tecRatioDao = tecRatioDao;
  }

  @Override
  public Result queryByTec(Integer id) {
    return Result.ok(tecRatioDao.queryByTec(id));
  }

  @Override
  public Result setRatioByTec(TecRatio tecRatio) {
    tecRatioDao.setRatioByTec(tecRatio);
    return Result.ok();
  }
}
