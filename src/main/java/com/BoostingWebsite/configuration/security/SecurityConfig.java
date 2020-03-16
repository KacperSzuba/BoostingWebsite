package com.BoostingWebsite.configuration.security;

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
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("userRepositoryUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/order/informationAboutDivision","/order/informationAboutAccount").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/booster").hasRole("BOOSTER")
                .antMatchers("/account/showChangePasswordPage","/account/changePasswordForm","/account/showEmailChangePage","/account/changeEmailForm").hasAnyRole("USER","ADMIN","BOOSTER")
                .antMatchers("/sendMessage/**").hasAnyRole("ADMIN","USER","BOOSTER")
               // .antMatchers("/sendMessage/send").hasAnyRole("ADMIN","USER","BOOSTER")
                .antMatchers("/").permitAll()
                .antMatchers("/account/updatePassword*","/account/resetPassword").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/css/**","/style/**","/static/**").permitAll() //Adding this line solved it
                .antMatchers("/register").permitAll()
                .antMatchers("/account/remindPasswordPage").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
}
