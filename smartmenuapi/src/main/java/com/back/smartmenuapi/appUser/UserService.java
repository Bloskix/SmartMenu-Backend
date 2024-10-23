package com.back.smartmenuapi.appUser;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> findAllUser();
    User findUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

}
