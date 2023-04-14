package com.example.survey_tourist.config;



import com.example.survey_tourist.config.filter.CustomAuthenticationFilter;
import com.example.survey_tourist.config.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)



public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        AppConfig appConfig = new AppConfig();// tao ra doi tuong passwordEncoder

        auth.userDetailsService(userDetailsService).passwordEncoder(appConfig.passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter=  new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        httpSecurity.cors();
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(STATELESS);
        httpSecurity.authorizeRequests().antMatchers("/auth/login", "/auth/register","/role/**","/getSurveyUsers/**","/survey-user/**","/answer-survey/**" ,"/users/**","/auth/logout").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/question-type/**","/createSurvey/**","/surveys/**","/survey/**","/getSurveys","/question-survey/**","/questionType/**","/roles/**","/department/**","/option-question-survey/**","/employee/**").hasAnyAuthority("ROLE_ADMIN");
        httpSecurity.authorizeRequests().antMatchers().hasAnyAuthority("ROLE_USER");
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.addFilter(customAuthenticationFilter);
        httpSecurity.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }



}