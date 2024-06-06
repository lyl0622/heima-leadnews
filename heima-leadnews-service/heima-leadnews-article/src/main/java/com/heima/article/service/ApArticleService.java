package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.model.article.dtos.ArticleHomeDto;
import com.itheima.model.article.pojos.ApArticle;
import com.itheima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 加载文章列表
     * @param type 1 加载更多  2 加载最新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto,Short type);
}
