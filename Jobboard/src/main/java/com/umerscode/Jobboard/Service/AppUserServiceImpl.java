package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{
//        , UserDetailsService {

    @Autowired
   private final AppUserRepo userRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Transactional
    @Override
    public AppUser updateUser(AppUser user) {
        AppUser existUser = userRepo.findById(user.getId()).get();

        existUser.setEmail(user.getUsername());
        existUser.setPassword(user.getPassword());
        existUser.setRole(user.getRole());

        return existUser;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        AppUser user = userRepo.findUserByEmail(username).get();
//
//        if(user == null){
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
//
//        return new User(user.getUsername(),user.getPassword(),authorities);
//    }
}
