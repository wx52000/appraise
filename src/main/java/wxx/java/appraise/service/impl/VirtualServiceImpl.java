package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.dao.VirtualDao;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.Virtual;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.VirtualService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class VirtualServiceImpl implements VirtualService {
  private VirtualDao virtualDao;
  private UserDao userDao;
  @Autowired
  public void setVirtualDao(VirtualDao virtualDao){
    this.virtualDao = virtualDao;
  }
  @Autowired
  public void setUserDao(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result setProject(Virtual virtual) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//    Pattern pattern = Pattern.compile("[^0-9]");
    virtualDao.setProject(virtual);
    //人员添加
    if (virtual.getGeneral().size()>0) {
      //若不为数字类型则删除
      List<Map<String,String>> list = virtualDao.queryRole(virtual.getId(),1);
      virtual.getGeneral().forEach(integer -> {
        for (int i=0; i < list.size();i++){
          if (integer.equals(list.get(i).get("id"))||
            integer.equals(list.get(i).get("name"))){
            list.remove(i);
            break;
          }
        }
      });
      //删除修改设总
      virtual.getGeneral().removeIf(o -> !(pattern.matcher(o).matches()));
      if (virtual.getGeneral().size() >0)
        virtualDao.addGeneral(virtual.getId(), virtual.getGeneral());
      if (list.size()>0)
        virtualDao.deleteRole(virtual.getId(),list);
    }
    if (virtual.getPrincipalState()){
      for (int i = 0; i < virtual.getPrincipal().size();i++){
        boolean exist = false;
        for (int j = 0 ;j < virtual.getPrincipal_group().size(); j ++) {
          if (virtual.getPrincipal().get(i).equals(virtual.getPrincipal_group().get(j).get("id"))) {
            exist = true;
          }
        }
          if (!exist) {
            Map map = new HashMap();
            map.put("id", virtual.getPrincipal().get(i));
            map.put("group", virtual.getPrincipal().get(i));
            virtual.getPrincipal_group().add(map);
          }
      }
        List<Map<String,String>> list = virtualDao.queryRole(virtual.getId(),2);
        virtual.getPrincipal().forEach(integer -> {
          for (int i=0; i < list.size();i++){
            if (integer.equals(String.valueOf(list.get(i).get("id")))){
              list.remove(i);
              break;
            }
          }
        });
        if (virtual.getPrincipal_group().size() > 0)
          virtualDao.addPrincipal(virtual.getId(),virtual.getPrincipal_group());
      if (list.size()>0)
        virtualDao.deleteRole(virtual.getId(),list);
    }
    if (virtual.getDesigner().size()>0){
      List<Integer> list1 = virtualDao.queryDesignerId(virtual.getId());
      virtual.getDesigner().forEach(integer -> {
        integer.getList().forEach(item -> {
        for (int i = 0; i < list1.size(); i++) {
          if (item.equals(list1.get(i))) {
            list1.remove(i);
            break;
          }
        }
        });
      });
      if (virtual.getDesigner().size() > 0)
        virtualDao.addDesigner(virtual.getId(),virtual.getDesigner());
      if (list1.size()>0)
        virtualDao.deleteDesigner(virtual.getId(),list1);
    }
    if (virtual.getPrincipalData() != null &&!virtual.getPrincipalData().isEmpty()){
      virtualDao.leaderWorkday(virtual.getId(),virtual.getPrincipalData());
    }
    if (virtual.getDesignerData() != null &&!virtual.getDesignerData().isEmpty()){
      virtualDao.designerWorkday(virtual.getId(),virtual.getDesignerData());
    }
    return Result.ok();
  }

  @Override
  public Result query() {
    return Result.ok(virtualDao.query());
  }

  @Override
  public Result queryById(Integer id) {
    Map<String,Object> map = new HashMap();
    map.put("general",virtualDao.queryByRole(id,1));
    map.put("principal",virtualDao.queryByRole1(id,2));
    map.put("principal_group",virtualDao.queryGroup(id,2));
    map.put("designer",virtualDao.queryDesignerByVirtual(id));
    return Result.ok(map);
  }

  @Override
  public Result queryDesignerByGroup(Integer id, Integer vid) {
    User user = userDao.queryById(id);
    Map map = new HashMap();
    map.put("principal",user.getId());
    map.put("name",user.getName());
    map.put("list",virtualDao.queryDesignerByGroup(id,vid));
    return Result.ok(map);
  }

  @Override
  public Result queryPrincipalWorkday(VirtualDesigner virtualDesigner) {
    return Result.ok(virtualDao.queryPrincipalWorkday(virtualDesigner));
  }

  @Override
  public Result queryDesignerWorkday(VirtualDesigner virtualDesigner) {
    return Result.ok(virtualDao.queryDesignerWorkday(virtualDesigner));
  }


}
