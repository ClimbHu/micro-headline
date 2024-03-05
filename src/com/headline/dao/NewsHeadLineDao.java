package com.headline.dao;

import com.headline.pojo.NewsHeadline;
import com.headline.pojo.vo.HeadlineDetailVo;
import com.headline.pojo.vo.HeadlinePageVo;
import com.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {
    /**
     * 根据 headlineQueryVo 查询新闻信息
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 查询新闻总条数
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查询头条详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadLineByHid(Integer hid);

    /**
     * 修改新闻信息浏览量+1
     * @param hid
     */
    void increasePageViews(Integer hid);

    /**
     * 新增一条新闻信息
     * @param headline
     * @return
     */
    int addNewsHeadline(NewsHeadline headline);

    /**
     * 根据hid查询头条信息
     * @param hid
     * @return
     */
    NewsHeadline findByHid(int hid);

    /**
     * 更新新闻信息
     * @param headline
     */
    int update(NewsHeadline headline);

    /**
     * 删除头条
     * @param hid
     * @return
     */
    int removeByHid(int hid);
}
