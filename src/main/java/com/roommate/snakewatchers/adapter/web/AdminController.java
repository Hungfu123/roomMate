package com.roommate.snakewatchers.adapter.web;
import com.roommate.snakewatchers.config.AdminOnly;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@AdminOnly
@Controller
public class AdminController {

    private final RoomService roomService;

    public AdminController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/admin/control")
    public String index(Model model){
        List<RoomDTO> rooms = roomService.getRooms();
        model.addAttribute("rooms", rooms);
        return "admin/admin_view_rooms";
    }


    @PostMapping("/room/{roomId}/delete")
    public RedirectView deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        // Redirect zu einer Seite, die die Raumübersicht anzeigt
        return new RedirectView("/admin");
    }

    @GetMapping("/admin/addroom")
    public String addRoomForm() {
        return "/admin/admin_add_room";
    }

    @PostMapping("/admin/addroom")
    public RedirectView addRoom(RoomDTO room){
        roomService.addRoom(room);
        System.out.println(room);
        return new RedirectView("/admin");
    }

    //
    @GetMapping("/admin")
    public String helloAdmin() {
        return "admin/admin_dashboard";
    }

    @GetMapping("/admin/room/{roomId}/{workplaceId}/block")
    public String blockWorkPlace() {

        return "admin/admin_block_workplace";
    }




}
