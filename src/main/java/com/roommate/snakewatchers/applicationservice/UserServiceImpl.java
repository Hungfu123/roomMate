package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.adapter.web.BookingService;
import com.roommate.snakewatchers.adapter.web.UserService;
import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BookingService bookingService;

    public UserServiceImpl(UserRepository userRepository, BookingService bookingService) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findbyId(userId);
    }

    @Override
    public UserDTO findByName(String username) {
        return userRepository.findbyName(username);
    }



    @Override
    public UserDTO getCurrentUser(OAuth2User oAuth2User) {
        // Extract necessary information from OAuth2User

        String login = oAuth2User.getAttribute("login");
        if(login == null) {
            login =   oAuth2User.getAttribute("email");
        }
        String name = oAuth2User.getAttribute("name");
        // You may need to adapt attribute names based on your OAuth provider

        // Check if the user already exists in your database
        UserDTO existingUser = userRepository.findbyName(login);

        // If the user does not exist, create a new user in the database
        if (existingUser == null) {
            UserDTO newUser = new UserDTO();
            newUser.setUsername(login);
            // Set other attributes as needed
            // Save the new user to the database
            userRepository.save(newUser);
            return newUser;
        } else {
            // If the user already exists, return the existing user
            return existingUser;
        }
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public UserDTO saveBookingInUser(String username, BookingDTO bookingDTO) {
        UserDTO userDTO = findByName(username);
        userDTO.addBooking(bookingDTO);
        save(userDTO);

        return userDTO;
    }
}
