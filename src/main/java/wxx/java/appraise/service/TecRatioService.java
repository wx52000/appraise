package wxx.java.appraise.service;

import wxx.java.appraise.entity.TecRatio;
import wxx.java.appraise.result.Result;

public interface TecRatioService {

  Result queryByTec(Integer id);

  Result setRatioByTec(TecRatio tecRatio);
}
