package com.flagtick.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagtick.SampleDataJpaApplication;
import com.flagtick.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
public class UserRepositoryIntegrationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setName("Flagtick Group");
        user.setPassword("Abc@123456");
        user.setEmail("flagtick@gmail.com");
        User savedUser = userRepository.save(user);
        System.out.println(savedUser.toString());
    }

    @Test
    public void updateUserTest() {
        User user = userRepository.findOne(1L);
        if(user != null) {
            user.setName("Vuong Nguyen");
            userRepository.save(user);
        }
    }

    @Test
    public void deleteUserTest() {
        User user = userRepository.findOne(1L);
        if (user != null) {
            userRepository.delete(1L);
        }
    }

    @Test
    public void getAllUsers() {
        List<User> users = userRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(users);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
