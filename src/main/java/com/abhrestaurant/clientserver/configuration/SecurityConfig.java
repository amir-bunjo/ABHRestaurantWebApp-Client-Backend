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
        web.ignoring().antMatchers("/api//table/restaurant/seats/{restaurantId}/{seats}/{dateString}/{timeString}");    }

    /*
            @Autowired
            public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

                List<User> listOfUser = userRepository.findAll();
                for(User user: listOfUser) {
                    System.out.println(user.getEmail());
                    auth.inMemoryAuthentication().withUser(user.getEmail()).password("{noop}"+user.getPassword()).roles("USER");
                }
            }


            @Bean
            @Override
            protected UserDetailsService userDetailsService() {
               // return super.userDetailsService();
                List<UserDetails> users = new ArrayList<>();
                users.add(User.withDefaultPasswordEncoder().username("amir").password("1234").roles("USER").build());
                users.add(User.withDefaultPasswordEncoder().username("ar").password("saas").roles("USER").build());


                return new InMemoryUserDetailsManager(users);
            }
         */
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