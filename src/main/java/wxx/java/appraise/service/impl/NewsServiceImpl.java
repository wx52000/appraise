package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.NewsDao;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
  private NewsDao newsDao;
  @Autowired
  public void setNewsDao(NewsDao newsDao){
    this.newsDao = newsDao;
  }
  @Override
  public Result newsCount(Integer id) {
    return Result.ok(newsDao.newsCount(id));
  }

  @Override
  public Result query(Integer id) {
    return Result.ok(newsDao.query(id));
  }
}
