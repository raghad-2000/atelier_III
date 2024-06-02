package com.user.service.appuser.controller;
import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.entity.AppUser;
import com.user.service.appuser.mapper.AppUserEntityToAppUserDTO;
import com.user.service.appuser.mapperImpl.AppUserEntityToAppUserDTOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.user.service.appuser.service.AppUserService;


@RestController
@RequestMapping(value = "/user")
public class AppUserController {


    private final AppUserEntityToAppUserDTOImpl appUserEntityToAppUserDTO = new AppUserEntityToAppUserDTOImpl() ;

    @Autowired
    private AppUserService appUserService;


    //@Autowired
    //private TransactionService transactionService;

    //@Autowired
    //private JwtService jwtService;

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addUser(@RequestBody AppUser appUser) {
        AppUser aU = appUserService.addUser(appUser);
        if (aU != null) {
            return ResponseEntity.ok(appUserEntityToAppUserDTO.appUserEntityToAppUserDTO(aU));
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Duplicate id Key");
    }


    @GetMapping(value = "/infos")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(exposedHeaders = "Authorization", origins = "http://localhost:5173")
    public ResponseEntity<AppUserDto> getUserData(@RequestHeader("user") String username) {
        AppUserDto appUserDto = appUserService.retrieveUserInfos(username);
        return ResponseEntity.ok(appUserDto);
    }

}

