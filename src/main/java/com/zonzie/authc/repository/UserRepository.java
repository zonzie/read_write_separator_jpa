package com.zonzie.authc.repository;

import com.zonzie.authc.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zonzie on 2018/2/27.
 */
public interface UserRepository extends JpaRepository<User,Integer>{
}
