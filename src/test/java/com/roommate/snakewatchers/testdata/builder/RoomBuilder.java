package com.roommate.snakewatchers.testdata.builder;

import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomBuilder {

        private Long roomId= 2L;
        private String roomName = "Snake";
        private List<WorkPlaceDTO> workPlaces = new ArrayList<>();

        public static RoomBuilder init() {
            return new RoomBuilder();
        }

        public RoomBuilder withId(Long id) {
            this.roomId = id;
            return this;
        }

        public RoomBuilder withRoomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public RoomBuilder withWorkPlaces(List<WorkPlaceDTO> workPlaces) {
            this.workPlaces.addAll(workPlaces);
            return this;
        }

        public RoomBuilder withWorkPlaces(WorkPlaceDTO... workPlaces) {
            this.workPlaces.addAll(Arrays.asList(workPlaces));
            return this;
        }

        public RoomDTO build() {
            if(roomId==null || roomName == null) {
                throw new IllegalArgumentException("Must contain RoomDTO-ID and RoomName");
            }
            return new RoomDTO(this.roomId, this.roomName,this.workPlaces);
        }



}
