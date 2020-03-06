package com.BoostingWebsite.account.password.reset;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.exceptions.DataMismatchException;

@Service
public class PasswordManager {

    private String message;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ActualUser actualUser;

    public PasswordManager(PasswordEncoder passwordEncoder, UserRepository userRepository, ActualUser actualUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.actualUser = actualUser;
    }

    public void changePassword(String currentPassword,String password, String repeatPassword){
        try {
            whetherPasswordCanBeChanged(currentPassword,password,repeatPassword);
        }
        catch (DataMismatchException exception){
            setMessage(exception.getMessage());
        }
    }

    private void whetherPasswordCanBeChanged(String currentPassword,String password,String repeatPassword) throws DataMismatchException {
        if(checkIfPasswordEnteredMatchesCurrent(currentPassword) && checkIfPasswordsAreTheSameAndHaveRequiredLength(password,repeatPassword)){
            tryToChangePassword(password);
        }
    }
    
    private boolean checkIfPasswordsAreTheSameAndHaveRequiredLength(String password, String repeatPassword) throws DataMismatchException {
        if(isPasswordLengthSufficient(password) && whetherThePasswordsAreTheSame(password,repeatPassword)) {
            return true;
        }
        else {
            throw new DataMismatchException("Your password is too short or passwords are different");
        }
    }

    private void tryToChangePassword(String password){
        userRepository.changePassword(loggedInUser().getId(),passwordEncoder.encode(password));
        setMessage("Your new password is : "+password);
    }

    private User loggedInUser(){
        return actualUser.getActualUser();
    }

    private boolean checkIfPasswordEnteredMatchesCurrent(String currentPassword) throws DataMismatchException {
        if(passwordEncoder.matches(currentPassword, loggedInUser().getPassword())){
            return true;
        }
        else {
            throw new DataMismatchException("The password you entered does not match the current one");
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