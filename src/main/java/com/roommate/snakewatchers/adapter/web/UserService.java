package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {

    UserDTO getUserById(Long userId);

    UserDTO getCurrentUser(OAuth2User oAuth2User);
    UserDTO findByName(String username);
    UserDTO save(UserDTO userDTO);

    UserDTO saveBookingInUser(String username, BookingDTO bookingDTO);
}
