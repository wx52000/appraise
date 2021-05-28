package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserOut;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserService;
import wxx.java.appraise.tools.Download;
import wxx.java.appraise.excel.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;
    @Autowired
    public  void  setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("login")
    public Result login(@RequestBody User user){
        return userService.login(user.getUsername(),user.getPaw());
    }

    @RequestMapping("add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.ok();
    }

    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        userService.del(id);
        return  Result.ok();
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody User user){
        userService.upd(user);
        return Result.ok();
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return Result.ok(userService.query(user));
    }

    @RequestMapping("queryToupd")
    public Result queryToupd(@RequestHeader Integer id){

      return Result.ok(userService.queryToupd(id));
    }

    @RequestMapping("queryByTid")
    public Result queryByTid(@RequestHeader Integer id){
        return Result.ok(userService.queryByTid(id));
    }

    @RequestMapping("queryNotSelf")
    public Result queryNotSelf(@RequestBody User user){
      return Result.ok(userService.queryNotSelf(user));
    }

    @RequestMapping("queryToScore")
    public Result queryToScore(@RequestBody User user){
      return userService.queryToScore(user);
    }

    @RequestMapping("queryScoreList")
    public Result queryScoreList(@RequestBody User user){
      return userService.queryScoreList(user);
    }

    @RequestMapping("queryNotScore")
    public Result queryNotScore(@RequestBody User user){
        return Result.ok(userService.queryNotScore(user));
    }

    @RequestMapping("queryAppriseAll")
    public Result queryAppriseAll(){
      return userService.queryAppriseAll();
    }
    @RequestMapping("queryAppraise")
    public Result queryAppraise(@RequestBody User user){
      return Result.ok(userService.queryAppraise(user));
    }

    @RequestMapping("queryNotAppraise")
    public Result queryNotAppraise(@RequestBody User user){
      return Result.ok(userService.queryNotAppraise(user));
    }

    @RequestMapping("queryNotScored")
    public Result queryNotScored(@RequestBody User user){
      return Result.ok(userService.queryNotScored(user));
    }

    @RequestMapping("queryNotTecApp")
    public Result queryNotTecApp(@RequestBody User user){
      return Result.ok(userService.queryNotTecApp(user));
    }

    @RequestMapping("queryByTec")
    public Result queryByTec(@RequestHeader Integer id){
        return Result.ok(userService.queryByTec(id));
    }

    @RequestMapping("queryByName")
    public Result queryByName(@RequestBody User user){
        return Result.ok(userService.queryByName(user));
    }

    @RequestMapping("paw")
    public Result paw(@RequestBody User user){
        userService.paw(user);
        return Result.ok();
    }
    //普通树
    @RequestMapping("userAll")
    public Result userAll(@RequestHeader Integer mode){
      return Result.ok(userService.userAll(mode));
    }
    //评价管理树，包含是否可评价状态
    @RequestMapping("userAllAndState")
    public Result userAllAndState(@RequestHeader Integer id){
      return Result.ok(userService.userAllAndState(id));
    }
    //主设人选择树，包含主设人分组
    @RequestMapping("userAllAndGroup")
    public Result userAllAndGroup(@RequestHeader Integer id,@RequestHeader Integer mode){
      return Result.ok(userService.userAllAndGroup(id,mode));
    }

  @RequestMapping("excel")
  public Result personExcel(HttpServletRequest request , HttpServletResponse response) {
    String s = "";
    Integer type = Integer.valueOf(request.getParameter("type"));
    Date date = new Date();
    try {
      List<UserOut> list = new ArrayList<>();
      User user = new User();
      if (type == 1){
        list = userService.queryAppraise(user);
      }else if (type == 2) {
        list = userService.queryNotAppraise(user);
      }else if (type == 3){
        list = userService.queryNotScored(user);
      }else if (type == 4){
        list = userService.queryNotTecApp(user);
      }
      ExcelProperty excelProperty = new ExcelProperty();
      String fileName = request.getParameter("name") + ".xlsx";
      Future<String> future = excelProperty.userExcel(list, fileName);
      s = future.get();
      Download.downloadFile(response, fileName, fileName);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Result.ok(s);
  }


  }
