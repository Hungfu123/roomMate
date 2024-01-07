package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.applicationservice.BookingRepository;
import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.model.Booking;
import com.roommate.snakewatchers.mapper.BookingMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final DBBookingRepository repository;


    public BookingRepositoryImpl(DBBookingRepository repository) {
        this.repository = repository;
    }
@Override
    public List<BookingDTO> findAll() {
        List<Booking> booking = repository.findAll();

        return booking.stream()
                .map(BookingMapper::mapToBookingDTO)
                .toList();
    }

@Override
    public synchronized BookingDTO save(BookingDTO bookingDTO) {
    Booking booking = repository.save(BookingMapper.mapToBooking(bookingDTO));
    System.out.println("Generated Booking ID:" + booking.getBookingID());
    return BookingMapper.mapToBookingDTO(booking);
    }


@Override
    public BookingDTO findById(Long bookingId) {
    Optional<Booking> bookingOptional = repository.findById(bookingId);
    BookingDTO booking = BookingMapper.mapToBookingDTO(bookingOptional
            .orElseThrow(()-> new NoSuchElementException("Keine Buchung mit der ID" + bookingId +"gefunden")));
    return booking;
    }

    //TODO: Exception Hanlder Methode, der das abfangen kann
    @Override
    public void deleteById(Long bookingId) {
        repository.deleteById(bookingId);
        System.out.println("Booking mit der Id "+bookingId +"ist gelöscht worden" );
    }

    //TODO: Wenn BuchungsDatum über aktuellen Datum ist, dann "vergangene Buchungen"
    // TODO: die nächsten Buchungen
    // TODO: BReadcrumb Menü: die nächste Auswahl wird angezeigt


    @Override
    public List<BookingDTO> findByUserName(String name) {
        List<Booking> bookings = repository.findByUserName(name);
        return bookings.stream()
                .map(BookingMapper::mapToBookingDTO)
                .toList();
    }
}
