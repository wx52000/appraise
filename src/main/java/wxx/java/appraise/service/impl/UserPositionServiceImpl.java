package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.UserPositionDao;
import wxx.java.appraise.entity.UserPosition;
import wxx.java.appraise.service.UserPositionService;

import java.util.List;

@Transactional
@Service
public class UserPositionServiceImpl implements UserPositionService {
    private UserPositionDao userPositionDao;

    @Autowired
    public void setUserPositionService(UserPositionDao userPositionDao){
        this.userPositionDao = userPositionDao;
    }
    @Override
    public void upd(List<UserPosition> list) {
        userPositionDao.del(list);
        userPositionDao.add(list);
    }
}
