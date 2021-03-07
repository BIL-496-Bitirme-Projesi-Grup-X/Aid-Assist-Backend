package com.aidassist.bl;

import com.aidassist.model.User;
import com.aidassist.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(User user) throws Exception {

        // validate before save
        if (user.getNationalIdentityNo().length() != 11) {
            throw  new Exception("National ID length should be 11!");
        }

        User createdUser = userRepository.save(user);
        logger.info("User saved {}", createdUser);
        return createdUser;
    }
}
