package com.headline.dao.impl;

import com.headline.dao.BaseDao;
import com.headline.dao.NewsUserDao;
import com.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {

    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                select 
                    uid, username, user_pwd as userPwd, nick_name as nickName 
                from 
                    news_user 
                where 
                    username = ?
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        return newsUserList != null && newsUserList.size() > 0 ? newsUserList.get(0) : null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                select 
                    uid, username, user_pwd as userPwd, nick_name as nickName 
                from 
                    news_user 
                where 
                    uid = ?
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        return newsUserList != null && newsUserList.size() > 0 ? newsUserList.get(0) : null;
    }

    @Override
    public Integer insertUser(NewsUser registUser) {
        String sql = "insert into news_user values(DEFAULT,?,?,?)";
        return baseUpdate(sql,
                registUser.getUsername(),
                registUser.getUserPwd(),
                registUser.getNickName());
    }
}
