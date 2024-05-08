package com.back.smartmenuapi.appUser;

import com.back.smartmenuapi.error.NotFoundException;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> findAllUser();
    User findUserById(Long id) throws NotFoundException;
    User updateUser(Long id, User user) throws NotFoundException;
    void deleteUser(Long id) throws NotFoundException;

}
