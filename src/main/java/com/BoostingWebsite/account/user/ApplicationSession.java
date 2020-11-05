package com.BoostingWebsite.account.user;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Component
public class ApplicationSession {

    private final UserRepository userRepository;
    private final HttpServletRequest request;

    public ApplicationSession(UserRepository userRepository, HttpServletRequest request) {
        this.userRepository = userRepository;
        this.request = request;
    }

    public User getActualUser() {
        Principal principal = this.request.getUserPrincipal();
        return userRepository.findByUsername(principal.getName());
    }
}