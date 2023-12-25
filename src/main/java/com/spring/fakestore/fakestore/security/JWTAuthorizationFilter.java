package com.spring.fakestore.fakestore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.fakestore.fakestore.models.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
                    List<Role> auth = Arrays.asList(mytoken.split("-")[1].split(",")).stream().map(item -> new Role(item)).collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, auth);
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
