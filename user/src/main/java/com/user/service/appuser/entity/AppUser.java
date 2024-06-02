package com.user.service.appuser.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class AppUser implements UserDetails {

    @Id
    @Column(nullable = false)
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private long id;

    @Column(unique = true, length = 100, nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private float money;

    /**
    @OneToMany(mappedBy = "appUserId")
    private List<CardAssociation> cards;
    **/
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    public AppUser() {
        this.id = 0;
        this.password = "";
        this.money = 4000;
    }
    // todo: refacto pour sécuriser le password
    public AppUser(String username, String password) {
        this.userName = username;
        this.password = password;
        this.money = 4000;
    }

    public AppUser(String username, String password, float money) {
        this.userName = username;
        this.password = password;
        this.money = 4000;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
