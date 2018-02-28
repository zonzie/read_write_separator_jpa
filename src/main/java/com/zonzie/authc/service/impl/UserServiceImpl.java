package com.zonzie.authc.service.impl;

import com.zonzie.authc.repository.UserRepository;
import com.zonzie.authc.repository.entity.User;
import com.zonzie.authc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by zonzie on 2018/2/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional
    public User findUser(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void updateUser(User user) {
        User one = userRepository.findOne(user.getUid());
        one.setPassword(user.getPassword());
        userRepository.save(one);
    }
}
