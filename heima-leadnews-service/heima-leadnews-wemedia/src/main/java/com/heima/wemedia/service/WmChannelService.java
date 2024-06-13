package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.pojos.WmChannel;


public interface WmChannelService extends IService<WmChannel> {

    public ResponseResult findAll();
}