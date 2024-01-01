package com.roommate.snakewatchers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private Long id;
    private String name;
    private List<WorkPlace> workPlaces;

//    public Room() {
//        this.roomID = generateRandomLongId();
//        this.workPlaces = new ArrayList<>();
//    }
//    @PersistenceCreator
//    public Room(Long id, String name, List<WorkPlace >workPlaces) {
//        this.id = id;
//        this.name = name;
//        this.workPlaces = workPlaces;
//    }

    public String getName() {
        return name;
    }


    public List<WorkPlace> getWorkPlaces() {
        return workPlaces;
    }

    public Long getId() {
        return id;
    }


    public void setWorkPlaces(List<WorkPlace> workPlacesList) {
        this.workPlaces = workPlacesList;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomID=" + id +
                ", roomName='" + name + '\'' +
                ", workPlaces=" + workPlaces +
                '}';

    }
}
