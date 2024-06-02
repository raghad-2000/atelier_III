package com.authentification.service.authentification.repositories;

import com.authentification.service.authentification.entities.LogUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<LogUser, Integer>
{
    Optional<LogUser> findByUserName(String username);
}
