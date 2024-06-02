package com.saga.orchestror.service.saga.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppUserDto {
    private Long id;
    private String username;

    // Getter pour id
    public Long getId() {
        return id;
    }

    // Setter pour id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter pour username
    public String getUsername() {
        return username;
    }

    // Setter pour username
    public void setUsername(String username) {
        this.username = username;
    }
}
