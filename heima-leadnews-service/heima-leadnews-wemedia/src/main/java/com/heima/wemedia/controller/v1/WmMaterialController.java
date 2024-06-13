package com.heima.wemedia.controller.v1;

import com.heima.wemedia.service.WmMaterialService;
import com.itheima.file.service.FileStorageService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.common.enums.AppHttpCodeEnum;
import com.itheima.model.wemedia.dtos.WmMaterialDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {

    @Autowired
    private WmMaterialService wmMaterialService;


    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }

    /**
     * 素材列表
     * @param wmMaterialDto
     * @return
     */
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto wmMaterialDto){
        return wmMaterialService.findList(wmMaterialDto);
    }


}
