package com.jiankun.blog.dao;

import com.jiankun.blog.pojo.User;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 14:06
 */
public interface IUserDao {
    User login(String username, String password);

    void updatePassword(String name, String newPassword);
}
