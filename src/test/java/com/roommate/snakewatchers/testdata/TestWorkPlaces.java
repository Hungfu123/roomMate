package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.testdata.builder.WorkPlaceDTOBuilder;

public class TestWorkPlaces {

    public static WorkPlaceDTOBuilder w1() {
        return WorkPlaceDTOBuilder.init()
                .withId(1L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static WorkPlaceDTOBuilder w2() {
        return WorkPlaceDTOBuilder.init()
                .withId(2L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static WorkPlaceDTOBuilder w3() {
        return WorkPlaceDTOBuilder.init()
                .withId(3L)
                .withEquipments(Equipment.MONITOR, Equipment.TABLE_ADJUSTABLE);
    }

    public static WorkPlaceDTOBuilder w4() {
        return WorkPlaceDTOBuilder.init()
                .withId(4L)
                .withEquipments();
    }
}
