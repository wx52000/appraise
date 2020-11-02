package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserService;

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

    @RequestMapping("queryByTid")
    public Result queryByTid(@RequestHeader Integer id){
        return Result.ok(userService.queryByTid(id));
    }

    @RequestMapping("queryNotScore")
    public Result queryNotScore(@RequestBody User user){
        return Result.ok(userService.queryNotScore(user));
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



}
