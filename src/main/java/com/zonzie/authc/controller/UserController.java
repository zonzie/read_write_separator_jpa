package com.zonzie.authc.controller;

import com.zonzie.authc.repository.entity.User;
import com.zonzie.authc.service.UserService;
import com.zonzie.common.vo.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zonzie on 2018/2/27.
 */
@RestController
@Api(description = "readWriteTest")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("find")
    public BaseResponse findUser(Integer userId) {
        User user = userService.findUser(userId);
        return new BaseResponse(0,user.getUsername());
    }

    @PostMapping("update")
    public BaseResponse updateUser(User user) {
        userService.updateUser(user);
        return new BaseResponse(0,"bingo!");
    }

}
