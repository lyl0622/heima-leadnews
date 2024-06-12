package com.heima.wemedia.controller.v1;


import com.heima.wemedia.service.WmUserService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.dtos.WmLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody WmLoginDto dto){
        return wmUserService.login(dto);
    }
}
