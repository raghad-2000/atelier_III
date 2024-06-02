package com.user.service.appuser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @Column(nullable = false)
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private long id;

    @Column(unique = true, length = 100, nullable = false)
    private String username;

    @Column(nullable = false)
    private float money;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    public AppUser() {
        this.id = 0;
        this.money = 4000;
    }

    public AppUser(String username) {
        this.username = username;
        this.money = 4000;
    }

    public AppUser(String username, float money) {
        this.username = username;
        this.money = money;
    }
}
