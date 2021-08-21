package com.maeinghome.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login() {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("", ""));
        Object principal = authenticate.getPrincipal();
        return "112233" + principal.toString();
    }

    @GetMapping("/role/admin")
    @PreAuthorize("hasRole('admin')")
    public String roleAdmin() {
        return "hasPermission";
    }

    @GetMapping("/role/user")
    @PreAuthorize("hasRole('user')")
    public String roleUser() {
        return "hasPermission";
    }

    @GetMapping("/permission/write")
    @PreAuthorize("hasAuthority('write')")
    public String permissionWrite() {
        return "hasPermission";
    }

    @GetMapping("/permission/read")
    @PreAuthorize("hasAuthority('read')")
    public String permissionRead() {
        return "hasPermission";
    }
}
