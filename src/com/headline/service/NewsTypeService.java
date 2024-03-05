package com.headline.service;

import com.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {

    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型一List<NewsList>集合形式返回
     */
    List<NewsType> findAll();
}
