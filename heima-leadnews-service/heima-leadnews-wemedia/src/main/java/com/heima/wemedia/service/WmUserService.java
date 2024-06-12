package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.dtos.WmLoginDto;
import com.itheima.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}