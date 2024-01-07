package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
public class UserBookingController {
    private final RoomService roomService;
    private final WorkPlaceService workPlaceService;
    private final BookingService bookingService;
    private final UserService userService;

    public UserBookingController(RoomService roomService, WorkPlaceService workPlaceService, BookingService bookingService, UserService userService) {
        this.roomService = roomService;
        this.workPlaceService = workPlaceService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @ModelAttribute("bookingDTO")
    public BookingDTO setUpBooking() {
        return new BookingDTO();
    }

    @GetMapping("/")
    public String index(BookingDTO bookingDTO, Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO user = userService.getCurrentUser(oAuth2User);

        bookingDTO.setUserName(user.getUsername());
        model.addAttribute("username", user.getUsername());

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
        bookingService.updateBookingWithRoom(roomID, booking);

        List<WorkPlaceDTO> workplaces = roomService.getWorkPlaceByEq(roomID, booking.getEquipments());
        List<Boolean> workplaceAvailabilityList = bookingService.getAvaiableWorkPlaces(booking, workplaces);

        model.addAttribute("workplaces", workplaces);
        model.addAttribute("workplaceAvailabilityList", workplaceAvailabilityList);

        breadcrumb(model);

        return "user/booking_workplace_overview";
    }


    // Buchung-Objekt aktualisiert mit workplace Id und Equipment Set
    @GetMapping("/booking/search/overview/room/workplace/summary")
    public String selectWorkPlaceBooking(@RequestParam Long workplaceId, BookingDTO booking, Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO user = userService.getCurrentUser(oAuth2User);
        String username = user.getUsername();
        booking.setUserName(username);
        booking.setWorkplaceId(workplaceId);
        WorkPlaceDTO workPlace = workPlaceService.getWorkPlaceById(workplaceId);
        bookingService.updateEqFromWorkPlace(booking ,workPlace.getEquipments());

        userService.saveBookingInUser(username, booking);

        breadcrumbSummary(model);
        return "user/booking_overview_summary";
    }


    // Liste von Bookings werden auf der Reservierung-Seite angezeigt
    @GetMapping("/booking/mybookings")
    public String viewBookingUser(Model model, @AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO user = userService.getCurrentUser(oAuth2User);
        List<BookingDTO> bookings = bookingService.findByUserName(user.getUsername());
        model.addAttribute("bookings", bookings);
        return "user/booking_overview-board";
    }

    // BookingDTO Objekt wird gelöscht aus der BookingDTO Liste
    @PostMapping("/booking/mybookings/delete")
    public String deleteBooking(@RequestParam Long bookingId, Model model) {
        bookingService.deleteBookingById(bookingId);
        return "redirect:/booking/mybookings";
    }


}




