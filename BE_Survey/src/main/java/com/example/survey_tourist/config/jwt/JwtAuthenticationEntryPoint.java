//package com.example.survey_tourist.config.jwt;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.Serializable;
///*
//* this class will extend Spring's AuthenticationEntryPoint class and override its method commence
//* it reject every unauthenticated request and send error code 401
//*
//* */
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
//
//    private static final long serialVersionUID = -7858869558953243875L;// trc và sau khi Serializable là 1
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//    }
//}