package wxx.java.appraise.dao;

import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserPosition;

import java.util.List;

@Repository
public interface UserPositionDao {

    void add(List<UserPosition> list);

    void del(List<UserPosition> list);

    List<Integer> queryByUserId(User user);
}
