package com.roommate.snakewatchers.adapter.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    //Handler Methode: User Login REQUEST
    @GetMapping("/login")
    public String loginPage(Model model, @AuthenticationPrincipal OAuth2User oAuth2User){
        model.addAttribute("user", oAuth2User != null ? oAuth2User.getAttribute("login"): null);
        return "login";
    }

//    @GetMapping("/register")
//    public String showRegistrationForm(Model model, RegistrationDto user){
//        model.addAttribute("user", user);
//        return "register";
//    }
}
