package com.ironhack.projectoRocioArellano.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //En este metodo principal vamos a incluir las URLs de nuestro sistema, y dar permisos a nuestros tipos de usuarios
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/accounts").authenticated()
                .antMatchers(HttpMethod.GET, "/myAccounts").authenticated()
                .antMatchers(HttpMethod.GET, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/myAccounts/**").hasRole("ACCOUNTHOLDER")
                .antMatchers(HttpMethod.POST, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/accounts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/myAccounts/**").hasRole("ACCOUNTHOLDER")
                .anyRequest().permitAll();
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
