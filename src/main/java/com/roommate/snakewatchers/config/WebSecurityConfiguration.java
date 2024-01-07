package com.roommate.snakewatchers.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
//TODO: eigenen UserProfile haben, der dann seine eigene Buchungen sehen kann.
    //TODO: nur Admins haben Zugriff auf alle Buchungen

    @Value("${roommate.rollen.admin}")
    private Set<String> admins;

    @Bean
    public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
        chainBuilder.authorizeHttpRequests(
                        configurer -> configurer
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/admin/**")
                                .hasAnyRole("ADMIN")
                                .requestMatchers("/oauth2/**").permitAll() // Hinzufügen Sie diesen Pfad, wenn nötig
                                .anyRequest().authenticated())
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            // Überprüfen Sie hier die Rollen und leiten Sie entsprechend weiter
                            if (authentication.getAuthorities().stream()
                                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin");
                            } else {
                                response.sendRedirect("/");
                            }
                        })

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
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();
        return (userRequest) -> {
            // Delegate to the default implementation for loading a user
            OidcUser oidcUser = delegate.loadUser(userRequest);

            // Log attributes for debugging
            Map<String, Object> attributes = oidcUser.getAttributes();
            System.out.println("UserProfile Attributes: " + attributes);

            // Extract necessary information, such as login/username
            String login = oidcUser.getAttribute("preferred_username"); // Adjust the attribute name accordingly

            // Check if the user is an admin
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            if (admins.contains(login)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            // Create a new OidcUser with the desired authorities
            OidcUser user = new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo(), "email");

            return user;
        };
    }


}


