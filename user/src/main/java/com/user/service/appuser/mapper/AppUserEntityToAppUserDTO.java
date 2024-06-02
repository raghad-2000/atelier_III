package com.user.service.appuser.mapper;

import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.entity.AppUser;

public interface AppUserEntityToAppUserDTO {
    AppUserDto appUserEntityToAppUserDTO(AppUser appUser);

}
