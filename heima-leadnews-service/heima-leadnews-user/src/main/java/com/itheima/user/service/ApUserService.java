package com.itheima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.user.pojos.ApUser;
import com.itheima.model.user.dtos.LoginDto;

public interface ApUserService extends IService<ApUser> {


    /**
     * app端登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
