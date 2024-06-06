package com.heima.article.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;

import com.heima.article.service.ApArticleService;
import com.itheima.common.constants.ArticleConstants;
import com.itheima.model.article.dtos.ArticleHomeDto;
import com.itheima.model.article.pojos.ApArticle;
import com.itheima.model.common.dtos.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;
    /**
     * 加载文章列表
     *
     * @param dto
     * @param type 1 加载更多  2 加载最新
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //1.校验参数
        Integer size = dto.getSize();
        if(size==null||size==0){
            size= ArticleConstants.DEFAULT_PAGE_SIZE;
        }
        size = Math.min(size,ArticleConstants.MAX_PAGE_SIZE);
        dto.setSize(size);

        //类型参数检验
        if(!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            type=ArticleConstants.LOADTYPE_LOAD_MORE;
        }

        //文章频道检验
        if(StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }

        //时间检验
        if(dto.getMaxBehotTime()==null)dto.setMaxBehotTime(new Date());
        if(dto.getMinBehotTime()==null)dto.setMinBehotTime(new Date());

        List<ApArticle> apArticleList = apArticleMapper.loadArticleList(dto, type);
        return ResponseResult.okResult(apArticleList);
    }
}
