package com.roommate.snakewatchers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Set;

@Configuration
public class WebSecurityConfiguration {

    @Value("${roommate.rollen.admin}")
    private Set<String> admins;

    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
        chainBuilder.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/admin/**")
                                .hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .loginProcessingUrl("/login")
                        .permitAll())
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .successHandler((request, response, authentication) -> {
                            // Überprüfen Sie hier die Rollen und leiten Sie entsprechend weiter
                            if (authentication.getAuthorities().stream()
                                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin");
                            } else {
                                response.sendRedirect("/");
                            }
                        })
                        .permitAll()
                        .userInfoEndpoint((
                                info -> info.userService(new AppUserService(admins))
                        )))
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                );


        return chainBuilder.build();
    }


}


