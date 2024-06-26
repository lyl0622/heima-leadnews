package com.heima.wemedia.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import com.itheima.file.service.FileStorageService;
import com.itheima.model.common.dtos.PageResponseResult;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.common.enums.AppHttpCodeEnum;
import com.itheima.model.wemedia.dtos.WmMaterialDto;
import com.itheima.model.wemedia.pojos.WmMaterial;
import com.itheima.utils.tread.WmTreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //1.检查参数
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.上传图片到minIO中
        String fileName = UUID.randomUUID().toString().replace("-", "");

        String originalFilename = multipartFile.getOriginalFilename();
        String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileId=null;
        try {
            fileId = fileStorageService.uploadImgFile("", fileName + postfix, multipartFile.getInputStream());
            log.info("上传图片到MinIo.fileID:{}", fileId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("WmMaterialServiceImpl-文件上传失败");
        }


        //3.保存到数据库中
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmTreadLocalUtil.getUser().getApUserId());
        wmMaterial.setUrl(fileId);
        wmMaterial.setIsCollection((short)0);
        wmMaterial.setType((short)0);
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);

        //4.返回结果
        return ResponseResult.okResult(wmMaterial);
    }

    /**
     * 素材列表查询
     * @param wmMaterialDto
     * @return
     */
    @Override
    public ResponseResult findList(WmMaterialDto wmMaterialDto) {
        //1.检查分页参数
        wmMaterialDto.checkParam();

        //2.分页查询
        IPage page=new Page(wmMaterialDto.getPage(),wmMaterialDto.getSize());
        LambdaQueryWrapper<WmMaterial> wrapper=new LambdaQueryWrapper<>();

        //是否收藏
        if(wmMaterialDto.getIsCollection()!=null && wmMaterialDto.getIsCollection()==1){
            wrapper.eq(WmMaterial::getIsCollection,wmMaterialDto.getIsCollection());
        }

        //按照用户查询
        wrapper.eq(WmMaterial::getUserId,WmTreadLocalUtil.getUser().getApUserId());

        //按时间倒序
        wrapper.orderByDesc(WmMaterial::getCreatedTime);

        page(page,wrapper);
        //3.结果返回


        PageResponseResult responseResult = new PageResponseResult(wmMaterialDto.getPage(),wmMaterialDto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }
}
