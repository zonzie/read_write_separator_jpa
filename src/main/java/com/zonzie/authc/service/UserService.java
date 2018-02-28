package com.zonzie.authc.service;

import com.zonzie.authc.repository.entity.User;

/**
 * Created by zonzie on 2018/2/27.
 */
public interface UserService {
    User findUser(Integer userId);

    void updateUser(User user);
}
