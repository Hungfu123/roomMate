package com.roommate.snakewatchers.mapper;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.model.Booking;
import com.roommate.snakewatchers.domain.model.UserProfile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO mapToUserDTO(UserProfile user) {
        if (user == null) {
            return null;
        }

        List<Booking> bookings = user.getBookings();

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .bookings((bookings != null)
                        ? bookings.stream().map(BookingMapper::mapToBookingDTO).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static UserProfile mapToUser(UserDTO user) {
        if (user == null) {
            return null;
        }

        List<BookingDTO> bookings = user.getBookings();

        return UserProfile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .bookings((bookings != null)
                        ? bookings.stream().map(BookingMapper::mapToBooking).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }
}

