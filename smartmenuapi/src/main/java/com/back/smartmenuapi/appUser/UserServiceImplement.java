package com.back.smartmenuapi.appUser;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        User userDb = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
            userDb.setUsername(user.getUsername());
        }

        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            userDb.setPassword(user.getPassword());
        }

        if (Objects.nonNull(user.getFirstname()) && !"".equalsIgnoreCase(user.getFirstname())) {
            userDb.setFirstname(user.getFirstname());
        }

        return userRepository.save(userDb);
    }


    @Override
    public void deleteUser(Long id) {
        User userDb = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(userDb);
    }
}
