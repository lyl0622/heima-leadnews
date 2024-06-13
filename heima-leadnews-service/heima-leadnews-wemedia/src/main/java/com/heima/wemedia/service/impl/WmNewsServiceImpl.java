package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.service.WmNewService;
import com.itheima.model.common.dtos.PageResponseResult;
import com.itheima.model.common.dtos.ResponseResult;
import com.itheima.model.common.enums.AppHttpCodeEnum;
import com.itheima.model.wemedia.dtos.WmNewsPageReqDto;
import com.itheima.model.wemedia.pojos.WmNews;
import com.itheima.model.wemedia.pojos.WmUser;
import com.itheima.utils.tread.WmTreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class WmNewsServiceImpl extends ServiceImpl< WmNewsMapper,WmNews> implements WmNewService {

    @Override
    public ResponseResult findList(WmNewsPageReqDto wmNewsPageReqDto) {
        //状态查询
        if(wmNewsPageReqDto==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //检查分页参数
        wmNewsPageReqDto.checkParam();

        //获取当前登录人的信息
        WmUser user = WmTreadLocalUtil.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //分页参数
        IPage<WmNews> page=new Page<>(wmNewsPageReqDto.getPage(),wmNewsPageReqDto.getSize());
        LambdaQueryWrapper<WmNews> wrapper=new LambdaQueryWrapper<>();
        //获取状态
        if(wmNewsPageReqDto.getStatus()!=null){
            wrapper.eq(WmNews::getStatus,wmNewsPageReqDto.getStatus());
        }

        //获取时间范围
        if(wmNewsPageReqDto.getBeginPubDate()!=null && wmNewsPageReqDto.getEndPubDate()!=null){
            wrapper.between(WmNews::getPublishTime,wmNewsPageReqDto.getBeginPubDate(),wmNewsPageReqDto.getEndPubDate());
        }

        //获取频道
        if(wmNewsPageReqDto.getChannelId()!=null){
            wrapper.eq(WmNews::getChannelId,wmNewsPageReqDto.getChannelId());
        }

        //关键字查询
        if(wmNewsPageReqDto.getKeyword()!=null){
            wrapper.like(WmNews::getTitle,wmNewsPageReqDto.getKeyword());
        }

        //查询当前登录用户的文章
        wrapper.eq(WmNews::getUserId,user.getApUserId());

        //发布时间倒序
        wrapper.orderByDesc(WmNews::getPublishTime);

        page=page(page,wrapper);

        //返回结果
        PageResponseResult pageResponseResult = new PageResponseResult(wmNewsPageReqDto.getPage(),wmNewsPageReqDto.getSize(), (int) page.getTotal());

        pageResponseResult.setData(page.getRecords());
        return pageResponseResult;
    }
}
