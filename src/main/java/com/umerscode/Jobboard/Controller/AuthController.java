package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Jwt.JwtService;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import com.umerscode.Jobboard.Repository.EmployeeRepo;
import com.umerscode.Jobboard.Dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final AppUserRepo userRepo;
    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;
    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("YEAAAAAH!");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getEmail(), request.getPassword()));
        AppUser activeUser = userRepo.findUserByEmail(request.getEmail()).get();
        UserDetails activeUserDetail = userRepo.findUserByEmail(request.getEmail()).get();

        if(activeUser.getRole().name().equals("EMPLOYEE")) {
        activeUserDetail.getAuthorities().forEach(System.out::println);
            return ResponseEntity.ok().body(employeeRepo.findByEmail(activeUser.getEmail()));
        }
        activeUserDetail.getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok().body(companyRepo.findByEmail(activeUser.getEmail()));
    }


    @GetMapping("/user/profile")
    public ResponseEntity<?> redirectingUserAfterAuthentication(){

       AppUser activeUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String jwtToken = jwtService.generateJwt(activeUser);
        System.out.println("JWt => " + jwtToken);
       if(activeUser.getRole().name().equals("EMPLOYEE")) {
           return ResponseEntity.ok().body(employeeRepo.findByEmail(activeUser.getEmail()));
       }
       return ResponseEntity.ok().body(companyRepo.findByEmail(activeUser.getEmail()));
    }
}
