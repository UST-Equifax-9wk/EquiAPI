package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Service
@EnableTransactionManagement
@jakarta.transaction.Transactional(Transactional.TxType.REQUIRED)
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User AddUser(User user){
        return userRepository.save(user);
        //isMatch($Person/EmailAddress ,'^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$')
    }


}
