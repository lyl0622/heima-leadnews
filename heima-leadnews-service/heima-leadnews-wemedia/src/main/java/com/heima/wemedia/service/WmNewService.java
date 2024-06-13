package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.dtos.WmNewsPageReqDto;
import com.itheima.model.wemedia.pojos.WmNews;

public interface WmNewService extends IService<WmNews> {

    public ResponseResult findList(WmNewsPageReqDto wmNewsPageReqDto);
}
