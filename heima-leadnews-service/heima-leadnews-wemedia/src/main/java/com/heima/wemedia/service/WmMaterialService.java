package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.wemedia.dtos.WmMaterialDto;
import com.itheima.model.wemedia.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {

    public ResponseResult uploadPicture(MultipartFile multipartFile);


    public ResponseResult findList(WmMaterialDto wmMaterialDto);
}