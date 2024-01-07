package com.roommate.snakewatchers.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfile {
    @Id
    private Long id;
    private String username;
    private List<Booking> bookings;
}
