package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.repository.UserRepository;

@Service
public class PasswordChange {

    private String message;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void changePassword(Long id,String password,String repeatPassword){
        try {
            tryToChangePassword(id,password,repeatPassword);
        }
        catch (IllegalArgumentException exceptoin){
            setMessage(exceptoin.getMessage());
        }
    }

    private void tryToChangePassword(Long id,String password,String repeatPassword){
        if(isPasswordLengthSufficient(password) && whetherThePasswordsAreTheSame(password,repeatPassword)){
            userRepository.changePasswordQuery(id,passwordEncoder.encode(password));
            setMessage("Your new password is : "+password);
        }
        else {
            throw new IllegalArgumentException("Your password is too short or passwords are different");
        }
    }

    private boolean isPasswordLengthSufficient(String password){
        return password.length() >= 7;
    }

    private boolean whetherThePasswordsAreTheSame(String password,String repeatPassword){
        return password.equals(repeatPassword);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}