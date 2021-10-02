package com.bashaev.springcourse.service;

import com.bashaev.springcourse.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();
}
