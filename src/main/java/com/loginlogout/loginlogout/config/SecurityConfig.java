package com.loginlogout.loginlogout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/security/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .defaultSuccessUrl("")
                        .failureUrl("/security/login")
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutUrl("/security/logout"));
        return http.build();
    }


}
