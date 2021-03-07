package com.aidassist.utils;

import com.aidassist.bl.UserService;
import com.aidassist.model.User;
import com.aidassist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
* This class is used for creating initial user accounts
*/
@Component
public class UserLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserLoader(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setNationalIdentityNo("11111111111");
        user.setName("nurseli");
        user.setSurname("sar");
        user.setPassword("123456");

        Optional optional = userRepository.findById(user.getNationalIdentityNo());
        if (!optional.isPresent()) {
            userService.createUser(user);
        }

        user = new User();
        user.setNationalIdentityNo("22222222222");
        user.setName("aybuke");
        user.setSurname("kucukbezirci");
        user.setPassword("654321");

        optional = userRepository.findById(user.getNationalIdentityNo());
        if (!optional.isPresent()) {
            userService.createUser(user);
        }

        user = new User();
        user.setNationalIdentityNo("33333333333");
        user.setName("isil");
        user.setSurname("firatli");
        user.setPassword("456789");

        optional = userRepository.findById(user.getNationalIdentityNo());
        if (!optional.isPresent()) {
            userService.createUser(user);
        }


    }
}
