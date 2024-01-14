package com.lizhankang.springbootdemo.sevice.impl;

import com.lizhankang.springbootdemo.dao.UserMapper;
import com.lizhankang.springbootdemo.dao.entity.User;
import com.lizhankang.springbootdemo.dto.request.ReqUser;
import com.lizhankang.springbootdemo.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public User getUser(Integer id, String name) {
        return userMapper.getUserByIdAndName(id, name);
    }

    @Override
    public Integer registerUser(ReqUser reqUser) {
        // 创建 dao User实体类
        User user1 = User.builder()
                .userName(reqUser.getUser_name())
                .passWord(reqUser.getPass_word())
                .build();

        return userMapper.registerUser(user1);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
