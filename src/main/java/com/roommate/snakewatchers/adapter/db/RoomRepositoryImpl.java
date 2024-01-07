package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.applicationservice.RoomRepository;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.model.Room;
import com.roommate.snakewatchers.mapper.RoomMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryImpl implements RoomRepository {


    private final DBRoomRepository repository;

    public RoomRepositoryImpl(DBRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoomDTO> findAll() {
//        return rooms;
        List<Room> rooms = repository.findAll();

        return rooms.stream()
                .map(RoomMapper::mapToRoomDTO)
                .toList();
    }
    @Override
    public synchronized RoomDTO save(RoomDTO roomDTO) {
        Room room = repository.save(RoomMapper.mapToRoom(roomDTO));
        System.out.println("Generated Room ID:" + room.getId());
        return RoomMapper.mapToRoomDTO(room);
    }
    @Override
    public void saveAll(List<RoomDTO> rooms) {
    }

    @Override
    public RoomDTO findById(Long roomId) {
       Optional<Room> roomOptional = repository.findById(roomId);
       RoomDTO room = RoomMapper.mapToRoomDTO(roomOptional
               .orElseThrow(() -> new NoSuchElementException("Room with ID " + roomId + " not found")));
               return room;
    }
    @Override
    public List<WorkPlaceDTO> findWorkPlacesById(Long roomId) {
        List<RoomDTO> rooms = findAll();
        return rooms.stream()
                .filter(roomDTO -> roomId.equals(roomDTO.getId()))
                .findFirst()
                .map(RoomDTO::getWorkPlaces)
                .orElse(Collections.emptyList());
    }
    @Override
    public void deleteById(Long roomId) {
        repository.deleteById(roomId);
    }
    @Override
    public List<RoomDTO> findRoomsByEq(Set<Equipment> equipments) {
        return findAll().stream()
                .filter(room -> room.getWorkPlaces().stream()
                        .anyMatch(workPlace -> workPlace.getEquipments().containsAll(equipments)))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkPlaceDTO> findFilteredWorkPlacesRoom(Set<Equipment> equipments, Long roomId) {
        return findRoomsByEq(equipments).stream()
                .filter(room -> room.getId().equals(roomId))
                .flatMap(room -> room.getWorkPlaces().stream())
                .filter(workPlace -> workPlace.getEquipments().containsAll(equipments))
                .collect(Collectors.toList());
    }
}
