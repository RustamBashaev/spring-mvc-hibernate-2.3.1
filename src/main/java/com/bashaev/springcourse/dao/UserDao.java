package com.bashaev.springcourse.dao;

import com.bashaev.springcourse.entity.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void editUser(User updateUser);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);
}
