package com.example.todo_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

//        http.csrf().disable() #marked for removal in latest version
              http.csrf(AbstractHttpConfigurer::disable)
                     .authorizeHttpRequests((authorize) -> {
                          // Authorize POST method when only having admin access
//                          authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//
//                          // Authorize PUT method when only having admin access
//                          authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//
//                          // Authorize DELETE method when only having admin access
//                          authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//
//                          //Both User Admin Roles user can access GET details
//                          authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//
//                          //Both User Admin Roles user can do Partial update using PATCH method.
//                          authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");

//                          // GET request make publicly accessible.
                          //authorize.requestMatchers( "/api/**").permitAll();

                          authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails mohan = User.builder()
//                .username("mohan")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(mohan, admin);
//    }
}
