package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.testdata.builder.RoomDTOBuilder;

import java.util.ArrayList;

public class TestRooms {

    public static RoomDTOBuilder viper1() {
        return RoomDTOBuilder.init()
                .withId(1L)
                .withRoomName("Viper")
                .withWorkPlaces(TestWorkPlaces.w1().build(), TestWorkPlaces.w2().build());
    }

    public static RoomDTOBuilder python() {
        return RoomDTOBuilder.init()
                .withId(1L)
                .withRoomName("Python")
                .withWorkPlaces(TestWorkPlaces.w3().build(), TestWorkPlaces.w4().build());
    }

    public static RoomDTOBuilder diablo() {
        return RoomDTOBuilder.init()
                .withId(1L)
                .withRoomName("Diablo")
                .withWorkPlaces(new ArrayList<>());
    }


}
