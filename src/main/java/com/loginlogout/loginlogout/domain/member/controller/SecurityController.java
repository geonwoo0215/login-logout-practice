package com.loginlogout.loginlogout.domain.member.controller;

import com.loginlogout.loginlogout.domain.member.service.PrincipalDetailsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    private final PrincipalDetailsService principalDetailsService;

    public SecurityController(PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
    }

    
}
