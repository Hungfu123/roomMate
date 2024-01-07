package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;

import java.util.List;
import java.util.Set;

public interface RoomService {

    List<WorkPlaceDTO> getWorkPlacesByRoomId(Long roomId);

    List<RoomDTO> getRooms();


    void addRoom(RoomDTO room);


    RoomDTO getRoom(Long roomId);

    void deleteRoom(Long roomId);


    List<RoomDTO> getFilteredRoomsWithWorkPlaces(Set<Equipment> equipments);

    List<WorkPlaceDTO> findFilteredWorkPlaces(Set<Equipment> equipments, Long roomId);

    List<WorkPlaceDTO> getWorkPlaceByEq(Long roomID, Set<Equipment> equipments);
}
