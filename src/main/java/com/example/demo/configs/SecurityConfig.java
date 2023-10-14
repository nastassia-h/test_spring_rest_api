package com.example.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.UserService;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
   private UserService userService;
   private JwtRequestFilter jwtRequestFilter;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      // http
      // .csrf().disable()
      // .cors().disable()
      // .authorizeHttpRequests(authorize -> authorize
      // .requestMatchers("/admin").hasRole("ADMIN")
      // .requestMatchers("/secured").authenticated()
      // .requestMatchers("/info").authenticated()
      // .anyRequest().permitAll())

      // .and()
      // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      // .and()
      // .exceptionHandling()
      // .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      // .addFilterBefore(jwtRequestFilter,
      // UsernamePasswordAuthenticationFilter.class);
      return http.build();
   }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      daoAuthenticationProvider.setUserDetailsService(userService);
      return daoAuthenticationProvider;
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
         throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }
}
