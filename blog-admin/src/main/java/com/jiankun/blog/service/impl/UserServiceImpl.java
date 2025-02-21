package com.jiankun.blog.service.impl;

import com.jiankun.blog.dao.IUserDao;
import com.jiankun.blog.dao.impl.UserDaoImpl;
import com.jiankun.blog.pojo.User;
import com.jiankun.blog.service.IUserService;
import com.jiankun.blog.utils.MD5Util;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 14:03
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public void updatePassword(String name, String newPassword) {
        userDao.updatePassword(name, newPassword);
    }
}
