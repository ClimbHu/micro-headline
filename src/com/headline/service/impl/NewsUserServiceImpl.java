package com.headline.service.impl;

import com.headline.dao.NewsUserDao;
import com.headline.dao.impl.NewsUserDaoImpl;
import com.headline.pojo.NewsUser;
import com.headline.service.NewsUserService;
import com.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao userDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return userDao.findByUid(userId);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        // 处理增加数据的业务，将明密码转换成密文密码
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return userDao.insertUser(registUser);
    }
}
