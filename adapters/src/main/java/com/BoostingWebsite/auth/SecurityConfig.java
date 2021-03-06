package com.BoostingWebsite.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    SecurityConfig(@Qualifier("userDetailsBusiness") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/account/change/password",
                        "/account/change/password/form",
                        "/account/change/email",
                        "/account/change/email/form",
                        "/message/**").hasAnyRole("USER", "ADMIN", "BOOSTER")
                .antMatchers("/account/updatePassword*",
                        "/account/reset/password").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/",
                        "/register",
                        "/account/remindPasswordPage",
                        "/css/**", "/style/**", "/static/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
}
