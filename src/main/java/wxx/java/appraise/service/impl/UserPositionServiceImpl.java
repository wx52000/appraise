package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.UserPositionDao;
import wxx.java.appraise.service.UserPositionService;

import java.util.*;

@Transactional
@Service
public class UserPositionServiceImpl implements UserPositionService {
    private UserPositionDao userPositionDao;

    @Autowired
    public void setUserPositionService(UserPositionDao userPositionDao){
        this.userPositionDao = userPositionDao;
    }

  @Override
  public void add(Map map) {
    List list = (List) Arrays.asList(map.get("userPosition")).get(0);
    List list1 = new ArrayList();
    int length = list.size();
    for (int i = 0; i< length ; i++ ){
      if (list.get(i) != null) {
        Map map1 = (Map<String, String>) list.get(i);
        if (map1.get("id").toString() != null && !map1.get("id").toString().equals("")) {
          list1.add(list.get(i));
          list.remove(i);
          length--;
          i--;
        }
      }
    }
    if (list.size()>0)
      userPositionDao.add(Integer.valueOf(map.get("id").toString()),list);
    if (list1.size()>0)
      userPositionDao.upd(list1);
  }

  @Override
  public void del(Integer id) {
      userPositionDao.del(id);
  }
}
