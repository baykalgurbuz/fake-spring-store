//package com.spring.fakestore.fakestore.config;
//
//import com.spring.fakestore.fakestore.security.JWTAuthenticationFilter;
//import com.spring.fakestore.fakestore.security.JWTAuthorizationFilter;
//import jakarta.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.io.IOException;
//import java.util.List;
//
//@Configuration
//public class SecurityConfig  {
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Autowired AuthenticationConfiguration authenticationConfiguration) throws Exception{
//        http.authorizeHttpRequests(x->x
//                .requestMatchers(HttpMethod.GET).hasRole("ADMIN")
//               .requestMatchers("/admin/**").hasRole("ADMIN")
//               .anyRequest().authenticated());
//        http.csrf(customizer -> customizer.disable());
//
//
////        http.exceptionHandling(x->x.authenticationEntryPoint(unauthorizedHandler));
//
//        http.sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
//        http.addFilterAfter(new JWTAuthorizationFilter(),JWTAuthenticationFilter.class);
//
////        CorsConfigurationSource corsconsfigurationsource = new CorsConfigurationSource()
////        {
////            @Override
////            public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
////            {
////                CorsConfiguration config = new CorsConfiguration();
////                config.setAllowedHeaders(List.of(""));
////                config.setAllowedOrigins(List.of(""));
////                config.setAllowedMethods(List.of("*"));
////                return config;
////            }
////        };
////        http.cors(customizer -> customizer.configurationSource(corsconsfigurationsource));
//
//
//        return http.build();
//    }
//
//}
