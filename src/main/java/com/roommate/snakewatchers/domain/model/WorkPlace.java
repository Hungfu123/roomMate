package com.roommate.snakewatchers.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkPlace {
    @Id
    private Long id;
//    private Long roomId;
    private Set<Equipment> equipment;

//    public WorkPlace() {
//        this.id = generateRandomLongId();
//        this.roomId = roomId;
//        this.equipments = generateRandomEquipments();
//    }


    @Override
    public String toString() {
        return "WorkPlaceDTO{" +
                "id=" + id +
//                ", roomId=" + roomId +
                ", equipments=" + equipment +
                '}';
    }
}
