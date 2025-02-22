package com.jiankun.blog.service;

import com.jiankun.blog.pojo.User;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/2/7 14:03
 */
public interface IUserService {
    User login(String username, String password);

    void updatePassword(String name, String newPassword);

    Boolean register(User user);
}
