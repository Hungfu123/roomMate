package com.roommate.snakewatchers.mapper;

import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Room;
import com.roommate.snakewatchers.domain.model.WorkPlace;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {


    public static RoomDTO mapToRoomDTO(Room room){
        List<WorkPlace> workPlaces = room.getWorkPlaces();

        return RoomDTO.builder()
                .id(room.getId())
                .roomName(room.getName())
                .workPlaces((workPlaces!=null)
                        ? workPlaces.stream().map(workPlace -> WorkPlaceMapper.mapToWorkPlaceDTO(workPlace)).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static Room mapToRoom(RoomDTO room){
        List<WorkPlaceDTO> workPlaces = room.getWorkPlaces();

        return Room.builder()
                .id(room.getId())
                .name(room.getRoomName())
                .workPlaces((workPlaces!=null)
                       ? workPlaces.stream().map(workPlace -> WorkPlaceMapper.mapToWorkPlace(workPlace)).collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }
}
