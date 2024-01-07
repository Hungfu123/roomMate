package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.testdata.builder.RoomBuilder;

public class TestRooms {

    public static RoomBuilder viper1() {
        return RoomBuilder.init()
                .withId(1L)
                .withRoomName("Viper")
                .withWorkPlaces(TestWorkPlaces.w1().build(), TestWorkPlaces.w2().build());
    }

    public static RoomBuilder python() {
        return RoomBuilder.init()
                .withId(1L)
                .withRoomName("Python")
                .withWorkPlaces(TestWorkPlaces.w3().build(), TestWorkPlaces.w4().build());
    }


}
