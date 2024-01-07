package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.model.Booking;

import java.util.List;

public interface UserRepository {

    List<UserDTO> findAll();

    UserDTO findbyId(Long userId);
    UserDTO findbyName(String username);


    UserDTO save(UserDTO newUser);
}
