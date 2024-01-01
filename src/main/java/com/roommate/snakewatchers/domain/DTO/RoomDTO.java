package com.roommate.snakewatchers.domain.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private Long id;
    private String roomName;
    private List<WorkPlaceDTO> workPlaces;

//    public RoomDTO() {
//        this.roomID = generateRandomLongId();
//        this.workPlaces = new ArrayList<>();
//    }

    public String getRoomName() {
        return roomName;
    }


    public List<WorkPlaceDTO> getWorkPlaces(){
        return workPlaces;
    }
    public Long getId() {
        return id;
    }


    public void addWorkPlace(WorkPlaceDTO workPlaceDTO) {
        workPlaces.add(workPlaceDTO);
    }

    public void setWorkPlaces(List<WorkPlaceDTO> workPlacesList) {
        this.workPlaces = workPlacesList;
    }
    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomID=" + id +
                ", roomName='" + roomName + '\'' +
                ", workPlaces=" + workPlaces +
                '}';
    }
}
