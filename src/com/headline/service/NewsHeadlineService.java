package com.headline.service;

import com.headline.pojo.NewsHeadline;
import com.headline.pojo.vo.HeadlineDetailVo;
import com.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 分页带条件查询所有头条
     * @param headlineQueryVo
     * @return
     */
    Map findNewsPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查询头条详情
     * @param hid 新闻id
     * @return
     */
    HeadlineDetailVo showHeadlineDetail(Integer hid);

    /**
     * 新增一条新闻信息
     * @param headline
     */
    int addNewsHeadline(NewsHeadline headline);

    /**
     * 根据hid查询头条消息
     * @param hid
     * @return
     */
    NewsHeadline findByHid(int hid);

    /**
     * 更新新闻信息
     * @param headline
     */
    int update(NewsHeadline headline);

    int removeByHid(int hid);
}
