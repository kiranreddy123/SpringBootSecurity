package com.SpringJwt.seconfig;

import com.SpringJwt.filters.JwtRequestFilter;
import com.SpringJwt.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtRequestFilter Jwtrequestfilter;
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/authenticate").permitAll()
                 .antMatchers("/h2-console/**").permitAll()
                 .antMatchers(AUTH_WHITELIST).permitAll()
                 .anyRequest().authenticated()
                 .and().sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         http.addFilterBefore(Jwtrequestfilter, UsernamePasswordAuthenticationFilter.class);
         http.headers().frameOptions().disable();
//         http.headers().frameOptions().sameOrigin();


    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }
}
