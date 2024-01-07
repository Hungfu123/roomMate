package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.testdata.builder.WorkPlaceBuilder;

public class TestWorkPlaces {

    public static WorkPlaceBuilder w1() {
        return WorkPlaceBuilder.init()
                .withId(1L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static WorkPlaceBuilder w2() {
        return WorkPlaceBuilder.init()
                .withId(2L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static WorkPlaceBuilder w3() {
        return WorkPlaceBuilder.init()
                .withId(3L)
                .withEquipments(Equipment.MONITOR, Equipment.TABLE_ADJUSTABLE);
    }

    public static WorkPlaceBuilder w4() {
        return WorkPlaceBuilder.init()
                .withId(4L)
                .withEquipments();
    }
}
