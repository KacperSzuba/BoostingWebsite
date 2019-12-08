package pl.javastart.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="messageSender")
    private User user;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="recipientOfTheMessage")
    private User user2;
    private String title;
    private String message;
    private LocalDateTime date;
    public Message(){ }

    public Message(User user, User user2, String title, String message) {
        this.user = user;
        this.user2 = user2;
        this.title = title;
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }
}