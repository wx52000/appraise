package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import wxx.java.appraise.service.UserPositionService;

public class UserPositionController {
    private UserPositionService userPositionService;
    @Autowired
    public void setUserPositionService(UserPositionService userPositionService){
        this.userPositionService = userPositionService;
    }


}
