package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.testdata.builder.BookingBuilder;

public class TestBookings {

    public static BookingBuilder booking2Viper() {
        return BookingBuilder.init()
                .withBookingID(2L)
                .withRoomName("Viper")
                .withWorkingSpaceId(1L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingBuilder booking1Python() {
        return BookingBuilder.init()
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingBuilder booking3Diablo() {
        return BookingBuilder.init()
                .withBookingID(3L)
                .withRoomName("Diablo")
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingBuilder booking4Anaconda() {
        return BookingBuilder.init()
                .withBookingID(4L)
                .withRoomName("Anaconda")
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }



}
