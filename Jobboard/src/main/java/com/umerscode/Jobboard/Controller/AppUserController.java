package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Service.AppUserService;
import com.umerscode.Jobboard.Service.AppUserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class AppUserController {

    @Autowired
    private final AppUserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<AppUser>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser newUSer){
        return new ResponseEntity<>(userService.createUser(newUSer), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser updatedUSer){
        return new ResponseEntity<>(userService.updateUser(updatedUSer), HttpStatus.OK);
    }
}
