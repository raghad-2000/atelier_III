package com.user.service.appuser.controller;
import com.user.service.appuser.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.user.service.appuser.service.AppUserService;


@RestController
@RequestMapping(value = "/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    //@Autowired
    //private TransactionService transactionService;

    //@Autowired
    //private JwtService jwtService;

    @GetMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
    public AppUser addUser(AppUser appUser) {
        return appUserService.addUser(appUser);
    }

    /**
    @GetMapping(value = "/infos")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(exposedHeaders = "Authorization", origins = "http://localhost:5173")
    public ResponseEntity<AppUserDto> getUserData(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token.substring(7));
        AppUserDto appUserDto = appUserService.retrieveUserInfos(username);
        return ResponseEntity.ok(appUserDto);
    }
    **/

}

