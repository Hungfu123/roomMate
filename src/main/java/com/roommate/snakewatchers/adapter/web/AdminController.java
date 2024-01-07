package com.roommate.snakewatchers.adapter.web;
import com.roommate.snakewatchers.config.AdminOnly;
import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.UserDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;

@AdminOnly
@Controller
@SessionAttributes("bookingDTO")
public class AdminController {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final EquipmentService equipmentService;

    private final UserService userService;

    public AdminController(RoomService roomService, BookingService bookingService, EquipmentService equipmentService, UserService userService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.equipmentService = equipmentService;
        this.userService = userService;
    }

    @GetMapping("/admin/control")
    public String index(Model model){
        List<RoomDTO> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);
        return "admin/admin_view_rooms";
    }

    @GetMapping("/admin/management")
    public String managementBookings(Model model){
        List<BookingDTO> bookings = bookingService.getBookings();
        model.addAttribute("bookings", bookings);
        return "admin/admin_management_bookings";
    }

    @PostMapping("/admin/management/{bookingId}/delete")
    public String deleteBookInManagement(@PathVariable Long bookingId){
        bookingService.deleteBookingById(bookingId);
        return "redirect:/admin/management";
    }
    @GetMapping("/admin/management/{bookingId}/edit")
    public String editBookForm(@PathVariable Long bookingId,
                               Model model){
        List<Equipment> equipmentList = equipmentService.findAll().stream().toList();
        BookingDTO bookingDTO = bookingService.findById(bookingId);

        model.addAttribute("bookingDTO",bookingDTO);
        model.addAttribute("equipments",equipmentList);
        return "admin/admin_booking_edit_profile";
    }

    @PostMapping("/admin/management/{bookingId}/edit")
    public String saveEditedBooking(@PathVariable Long bookingId,
                                    BookingDTO bookingDTO,
                                    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeFrom,
                                    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeTo,
                                    RedirectAttributes redirectAttributes,
                                    Model model){
        List<Equipment> equipmentList = equipmentService.findAll().stream().toList();
        bookingService.addBooking(bookingDTO);

        redirectAttributes.addFlashAttribute("successMessage", "Deine Buchung wurde erfolgreich gespeichert!");
        model.addAttribute("equipments",equipmentList);
        model.addAttribute("bookingDTO",bookingDTO);
        return "redirect:/admin/management/" +bookingId +"/edit";
    }


    @PostMapping("/room/{roomId}/delete")
    public RedirectView deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        // Redirect zu einer Seite, die die Raumübersicht anzeigt
        return new RedirectView("/admin/control");
    }

    @GetMapping("/admin/addroom")
    public String addRoomForm() {
        return "/admin/admin_add_room";
    }

    @PostMapping("/admin/addroom")
    public RedirectView addRoom(RoomDTO room){
        roomService.addRoom(room);
        System.out.println(room);
        return new RedirectView("/admin/control");
    }

    //
    @GetMapping("/admin")
    public String helloAdmin(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        UserDTO user = userService.getCurrentUser(oAuth2User);
        String username =   "ADMIN " +user.getUsername();

        model.addAttribute("username", username);

        return "admin/admin_dashboard";
    }

    @ModelAttribute("booking")
    public BookingDTO setUpBooking() {
        return new BookingDTO();
    }
    @GetMapping("/admin/room/{roomId}/{workplaceId}/block")
    public String blockWorkPlaceForm(@PathVariable Long workplaceId,
                                     @PathVariable Long roomId,
                                     BookingDTO bookingDTO,
                                     OAuth2AuthenticationToken auth,
                                     Model model) {

        String name = "ADMIN " + auth.getPrincipal().getAttribute("login");
        bookingDTO.setUserName(name);
        bookingService.updateBookingWithRoom(roomId, bookingDTO);

        List<BookingDTO> bookings = bookingService.findBookingsByWorkPlaceId(workplaceId, bookingDTO);

        model.addAttribute("bookings", bookings);
        return "admin/admin_block_workplace";
    }

    @PostMapping("/admin/room/{workplaceId}/blockworkplace")
    public String blockWorkPlace( @Valid BookingDTO bookingDTO,
                                  BindingResult bindingResult,
                                  @PathVariable Long workplaceId,
                                  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeFrom,
                                  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") LocalDateTime dateTimeTo,
                                  Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("bookingDTO", bookingDTO);
            return "admin/admin_block_workplace";
        }
        bookingService.addBooking(bookingDTO);

        model.addAttribute("bookingDTO", bookingDTO);
        return "redirect:/room/"+ bookingDTO.getRoomId();
    }

    @PostMapping("/admin/room/{workplaceId}/{bookingId}/releasebooking")
    public String releaseBookingWp(@PathVariable Long bookingId,
                                   @PathVariable Long workplaceId) {
        BookingDTO booking = bookingService.findById(bookingId);
        Long roomId = booking.getRoomId();
        bookingService.deleteBookingById(bookingId);

        System.out.println("WorkPlace" + workplaceId + " is released");
        return "redirect:/admin/room/"+ roomId+ "/" + workplaceId +"/block";
    }



}
