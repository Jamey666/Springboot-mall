package com.example.ecommerce_project.Service.User;

import com.example.ecommerce_project.Dao.User.UserDao;
import com.example.ecommerce_project.dto_DataTransferObject.RequestUser;
import com.example.ecommerce_project.modle.User;
import jdk.jshell.spi.ExecutionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;



@Component
public class UserServiceImp implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Integer CreateUser(RequestUser requestUser) {
        if (userDao.getUserByEmail(requestUser.getEmail()) != null) {
            log.warn("Email Already Exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }else {
            requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
            return userDao.CreateUser(requestUser);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User updateUser(Integer id,String email,String password,RequestUser requestUser) {
        User user = userDao.getUserById(id);
        if (user != null) {
            if(user.getEmail().equals(email)) {
                if(passwordEncoder.matches(password,user.getPassword())) {
                    requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
                    return userDao.updateUser(id,requestUser);
                }else  {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "此email的密碼輸入錯誤><");
                }
            }else  {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "此id的email輸入錯誤!");
            }

        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "此Id不存在User~");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User UserLogin(RequestUser requestUser) {
        User user = userDao.getUserByEmail(requestUser.getEmail());
        if (user != null) {
            if(passwordEncoder.matches(requestUser.getPassword(), user.getPassword())) {
                return user;
            }else {
                log.warn("{}密碼不正確",requestUser.getEmail());
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"密碼錯誤TAT");
            }
        }else {
            log.warn("{}未被註冊過",requestUser.getEmail());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "此email未註冊過帳號><");
        }
    }
}