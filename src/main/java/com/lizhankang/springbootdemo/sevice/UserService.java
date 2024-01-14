package com.lizhankang.springbootdemo.sevice;

import com.lizhankang.springbootdemo.dao.entity.User;
import com.lizhankang.springbootdemo.dto.request.ReqUser;

import java.util.List;

public interface UserService {
    User getUser(Integer id);

    List<User> getAll();

    User getUserByName(String name);

    User getUser(Integer id, String name);

    Integer registerUser(ReqUser reqUser);

    User getUserById(Integer id);
}
