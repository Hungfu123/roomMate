package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.config.AdminOnly;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Set;

@AdminOnly
@Controller
public class RoomController {

    private final RoomService roomService;
    private final WorkPlaceService workPlaceService;
    private final EquipmentService equipmentService;


    public RoomController(RoomService roomService, WorkPlaceService workPlaceService, EquipmentService equipmentService) {
        this.roomService = roomService;
        this.workPlaceService = workPlaceService;
        this.equipmentService = equipmentService;
    }

    @GetMapping("/room/{roomId}")
    public String roomDetail_viewWorkPlaces(Model model, @PathVariable("roomId") Long roomId){
        model.addAttribute("maybeRoom", roomService.getRoom(roomId));
        List<WorkPlaceDTO> workPlaces = roomService.getWorkPlacesByRoomId(roomId);
        model.addAttribute("workPlaces", workPlaces);
        return "admin/admin_room_details";
    }

    @PostMapping("/admin/room/{roomId}/{workplaceId}/delete")
    public String deleteWorkPlace(@PathVariable Long workplaceId, @PathVariable("roomId") Long roomId){
        workPlaceService.deleteWorkPlace(workplaceId);
        return "redirect:/room/"+ roomId;
    }


    @GetMapping("/admin/{roomId}/workplace")
    public String addWorkPlaceForm(@PathVariable("roomId") Long roomId, Model model) {
        model.addAttribute("equipments", Equipment.values());
        return "admin/admin_add_workplace";
    }
    @PostMapping("/admin/room/{roomId}/addworkplace")
    public RedirectView addWorkPlace(@ModelAttribute WorkPlaceDTO workplace, @RequestParam boolean addWorkPlace, @PathVariable("roomId") Long roomId){
        if(addWorkPlace) {
            //TODO: Room room = roomService.getRoom(roomId)
            workPlaceService.addWorkSpace(roomId,workplace);
        }
        return new RedirectView("/room/"+roomId);
    }

    @GetMapping("/admin/{workplaceId}")
    public String workPlaceProfile(@PathVariable Long workplaceId, Model model) {
        WorkPlaceDTO workplace = workPlaceService.getWorkPlaceById(workplaceId);
        Set<Equipment> equipments = workplace.getEquipments();
        Set<Equipment> allEquipments = equipmentService.findAll();

        model.addAttribute("allEquipments", allEquipments);
        model.addAttribute("workplace", workplace);
        model.addAttribute("equipments", equipments);
        // Redirect zu einer Seite, die die Raumübersicht anzeigt
        return "admin/admin_view_workplace_profile";
    }

    @PostMapping("/admin/{workplaceId}/{equipmentName}/delete")
    public String deleteEquipmentFromWorkPlace(
            @PathVariable Long workplaceId,
            @PathVariable String equipmentName,
            Model model) {
        WorkPlaceDTO workPlace = workPlaceService.getWorkPlaceById(workplaceId);
        Set<Equipment> equipments = workPlaceService.deleteEqFromWorkPlace(equipmentName, workPlace);
        Set<Equipment> allEquipments = equipmentService.findAll();

        model.addAttribute("allEquipments", allEquipments);
        model.addAttribute("workplace", workPlace);
        model.addAttribute("equipments", equipments);
        return "redirect:/admin/"+workplaceId;
    }


    //TODO: Equipment einfügen
    @PostMapping("/admin/{workplaceId}/add/equipment")
    public String addEquipmentFromWorkPlace(
            @PathVariable Long workplaceId,
            @RequestParam String equipment,
            Model model
    ) {
        WorkPlaceDTO workPlace = workPlaceService.getWorkPlaceById(workplaceId);
        Set<Equipment> equipments = workPlace.getEquipments();
        Equipment selectedEquipment = equipmentService.findByName(equipment);
        equipments.add(selectedEquipment);
        workPlaceService.updatedWorkPlaceEq(workPlace,selectedEquipment);

        Set<Equipment> allEquipments = equipmentService.findAll();

        model.addAttribute("allEquipments", allEquipments);
        model.addAttribute("workplace", workPlace);
        model.addAttribute("equipments", equipments);

//        equipment.add(equipmentName);
        return "redirect:/admin/"+workplaceId;
    }



}
