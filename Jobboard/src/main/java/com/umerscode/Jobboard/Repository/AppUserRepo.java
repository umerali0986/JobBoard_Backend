package com.umerscode.Jobboard.Repository;

import com.umerscode.Jobboard.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findUserByEmail(String email);

}
