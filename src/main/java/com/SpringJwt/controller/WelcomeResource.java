package com.SpringJwt.controller;

import com.SpringJwt.models.AuthenticationRequest;
import com.SpringJwt.models.AuthenticationResponse;
import com.SpringJwt.service.MyUserDetailsService;
import com.SpringJwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;
    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security Jwt";
    }

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
           );
       }catch (BadCredentialsException ex){
           throw new Exception("Incorrect username or password",ex);
       }
       final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       final String jwt= jwtUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
