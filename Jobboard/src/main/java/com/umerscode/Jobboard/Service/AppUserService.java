package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.AppUser;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AppUserService {

    List<AppUser> getUsers();
    AppUser createUser(AppUser user);
    AppUser updateUser(AppUser user);
}
