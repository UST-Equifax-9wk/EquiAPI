package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
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

    public User addUser(User user) throws UserAlreadyExistsException{
        if(userRepository.findUserByEmail(user.getEmail()) !=null){
            throw new UserAlreadyExistsException("Email already associated with user. Forgot password?");
        }
        user.setPassword(hash(user.getPassword()));
        return userRepository.save(user);
        //isMatch($Person/EmailAddress ,'^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$')
    }

    public Boolean authenticateUser(User user) throws UserDoesNotExistException{
        User authUser = userRepository.findUserByEmail(user.getEmail());
        if(authUser==null){
            throw new UserDoesNotExistException("This user is not in database");
        }

        if(checkHash(user.getPassword(), authUser.getPassword())){
            return true;
        }
        return false;
    }


    public String hash(String text) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(text, salt);
    }
    public boolean checkHash(String text, String hash) {
//        return text.equals(hash);
        return BCrypt.checkpw(text, hash);
    }


}
