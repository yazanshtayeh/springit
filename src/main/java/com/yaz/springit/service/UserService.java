package com.yaz.springit.service;

import com.yaz.springit.domain.User;
import com.yaz.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final RoleService roleService;
    private final Logger logger= LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
     private final BCryptPasswordEncoder encoder;
     private final MailService mailService;
    public UserService(RoleService roleService, UserRepository userRepository, MailService mailService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.mailService = mailService;
        encoder=new BCryptPasswordEncoder();
    }

    public User Register(User user){
        //encrypt the password
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        //confirm password
        user.setConfirmPassword(secret);
        //assign role
        user.addRole(roleService.findByName("ROLE_USER"));
        //send activation code
        user.setActivationCode(UUID.randomUUID().toString());
        //disable the user
        user.setEnabled(false);
        //save the user
        Save(user);
        //send activation code
        sendActivationEmail(user);
        //return the user
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
    public void sendActivationEmail(User user){
        mailService.sendActivationEmail(user);
    }
    public void sendWelcomeEmail(User user){
        mailService.sendWelcomeEmail(user);
    }

    public Optional<User> findByEmailAndActivationCode(String email,String activationCode){
        return userRepository.findByEmailAndActivationCode(email,activationCode);
    }
}
