package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.testdata.builder.BookingDTOBuilder;

public class TestBookings {

    public static BookingDTOBuilder booking2Viper() {
        return BookingDTOBuilder.init()
                .withBookingID(2L)
                .withRoomName("Viper")
                .withWorkingSpaceId(1L)
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingDTOBuilder booking1Python() {
        return BookingDTOBuilder.init()
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingDTOBuilder booking3Diablo() {
        return BookingDTOBuilder.init()
                .withBookingID(3L)
                .withRoomName("Diablo")
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }

    public static BookingDTOBuilder booking4Anaconda() {
        return BookingDTOBuilder.init()
                .withBookingID(4L)
                .withRoomName("Anaconda")
                .withEquipments(Equipment.DOCKINGSTATION, Equipment.MONITOR);
    }



}
