package com.headline.service;

import com.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户登录的账户查找用户的方法
     *
     * @param username 用户登录的账户
     * @return 找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查找用户的方法
     * @param userId 用户的id
     * @return 找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 注册用户的方法
     * @param registUser 注册用户信息
     * @return 注册成功返回大于0的整数,失败返回0
     */
    Integer registUser(NewsUser registUser);
}
