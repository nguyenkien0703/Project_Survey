package com.example.survey_tourist.rest.login_logout_register;

//import com.example.survey_tourist.config.filter.CustomAuthenticationFilter;
//import com.example.survey_tourist.config.jwt.JwtTokenUtil;
import com.example.survey_tourist.dto.JwtRequest;
import com.example.survey_tourist.dto.JwtResponse;
import com.example.survey_tourist.dto.UserDto;
//import com.example.survey_tourist.dto.UserPrincipal;
import com.example.survey_tourist.entity.User;
//import com.example.survey_tourist.service.AuthService;
import com.example.survey_tourist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

//API solve authenticated
@RestController
@RequestMapping("/auth")
@Transactional
public class AuthResource {
    // Injecting the services into the controller.
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody UserDto userDto) throws Exception {
        User user = userService.registerUser(userDto);
    }


    // if logout then delete token of user curernt
    private Set<String> tokenBlacklist = new HashSet<>(); // declare backlist  (blacklist)
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String token = getTokenFromRequest(request); // get token from request
            tokenBlacklist.add(token); // add token in blacklist
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            tokenBlacklist.remove(token);
        }
        return new ResponseEntity<>("You have been logged out successfully.", HttpStatus.OK);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken!= null  && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

//    /**
//     *function get username and password, authenticate user, and return 1 jwt
//     * @param authenticationRequest This is the object that contains the username and password that the user entered.
//     * @return A JWT token
//     */


//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws  Exception{
//        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
//
//        final UserPrincipal userPrincipal =  userService.loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken( userPrincipal);
//        final String nameNeed= jwtTokenUtil.getUsernameFromToken(token);
//        return ResponseEntity.ok(new JwtResponse(token,nameNeed));
//    }
//
//    private  void authenticate(String username,String password) throws  Exception{
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//
//        }catch (DisabledException e){
//            throw  new Exception("User_disable",e);
//        }catch (BadCredentialsException e ){
//            throw new Exception("Invalid_credentials",e);
//        }
//    }



}
