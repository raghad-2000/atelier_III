package com.user.service.appuser.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
    private Long id;
    private String username;
    private float money;
    //private List<CardAssociationDTO> cards;
}
