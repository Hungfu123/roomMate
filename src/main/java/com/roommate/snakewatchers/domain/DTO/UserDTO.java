package com.roommate.snakewatchers.domain.DTO;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    Long id;
    String username;
    List<BookingDTO> bookings;


    public void addBooking(BookingDTO booking) {
        bookings.add(booking);
    }
}
