package com.headline.service.impl;

import com.headline.dao.NewsHeadLineDao;
import com.headline.dao.impl.NewsHeadLineDaoImpl;
import com.headline.pojo.NewsHeadline;
import com.headline.pojo.vo.HeadlineDetailVo;
import com.headline.pojo.vo.HeadlinePageVo;
import com.headline.pojo.vo.HeadlineQueryVo;
import com.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {

    private NewsHeadLineDao headLineDao = new NewsHeadLineDaoImpl();

    @Override
    public Map findNewsPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageDate = headLineDao.findPageList(headlineQueryVo);
        int totalSize = headLineDao.findPageCount(headlineQueryVo);

        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize;
        Map pageInfo = new HashMap();
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalSize", totalSize);
        pageInfo.put("pageData", pageDate);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo showHeadlineDetail(Integer hid) {
        // 修改新闻信息浏览量+1
        headLineDao.increasePageViews(hid);
        // 查询新闻详情
        HeadlineDetailVo headline = headLineDao.findHeadLineByHid(hid);
        return headline;
    }

    @Override
    public int addNewsHeadline(NewsHeadline headline) {
        return headLineDao.addNewsHeadline(headline);
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        return headLineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline headline) {
        return headLineDao.update(headline);
    }

    @Override
    public int removeByHid(int hid) {
        return headLineDao.removeByHid(hid);
    }
}
