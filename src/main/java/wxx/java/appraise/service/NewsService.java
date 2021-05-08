package wxx.java.appraise.service;

import wxx.java.appraise.result.Result;

public interface NewsService {

  Result newsCount(Integer id);

  Result query(Integer id);
}
