package com.back.smartmenuapi.appUser;

import com.back.smartmenuapi.error.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/findAllUser")
    public List<User> findAll() {
        return userService.findAllUser();
    }

    @GetMapping("/findUserById/{id}")
    User findById(@PathVariable Long id) throws NotFoundException {
        return userService.findUserById(id);
    }


    @PutMapping("/updateUser/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User user) throws  NotFoundException {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
        return "User deleted";
    }
}
