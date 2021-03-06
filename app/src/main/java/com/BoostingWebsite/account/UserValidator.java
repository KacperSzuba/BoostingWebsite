package com.BoostingWebsite.account;

import org.springframework.stereotype.Component;

@Component
class UserValidator {
    private final UserQueryRepository userQueryRepository;

    UserValidator(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    boolean canCreateAccount(User user, String confirmPassword) {
        return checkIfEmailNotExist(user) && checkIfUserNotExists(user) && checkIfPasswordsAreTheSame(user, confirmPassword);
    }

    private boolean checkIfUserNotExists(User user) {
        boolean isUserExist = userQueryRepository.existsUserByUsername(user.getUsername());
        if (isUserExist) {
            throw new IllegalArgumentException("User with this username already exist");
        } else {
            return true;
        }
    }

    private boolean checkIfEmailNotExist(User user) {
        boolean isEmailExist = userQueryRepository.existsUserByEmail(user.getEmail());
        if (isEmailExist) {
            throw new IllegalArgumentException("User with this email already exist");
        } else {
            return true;
        }
    }

    private boolean checkIfPasswordsAreTheSame(User user, String confirmPassword) {
        boolean isPasswordsAreTheSame = user.getPassword().equals(confirmPassword);
        if (isPasswordsAreTheSame) {
            return true;
        }

        throw new IllegalArgumentException("Passwords don't match");
    }
}