package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.domain.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBBookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();


}
