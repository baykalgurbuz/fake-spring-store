package com.spring.fakestore.fakestore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.fakestore.fakestore.models.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter  extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Gelen Tokenın Header Kısmı
        String token = request.getHeader("Authorization");
        if (token != null)
        {
            try
            {
                String mytoken = JWT.require(Algorithm.HMAC512("MY_SECRET_KEY".getBytes())).build().verify(token.replace("Bearer ", "")).getSubject();
                if (mytoken != null)
                {
                    // user-USER
                    // admin-ADMIN
                    String username = mytoken.split("-")[0];
                    List<String> roleNames = Arrays.asList(mytoken.split("-")[1].split(","));
                    Users user = new Users();
                    user.setRole("ROLE_" + roleNames.get(0)); // assuming you want the first role
                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                }
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Token exception => " + e.getMessage());
            }
        }
        else
        {
            System.err.println("Token yok ama header gelmiş");
            // token yok, nasıl olsa security config 'den patlayacağım
            filterChain.doFilter(request, response);
        }
    }

}
