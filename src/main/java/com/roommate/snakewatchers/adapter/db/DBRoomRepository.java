package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.domain.model.Room;
import com.roommate.snakewatchers.domain.model.WorkPlace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBRoomRepository extends CrudRepository<Room,Long> {

    List<Room> findAll();
    List<WorkPlace> findAllById(Long roomId); //TODO: Warum room
}
