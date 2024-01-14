package com.lizhankang.springbootdemo.dao;

import com.lizhankang.springbootdemo.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    // 使用xml方式
    User getUserByName(String username);
    User getUserById(Integer id);
    Integer registerUser(User user1);

    // 使用注解方式
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "password"),
            @Result(property = "id", column = "id")
    })
    User getUser(Integer id);

    @Select("select * from user where id = #{id} and user_name=#{name}")
    User getUserByIdAndName(@Param("id") Integer id, @Param("name") String username);

    @Select("select * from user")
    List<User> getAll();


}
