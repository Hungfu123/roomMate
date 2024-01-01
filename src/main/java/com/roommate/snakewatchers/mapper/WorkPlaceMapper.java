package com.roommate.snakewatchers.mapper;

import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.WorkPlace;

public class WorkPlaceMapper {

    public static WorkPlaceDTO mapToWorkPlaceDTO(WorkPlace workPlace) {
        return WorkPlaceDTO.builder()
                .id(workPlace.getId())
//                .roomId(workPlace.getRoomId())
                .equipments(workPlace.getEquipment())
                .build();
    }

    public static WorkPlace mapToWorkPlace(WorkPlaceDTO workPlace) {
        return WorkPlace.builder()
                .id(workPlace.getId())
//                .roomId(workPlace.getRoomId())
                .equipment(workPlace.getEquipments())
                .build();
    }
}
