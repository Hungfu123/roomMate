package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.adapter.web.BookingService;
import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Booking;
import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.model.WorkPlace;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;

        this.userRepository = userRepository;
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


        return booking;
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
    @Override
    public List<BookingDTO> findBookingsByWorkPlaceId(Long workplaceId, BookingDTO bookingDTO) {
        List<BookingDTO> booklist = bookingRepository.findAll();

        return booklist.stream()
                .filter(b -> b.getWorkplaceId().equals(workplaceId))
                .toList();
    }
//TODO: UserProfile erstellen und die Methode ungefähr so anpassen dass UserProfile eine Liste von Bookings hat.
//    public List<BookingDTO> findBookingsByUser() {
//        String username = get();
//        UserProfile createdBy = userRepository.findByUser_Name();
//        Long userId = createdBy.getId();
//        List<BookingDTO> bookingsByUser = bookingRepository.findBookingByUser(userId);
//        return bookingsByUser.stream().toList();
//
//
//    }

    @Override
    public BookingDTO findById(Long bookingId) {

        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<BookingDTO> findByUserName(String name) {
        UserDTO user = userRepository.findbyName(name);
        List<BookingDTO> bookingsByUser = bookingRepository.findByUserName(user.getUsername());

        return bookingsByUser;
    }

    @Override
    public void updateBookingWithRoom(Long roomID, BookingDTO booking) {
        RoomDTO room =roomRepository.findById(roomID);
        booking.setRoomId(roomID);
        booking.setRoomName(room.getRoomName());
    }
}
