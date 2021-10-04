package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> getAllUser(String admin);
    User getUserByNameAndPassword(String name, String password);
    Integer addUser(String id, String name, String password);

}
