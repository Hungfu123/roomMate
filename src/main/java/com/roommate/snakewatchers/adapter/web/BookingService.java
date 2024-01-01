package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;

import java.util.List;
import java.util.Set;


public interface BookingService {

    List<BookingDTO> getBookings();

    void addBooking(BookingDTO booking);

    List<Boolean> getAvaiableWorkPlaces(BookingDTO booking, List<WorkPlaceDTO> workplaces);

    void deleteBookingById(Long bookingId);

    boolean checkDuplicate(BookingDTO booking);

    BookingDTO updateEqFromWorkPlace(BookingDTO booking, Set<Equipment> equipments);
}
