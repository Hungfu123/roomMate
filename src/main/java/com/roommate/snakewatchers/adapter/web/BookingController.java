package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

import static com.roommate.snakewatchers.adapter.web.Breadcrumb.*;

@Controller
@SessionAttributes("bookingDTO")
public class BookingController {

    private final RoomService roomService;
    private final WorkPlaceService workPlaceService;
    private final BookingService bookingService;

    public BookingController(RoomService roomService, WorkPlaceService workPlaceService, BookingService bookingService) {
        this.roomService = roomService;
        this.workPlaceService = workPlaceService;
        this.bookingService = bookingService;
    }

    @ModelAttribute("bookingDTO")
    public BookingDTO setUpBooking() {
        return new BookingDTO();
    }

    @GetMapping("/")
    public String index(BookingDTO booking, OAuth2AuthenticationToken auth, Model model) {
        booking.setUserName(auth.getPrincipal().getAttribute("login"));
        model.addAttribute("booking", booking);

        return "user/dashboard";
    }

    // Nach Bestätigung vom aktualisierten BookingDTO Objekt wird Success Meldung angezeigt und wir sind auf dashboard
    @PostMapping("/")
    public String confirmBooking(BookingDTO booking, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "Deine Buchung wurde erfolgreich bestätigt!");
        return "redirect:/";
    }

    //ein neues BookingDTO Objekt wird erstellt und wir können unsere Präferenzen auswählen.
    // Equipment Checkboxen werden dort erstellt
    @GetMapping("/booking/search")
    public String selectBookingView(BookingDTO booking,
                                    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeFrom,
                                    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeTo,
                                    Model model) {

        if (bookingService.checkDuplicate(booking)) {
            // Das BookingDTO-Objekt enthält bereits Werte, verwende es im Model
            model.addAttribute("booking", booking);
        } else {
            // Das BookingDTO-Objekt ist neu, füge ein leeres BookingDTO-Objekt zum Model hinzu
            model.addAttribute("booking", new BookingDTO());
        }

        model.addAttribute("equipments", Equipment.values());
        breadcrumbTime(model);
        return "user/booking_selection";
    }

    // Räume werden gefiltert angezeigt, die Arbeitsplätze haben die, die selektierten Equipments haben
    @GetMapping("/booking/search/overview/room")
    public String overviewSelectedRooms(@Valid BookingDTO bookingDTO, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("equipments", Equipment.values());
            return "user/booking_selection";
        }
        if (bookingDTO.getEquipments() == null) {
            model.addAttribute("rooms", roomService.getRooms());
        } else {
            model.addAttribute("rooms", roomService.getFilteredRoomsWithWorkPlaces(bookingDTO.getEquipments()));
        }
        breadcrumbRoom(model);
        return "user/booking_room_overview";
    }

    // Arbeitsplätze werden angezeigt, und das BookingDTO Objekt wird mit dem ausgewählten Raum aktualisiert (RoomDTO Name und RoomDTO Id)
    @GetMapping("/booking/search/overview/room/workplace")
    public String overviewWorkplaces(@RequestParam Long roomID, BookingDTO booking, Model model) {
        booking.setRoomId(roomID);
        RoomDTO room = roomService.getRoom(roomID);
        booking.setRoomName(room.getRoomName());
        List<WorkPlaceDTO> workplaces;

        if (booking.getEquipments() == null) {
            workplaces = workPlaceService.getAllWorkPlaces();
        } else {
            workplaces = roomService.findFilteredWorkPlaces(booking.getEquipments(), roomID);
        }
        List<Boolean> workplaceAvailabilityList = bookingService.getAvaiableWorkPlaces(booking, workplaces);

        model.addAttribute("workplaces", workplaces);
        model.addAttribute("workplaceAvailabilityList", workplaceAvailabilityList);

        breadcrumb(model);


        return "user/booking_workplace_overview";
    }

    // Buchung-Objekt aktualisiert mit workplace Id und Equipment Set
    @GetMapping("/booking/search/overview/room/workplace/summary")
    public String selectWorkPlaceBooking(@RequestParam Long workplaceId, BookingDTO booking, Model model, OAuth2AuthenticationToken auth) {
        booking.setUserName(auth.getPrincipal().getAttribute("login"));
        booking.setWorkplaceId(workplaceId);

        WorkPlaceDTO workPlace = workPlaceService.getWorkPlaceById(workplaceId);
        bookingService.updateEqFromWorkPlace(booking ,workPlace.getEquipments());


        breadcrumbSummary(model);
//        System.out.println(booking);
        return "user/booking_overview_summary";
    }


    // Liste von Bookings werden auf der Reservierung-Seite angezeigt
    @GetMapping("/booking/mybookings")
    public String viewBookingUser(Model model) {
        List<BookingDTO> bookings = bookingService.getBookings();
        model.addAttribute("bookings", bookings);
        System.out.println(bookings);

        return "user/booking_overview-board";
    }

    // BookingDTO Objekt wird gelöscht aus der BookingDTO Liste
    @PostMapping("/booking/mybookings/delete")
    public String deleteBooking(@RequestParam Long bookingId, Model model) {
        bookingService.deleteBookingById(bookingId);
        System.out.println(bookingService.getBookings());
        return "redirect:/booking/mybookings";
    }
//TODO:roomId von summary Page zu WorkPlaces Overview anzeigen
// TESTS Schreiben

}




