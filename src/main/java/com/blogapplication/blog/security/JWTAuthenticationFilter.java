package com.blogapplication.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JWTtokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(" coming in the  doFlterInternal fn ");
        System.out.println(" Request comes is "+request.toString());
        // token came in the form of Bearer 45Swe567Dfgfwikhek

        String path ="/api/v1/auth/login";
        if(path.equals(request.getRequestURI())){
            filterChain.doFilter(request,response);
            return;
        }


        String requestToken = request.getHeader("Authorization");
        System.out.println(" inital token is "+requestToken);
        String token=null;
        String username="";

        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            System.out.println(" now actual token is "+token);
            try{
                username= this.jwtTokenHelper.getUsernameFromToken(token);
                System.out.println(" username that came is "+username);
            }catch(IllegalArgumentException illegalArgument){
                System.out.println("Received token contains illegal");
            }catch(ExpiredJwtException ew){
                 System.out.println(" token is out of date now ");
            }
            catch(MalformedJwtException e){
                  System.out.println(" Invalid JWT token from the input ");
            }


        }else{
            if(requestToken==null)System.out.println("Request toekn is null");
            else
            System.out.println(" token does not start with Bearer ");
        }

        // Once we get the token -->then validate it
           if( username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
               UserDetails  userDetails = this.userDetailsService.loadUserByUsername(username);

               System.out.println(" userDetails fetched successfully from the   userDetailsService "+ userDetails.getUsername());
               if(this.jwtTokenHelper.validateToken(token,userDetails)==true){
               System.out.println(" token is validated  ");
                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                           new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

               }else{
                System.out.println(" Invalid jwt token ");
               }


           }else{

               System.out.println("  username is null or security context is not null ");

           }

           filterChain.doFilter(request,response);




    }
}
