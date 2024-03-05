package com.headline.dao.impl;

import com.headline.dao.BaseDao;
import com.headline.dao.NewsHeadLineDao;
import com.headline.pojo.NewsHeadline;
import com.headline.pojo.vo.HeadlineDetailVo;
import com.headline.pojo.vo.HeadlinePageVo;
import com.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadLineDaoImpl extends BaseDao implements NewsHeadLineDao {

    /*
        private Integer hid;
        private String title;
        private Integer type;
        private Integer pageViews;
        private Long pastHours;
        private Integer publisher;

            private String keyWords;
            private Integer type;
            private Integer pageNum;
            private Integer pageSize;
     */
    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList<>();
        String sql = """
                select 
                    hid,
                    title,
                    type,
                    page_views as pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,now()) as pastHours,
                    publisher 
                from 
                    news_headline
                where 
                    is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        sql = sql.concat(" order by pastHours ASC ,page_views DESC");
        sql = sql.concat(" limit ?,? ");
        // limit查询的起始条数
        int pageNum = (headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize();
        params.add(pageNum);
        params.add(headlineQueryVo.getPageSize());
        return baseQuery(HeadlinePageVo.class, sql, params.toArray());
    }

    @Override
    public int findPageCount(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList<>();
        String sql = """
                select 
                    count(1)
                from 
                    news_headline
                where 
                    is_deleted = 0
                """;
        if (headlineQueryVo.getType() != 0) {
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }
        Long count = baseQueryObject(Long.class, sql, params.toArray());
        return count.intValue();
    }

    @Override
    public HeadlineDetailVo findHeadLineByHid(Integer hid) {
        String sql = """
                select
                   h.hid hid,
                   h.title title,
                   h.article article,
                   h.type type,
                   t.tname typeName,
                   h.page_views pageViews,
                   TIMESTAMPDIFF(HOUR, create_time, NOW()) pastHours,
                   h.publisher,
                   u.nick_name author
                from
                    news_headline h
                left join
                    news_type t
                on
                    h.type = t.tid
                left join
                    news_user u
                on
                    h.publisher = u.uid
                where
                    h.hid = ?
                """;
        List<HeadlineDetailVo> headlineDetailVoList = baseQuery(HeadlineDetailVo.class, sql, hid);
        if (null != headlineDetailVoList && headlineDetailVoList.size() > 0)
            return headlineDetailVoList.get(0);
        return null;
    }

    @Override
    public void increasePageViews(Integer hid) {
        String sql = """
                update
                    news_headline
                set
                    page_views = page_views + 1
                where
                    hid = ?
                """;
        baseUpdate(sql, hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline headline) {
        String sql = """
                insert into
                    news_headline
                values(DEFAULT,?,?,?,?,0,now(),now(),0)
                """;
        return baseUpdate(sql,
                headline.getTitle(),
                headline.getArticle(),
                headline.getType(),
                headline.getPublisher()
        );
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        String sql = """
                select
                    hid,
                    title,
                    article,
                    type
                from
                    news_headline
                where
                    hid = ?
                """;
        List<NewsHeadline> newsHeadlineList = baseQuery(NewsHeadline.class, sql, hid);
        if (null != newsHeadlineList && newsHeadlineList.size() > 0)
            return newsHeadlineList.get(0);
        return null;
    }

    @Override
    public int update(NewsHeadline headline) {
        String sql = """
                update
                    news_headline
                set
                    title = ?,
                    article = ?,
                    type = ?,
                    update_time = now()
                where
                 hid = ?
                 and is_deleted = 0
                """;
        return baseUpdate(sql,
                headline.getTitle(),
                headline.getArticle(),
                headline.getType(),
                headline.getHid()
        );
    }

    @Override
    public int removeByHid(int hid) {
        String sql = """
                update
                    news_headline
                set
                    is_deleted = 1
                where
                    hid = ?
                """;
        return baseUpdate(sql, hid);
    }
}
