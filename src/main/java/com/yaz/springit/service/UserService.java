package com.yaz.springit.service;

import com.yaz.springit.domain.User;
import com.yaz.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger= LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User Register(User user){
        return  user;
    }

    public User Save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(User... users){
        for (User user:users) {
            logger.info("saving user:"+user.getEmail());
            userRepository.save(user);
        }
    }
}
