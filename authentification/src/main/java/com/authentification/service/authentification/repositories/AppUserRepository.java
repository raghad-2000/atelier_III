package com.authentification.service.authentification.repositories;

import com.authentification.service.authentification.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer>
{
    Optional<AppUser> findByUserName(String username);
}
