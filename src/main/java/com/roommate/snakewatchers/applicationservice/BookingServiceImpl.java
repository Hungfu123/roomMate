package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.adapter.web.BookingService;
import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final WorkPlaceRepository workPlaceRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, WorkPlaceRepository workPlaceRepository) {
        this.bookingRepository = bookingRepository;
        this.workPlaceRepository = workPlaceRepository;
    }

    @Override
    public List<BookingDTO> getBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public void addBooking(BookingDTO booking) {
        bookingRepository.save(booking);
    }

    //Überprüfen Sie die Verfügbarkeit jedes Arbeitsplatzes
    @Override
    public List<Boolean> getAvaiableWorkPlaces(BookingDTO booking, List<WorkPlaceDTO> workplaces) {
        List<Boolean> workplaceAvailabilityList = new ArrayList<>();
        for (WorkPlaceDTO workplace : workplaces) {
            boolean isAvailable = isWorkplaceAvailable(workplace.getId(), booking.getDateTimeFrom(), booking.getDateTimeTo());
            workplaceAvailabilityList.add(isAvailable);
        }
        return workplaceAvailabilityList;
    }
    @Override
    public void deleteBookingById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public boolean checkDuplicate(BookingDTO booking) {
        List<BookingDTO> allBooks = bookingRepository.findAll();
        return !allBooks.contains(booking);
    }
    @Override
    public BookingDTO updateEqFromWorkPlace(BookingDTO booking, Set<Equipment> equipments) {
        booking.addEquipment(equipments);


        return bookingRepository.save(booking);
    }

    public boolean isWorkplaceAvailable(Long workplaceId, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        List<BookingDTO> bookings = bookingRepository.findAll();

        for (BookingDTO booking : bookings) {
            // Überprüfen Sie, ob der Arbeitsplatz bereits für den Zeitraum gebucht ist
            if (workplaceId.equals(booking.getWorkplaceId()) && dateTimeFrom.isBefore(booking.getDateTimeTo()) && dateTimeTo.isAfter(booking.getDateTimeFrom())) {
                return false; // Arbeitsplatz ist im angegebenen Zeitraum nicht verfügbar
            }
        }

        return true; // Arbeitsplatz ist verfügbar
    }

    public List<Boolean> getAvaiableWorkPlacesOnly(BookingDTO booking, List<WorkPlaceDTO> workplaces) {
        List<Boolean> workplaceAvailabilityList = new ArrayList<>();
        for (WorkPlaceDTO workplace : workplaces) {
            boolean isAvailable = isWorkplaceAvailable(workplace.getId(), booking.getDateTimeFrom(), booking.getDateTimeTo());
            workplaceAvailabilityList.add(isAvailable);
        }
        return workplaceAvailabilityList;
    }
//    public List<Boolean> getAvaiableWorkPlacesOnlyTest() {
//        List<WorkPlaceDTO> workplaces = workPlaceRepository.findAll().stream().toList();
//
//        List<Boolean> workplaceAvailabilityList = new ArrayList<>();
//        for (WorkPlaceDTO workplace : workplaces) {
//            boolean isAvailable = isWorkplaceAvailable(workplace.getId(), booking.getDateTimeFrom(), booking.getDateTimeTo());
//            workplaceAvailabilityList.add(isAvailable);
//        }
//        return workplaceAvailabilityList;
//    }
}
