package com.crud_example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    UserDetailsService userDetailsService;
    JwtService jwtService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");


        if(header!=null  && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (jwtService.validatetoken(token)){





                String username =jwtService.getUsername(token);

             UserDetails userDetails=   userDetailsService.loadUserByUsername(username);
             if(SecurityContextHolder.getContext().getAuthentication()==null){


                 UsernamePasswordAuthenticationToken authenticationToken = new
                         UsernamePasswordAuthenticationToken(
                                 userDetails,
                         null,
                         userDetails.getAuthorities());
                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);

             }


            }



        }

        filterChain.doFilter(request,response);
    }
}
