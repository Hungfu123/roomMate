package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.adapter.web.RoomService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<WorkPlaceDTO> getWorkPlacesByRoomId(Long roomId) {
        return roomRepository.findWorkPlacesById(roomId);
    }

    @Override
    public List<RoomDTO> getRooms() {
        return roomRepository.findAll().stream().toList();
    }

    @Override
    public void addRoom(RoomDTO room) {

        roomRepository.save(room);
    }

    @Override
    public RoomDTO getRoom(Long roomId){
        return roomRepository.findById(roomId);
    }

    @Override
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<RoomDTO> getFilteredRoomsWithWorkPlaces(Set<Equipment> equipments) {
        return roomRepository.findRoomsByEq(equipments);
    }

    @Override
    public List<WorkPlaceDTO> findFilteredWorkPlaces(Set<Equipment> equipments, Long roomId) {
        return roomRepository.findFilteredWorkPlacesRoom(equipments, roomId);
    }


}
