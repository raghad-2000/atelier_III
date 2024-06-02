package com.user.service.appuser.service;

import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.entity.AppUser;
import com.user.service.appuser.repository.AppUserRepository;
import com.user.service.appuser.mapperImpl.AppUserEntityToAppUserDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    private final AppUserEntityToAppUserDTOImpl appUserEntityToAppUserDTO = new AppUserEntityToAppUserDTOImpl();

    //private final CardEntityToCardDTOImpl cardEntityToCardDTO  = new CardEntityToCardDTOImpl();;

    public AppUser addUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUserDto retrieveUserInfos(String username) {
        Optional<AppUser> user;
        user = appUserRepository.findByUsername(username);
        if (user.isPresent()) {
            AppUserDto appUserDto = appUserEntityToAppUserDTO.appUserEntityToAppUserDTO(user.get());
            /**
            for(CardAssociation cardAssociation: appUser.get().getCards()) {
                appUserDto.getCards().add(
                        new CardAssociationDTO(cardEntityToCardDTO.cardEntityToCardDTO(cardAssociation.getCard()), cardAssociation.getQuantity())
                );
            }
             **/
            return appUserDto;
        } else
            return null;
    }
    public Optional<AppUser> findUserByUserName(String username) {
        return appUserRepository
                .findByUsername(username);
    }
}
