//package com.example.survey_tourist.dto;
//
//import com.example.survey_tourist.entity.Role;
//import com.example.survey_tourist.entity.User;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
///*
//* Nó triển khai giao diện UserDetails, được Spring security sử dụng để đại diện cho người dùng
//* */
//public class UserPrincipal implements UserDetails {
//
//    User  user ;
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        Role role = user.getRole();
//        String roleName = role.getNameRole();
//        authorities.add(new SimpleGrantedAuthority(roleName));
//        return authorities;
//    }
//
//
//    @Override
//    public String getPassword() {
//        return  user.getPasswordAccount();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUserName();
//    }
//    public Integer getUserId(){
//        return user.getId();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true ;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
