package com.roommate.snakewatchers.domain.DTO;

import com.roommate.snakewatchers.domain.model.Equipment;
import lombok.*;

import java.util.*;

import static com.roommate.snakewatchers.adapter.web.utils.RandomId.generateRandomEquipments;
import static com.roommate.snakewatchers.adapter.web.utils.RandomId.generateRandomLongId;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkPlaceDTO {

    private Long id;
    private Set<Equipment> equipments;

    public void addEquipment(Equipment equipment) {
    equipments.add(equipment);
}

    @Override
    public String toString() {
        return "WorkPlaceDTO{" +
                "id=" + id +
                ", equipments=" + equipments +
                '}';
    }

}
