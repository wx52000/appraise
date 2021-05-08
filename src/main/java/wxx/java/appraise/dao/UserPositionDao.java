package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.User;

import java.util.List;

@Repository
public interface UserPositionDao {

    void add(@Param("id") Integer id , @Param("list") List list);

    void del(Integer id);

    void upd( List list);

    List<Integer> queryByUserId(User user);
}
