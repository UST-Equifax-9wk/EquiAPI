package com.revature.ecommerce.services;

import com.revature.ecommerce.dtos.NewUserDto;
import com.revature.ecommerce.entities.Auth;
import com.revature.ecommerce.exceptions.UserAlreadyExistsException;
import com.revature.ecommerce.exceptions.UserDoesNotExistException;
import com.revature.ecommerce.repositories.AuthRepository;
import com.revature.ecommerce.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class AuthService {
    private AuthRepository authRepository;
    private UserRepository userRepository;

    public AuthService(AuthRepository authRepository, UserRepository userRepository){
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    public Auth createNewAuthentication(NewUserDto newUserDto) throws UserAlreadyExistsException{
        if(userRepository.findUserByEmail(newUserDto.getUser().getEmail()) != null){
            throw new UserAlreadyExistsException("Email already exists, forgot password?");
        }
        newUserDto.getAuth().setPassword(hash(newUserDto.getAuth().getPassword()));
        userRepository.save(newUserDto.getUser());
        //This should invalidate the saving of new users through any other method
        //The user creation may be used differently maybe?
        return authRepository.save(newUserDto.getAuth());
    }

    public Boolean authenticateUser(Auth auth) throws UserDoesNotExistException {
        Boolean valid = false;
        if(userRepository.findUserByEmail(auth.getEmail()) == null){
            throw new UserDoesNotExistException ("Email does not exist on database"); //hacks!!!
        }
        if(checkHash(auth.getPassword(), authRepository.findPasswordByEmail(auth.getEmail()))){
            return true;
        }
        return valid;
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
