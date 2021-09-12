package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByNameAndPassword(String name, String password);
}
