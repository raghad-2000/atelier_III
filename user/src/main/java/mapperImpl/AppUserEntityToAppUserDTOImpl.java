package mapperImpl;

import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.entity.AppUser;
import com.user.service.appuser.mapper.AppUserEntityToAppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class AppUserEntityToAppUserDTOImpl implements AppUserEntityToAppUserDTO {

    //@Autowired
    //private CardService cardService;

    @Override
    public AppUserDto appUserEntityToAppUserDTO(AppUser appUser) {
        AppUserDto appUserDto = new AppUserDto();

        appUserDto.setUsername(appUser.getUsername());
        //appUserDto.setMoney(appUser.getMoney());
        //appUserDto.setCards(new ArrayList<>());
        return appUserDto;
    }
}
