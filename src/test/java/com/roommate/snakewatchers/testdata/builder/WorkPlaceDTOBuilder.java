package com.roommate.snakewatchers.testdata.builder;

import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;

import java.util.HashSet;
import java.util.Set;

public class WorkPlaceDTOBuilder {

    private Long id= 5L;
    private Long roomId = 2L;
    private Set<Equipment> equipments = new HashSet<>();

    public static WorkPlaceDTOBuilder init() {
        return new WorkPlaceDTOBuilder();
    }

    public WorkPlaceDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

//    public WorkPlaceBuilder withRoomId(Long roomId) {
//        this.roomId = id;
//        return this;
//    }

    public WorkPlaceDTOBuilder withEquipments(Set<Equipment> equipments) {
        this.equipments.addAll(equipments);
        return this;
    }

    public WorkPlaceDTOBuilder withEquipments(Equipment... equipment) {
        this.equipments.addAll(equipments);
        return this;
    }

    public WorkPlaceDTO build() {
        if(id==null || roomId == null) {
            throw new IllegalArgumentException("Must contain Workplace-ID and RoomID");
        }
        return new WorkPlaceDTO(this.id,this.equipments);
    }

}
