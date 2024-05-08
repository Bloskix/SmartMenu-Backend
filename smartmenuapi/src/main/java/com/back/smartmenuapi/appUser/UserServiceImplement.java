package com.back.smartmenuapi.appUser;

import com.back.smartmenuapi.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public User findUserById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found in database");
        }
        return user.get();
    }

    @Override
    public User updateUser(Long id, User user) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found in database");
        }
        if (user.getName() != null && !"".equalsIgnoreCase(user.getName())) {
            user.setName(user.getName());
        }
        if (user.getEmail() != null && !"".equalsIgnoreCase(user.getEmail())) {
            user.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !"".equalsIgnoreCase(user.getPassword())) {
            user.setPassword(user.getPassword());
        }
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found in database");
        }
        userRepository.deleteById(id);
    }
}
