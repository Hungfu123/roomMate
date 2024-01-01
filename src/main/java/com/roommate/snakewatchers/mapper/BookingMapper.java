package com.roommate.snakewatchers.mapper;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.model.Booking;

public class BookingMapper {

    public static BookingDTO mapToBookingDTO(Booking booking) {
        return BookingDTO.builder()
                .bookingID(booking.getBookingID())
                .workplaceId(booking.getWorkplaceId())
                .roomId(booking.getRoomId())
                .roomName(booking.getRoomName())
//                .userId(booking.getUserId())
                .dateTimeFrom(booking.getDateTimeFrom())
                .dateTimeTo(booking.getDateTimeTo())
                .userName(booking.getUserName())
                .equipments(booking.getEquipments())
                .build();

    }

    public static Booking mapToBooking(BookingDTO booking) {
        return Booking.builder()
                .bookingID(booking.getBookingID())
                .workplaceId(booking.getWorkplaceId())
                .roomId(booking.getRoomId())
                .roomName(booking.getRoomName())
//                .userId(booking.getUserId())
                .dateTimeFrom(booking.getDateTimeFrom())
                .dateTimeTo(booking.getDateTimeTo())
                .userName(booking.getUserName())
                .equipments(booking.getEquipments())
                .build();

    }


}
