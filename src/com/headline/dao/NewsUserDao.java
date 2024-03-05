package com.headline.dao;

import com.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户登录的账户查找用户的方法
     * @param username
     * @return
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查找用户的方法
     * @param userId
     * @return
     */
    NewsUser findByUid(Integer userId);

    /**
     * 注册用户的方法
     * @param registUser 注册用户信息
     * @return 注册成功返回大于0的整数,失败返回0
     */
    Integer insertUser(NewsUser registUser);
}
