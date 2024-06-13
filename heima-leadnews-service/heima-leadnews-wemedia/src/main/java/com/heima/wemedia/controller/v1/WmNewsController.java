package com.heima.wemedia.controller.v1;

import com.heima.wemedia.service.WmNewService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.dtos.WmNewsPageReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewService wmNewService;

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmNewsPageReqDto wmNewsPageReqDto){
        return wmNewService.findList(wmNewsPageReqDto);
    }
}
