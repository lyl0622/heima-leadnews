package com.itheima.user.controller.v1;

import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.user.dtos.LoginDto;
import com.itheima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="app端用户登录",tags="app端用户登录")
@RestController
@RequestMapping("/api/v1/login")
public class ApUserLoginController {
    @Autowired
    private ApUserService apUserService;

    @ApiOperation("用户登录功能")
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto dto){
        return apUserService.login(dto);
    }
}
