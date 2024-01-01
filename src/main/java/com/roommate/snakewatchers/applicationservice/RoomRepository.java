package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RoomRepository {

    Collection<RoomDTO> findAll();
    RoomDTO save(RoomDTO room);
    void saveAll(List<RoomDTO> rooms);
    RoomDTO findById(Long roomId);
    List<WorkPlaceDTO> findWorkPlacesById(Long roomId);
    void deleteById(Long roomId);
    List<RoomDTO> findRoomsByEq(Set<Equipment> equipments);
    List<WorkPlaceDTO> findFilteredWorkPlacesRoom(Set<Equipment> equipments, Long roomId);

}
