package com.headline.dao.impl;

import com.headline.dao.BaseDao;
import com.headline.dao.NewsTypeDao;
import com.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        String sql = "select tid, tname from news_type";
        return baseQuery(NewsType.class, sql);
    }
}
