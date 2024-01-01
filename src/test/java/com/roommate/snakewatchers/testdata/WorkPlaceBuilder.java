package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;

import java.util.HashSet;
import java.util.Set;

public class WorkPlaceBuilder {

    private Long id= null;
    private Long roomId = null;
    private Set<Equipment> equipments = new HashSet<>();

    public static WorkPlaceBuilder init() {
        return new WorkPlaceBuilder();
    }

    public WorkPlaceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WorkPlaceBuilder withRoomId(Long roomId) {
        this.roomId = id;
        return this;
    }

    public WorkPlaceBuilder withEquipments(Set<Equipment> equipments) {
        this.equipments.addAll(equipments);
        return this;
    }

    public WorkPlaceBuilder withEquipments(Equipment... equipment) {
        this.equipments.addAll(equipments);
        return this;
    }

    public WorkPlaceDTO build() {
        if(id==null || roomId == null) {
            throw new IllegalArgumentException("Must contain Workplace-ID and RoomID");
        }
        return new WorkPlaceDTO(this.id, this.roomId,this.equipments);
    }

}
