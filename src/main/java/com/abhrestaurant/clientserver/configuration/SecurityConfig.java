package com.abhrestaurant.clientserver.configuration;

import com.abhrestaurant.clientserver.model.User;
import com.abhrestaurant.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()
                .and().httpBasic();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/create/user").antMatchers();
        web.ignoring().antMatchers("/api/restaurants/{startindex}");
        web.ignoring().antMatchers("/api/restaurant/{id}");
        web.ignoring().antMatchers("/api/restaurant/matchpatern/{patern}");
        web.ignoring().antMatchers("/api/reservations/bydate/{dateString}/byguest/{guestNumber}/bytime/{timeString}/{restaurantId}");
        web.ignoring().antMatchers("/api/reservation/available/count/{restaurantId}/{dateString}");
        web.ignoring().antMatchers("/api//table/restaurant/seats/{restaurantId}/{seats}/{dateString}/{timeString}");
        web.ignoring().antMatchers("/api/restaurant/avalable/{name}/{seats}/{dateString}/{timeString}");
        web.ignoring().antMatchers("/api/restaurant/matchpatern/{patern}/{mark}/{price}/{page}");
        web.ignoring().antMatchers("/api/restaurant/count/matchpatern/{patern}/{mark}/{price}/{page}");
        web.ignoring().antMatchers("/api/reviews/{restaurantId}/{username}");
        web.ignoring().antMatchers("/api/get/allemails");






    }

    @Autowired
    private  UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

}