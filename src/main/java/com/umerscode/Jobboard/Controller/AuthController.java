package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Jwt.JwtAuth;
import com.umerscode.Jobboard.Jwt.JwtService;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import com.umerscode.Jobboard.Repository.EmployeeRepo;
import com.umerscode.Jobboard.Dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final JwtAuth jwtAuth;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getEmail(), request.getPassword()));
        if (!authenticate.isAuthenticated()) {
            System.out.println("Print......");
        }
        AppUser activeUser = userRepo.findUserByEmail(request.getEmail()).get();
        UserDetails activeUserDetail = userRepo.findUserByEmail(request.getEmail()).get();

//        AppUser activeUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final String jwtToken = jwtService.generateJwt(activeUser);
        jwtAuth.setJwt(jwtToken);
        jwtAuth.setMessage("Login success");
        System.out.println("JWt => " + jwtToken);
        return ResponseEntity.ok().body(jwtAuth);

        //TODO- get rid of commented section
//
//        if(activeUser.getRole().name().equals("EMPLOYEE")) {
//        activeUserDetail.getAuthorities().forEach(System.out::println);
//            return ResponseEntity.ok().body(employeeRepo.findByEmail(activeUser.getEmail()));
//        }
//        activeUserDetail.getAuthorities().forEach(System.out::println);
//        return ResponseEntity.ok().body(companyRepo.findByEmail(activeUser.getEmail()));
    }


//    @GetMapping("/user/profile")
//    public ResponseEntity<?> redirectingUserAfterAuthentication(){
//
//       AppUser activeUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        final String jwtToken = jwtService.generateJwt(activeUser);
//        System.out.println("JWt => " + jwtToken);
//       if(activeUser.getRole().name().equals("EMPLOYEE")) {
//           return ResponseEntity.ok().body(employeeRepo.findByEmail(activeUser.getEmail()));
//       }
//       return ResponseEntity.ok().body(companyRepo.findByEmail(activeUser.getEmail()));
//    }
}
