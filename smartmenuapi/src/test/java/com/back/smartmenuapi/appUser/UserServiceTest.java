package com.back.smartmenuapi.appUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        //Save
        User user = User.builder()
                .id(1L)
                .name("Test User")
                .build();
        Mockito.when(userRepository.save(user)).thenReturn(user);
        //Find all
        List<User> testUsers = Arrays.asList(
                User.builder().id(1L).name("Test User 1").build(),
                User.builder().id(2L).name("Test User 2").build()
        );
        Mockito.when(userRepository.findAll()).thenReturn(testUsers);
        //Find by id
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        //Update
        User updatedUser = User.builder()
                .id(1L)
                .name("Updated User")
                .build();
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        //Delete
        Long id = 1L;
        Mockito.doNothing().when(userRepository).deleteById(id);
    }

    @Test
    public void testSaveUser() {
        User testUser = User.builder()
                .id(1L)
                .name("Test User")
                .build();

        User savedUser = userService.saveUser(testUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(testUser);
        assertEquals(testUser, savedUser);
        System.out.println("savedUser = " + savedUser);
    }

    @Test
    public void testFindAllUser() {
        List<User> testUsers = Arrays.asList(
                User.builder().id(1L).name("Test User 1").build(),
                User.builder().id(2L).name("Test User 2").build()
        );

        List<User> users = userService.findAllUser();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        assertEquals(testUsers, users);
        System.out.println("users = " + users);
    }

    @Test
    public void testFindUserById() {
        Long id = 1L;

        try {
            User user = userService.findUserById(id);
            Mockito.verify(userRepository, Mockito.times(1)).findById(id);
            assertNotNull(user);
            System.out.println("user = " + user);
        } catch (com.back.smartmenuapi.error.NotFoundException e) {
            fail("User not found exception was thrown");
        }
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        User initialUser = User.builder()
            .id(id)
            .name("Initial User")
            .build();

        System.out.println("initialUser = " + initialUser);

        User updatedUser = User.builder()
            .id(id)
            .name("Updated User")
            .build();

        try {
            User resultUser = userService.updateUser(id, updatedUser);
            Mockito.verify(userRepository, Mockito.times(1)).save(updatedUser);
            assertEquals(updatedUser, resultUser);
            System.out.println("resultUser = " + resultUser);
        } catch (com.back.smartmenuapi.error.NotFoundException e) {
            fail("User not found exception was thrown");
        }
    }

    @Test
    public void testDeleteUser() {
    }
}