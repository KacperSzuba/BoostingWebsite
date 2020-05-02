package com.BoostingWebsite.account.login;

import com.BoostingWebsite.account.user.User;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicUpdate
@Entity
@Table(name = "login_history")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    public LoginHistory() {}

    public LoginHistory(LocalDateTime date) {
        this.date = date;
    }

    public LoginHistory(LocalDateTime date, User user) {
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}