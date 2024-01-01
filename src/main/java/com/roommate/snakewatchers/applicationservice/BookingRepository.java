package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface BookingRepository {

    List<BookingDTO> findAll();

    BookingDTO save(BookingDTO booking);

    BookingDTO findById(Long bookingId) throws NoSuchElementException;

    void deleteById(Long bookingId);
}
