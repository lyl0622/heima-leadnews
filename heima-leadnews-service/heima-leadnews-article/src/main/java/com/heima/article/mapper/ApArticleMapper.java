package com.heima.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.model.article.dtos.ArticleHomeDto;
import com.itheima.model.article.pojos.ApArticle;
import com.itheima.model.article.pojos.ApArticleContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    /**
     * 加载文章列表
     * @param dto
     * @return type 1 加载更多 2 记载最新
     */
    public List<ApArticle> loadArticleList(ArticleHomeDto dto,Short type);
}
