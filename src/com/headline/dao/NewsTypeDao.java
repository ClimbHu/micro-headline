package com.headline.dao;

import com.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 查询所有头条类型
     * @return
     */
    List<NewsType> findAll();
}
