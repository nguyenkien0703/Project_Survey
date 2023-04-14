//package com.example.survey_tourist.service;
//
//import com.example.survey_tourist.dto.UserPrincipal;
//import com.example.survey_tourist.entity.User;
//import com.example.survey_tourist.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository userRepository;
//    /*
//    get user'id from security context
//        @Return the current user's id
//     * */
//    public Integer getCurrentUserId(){
//        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return user;
//    }
//
//
//    /*
//    get user'id, then find user have a id, if it is not exist, return code 404
//    * @return the current user's id
//    * */
//
//
//    public User getCurrentUser(){
//        return  userRepository.findById(getCurrentUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }
//
//
//
//
//
//}
