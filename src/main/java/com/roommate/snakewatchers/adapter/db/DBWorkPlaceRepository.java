package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.domain.model.WorkPlace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DBWorkPlaceRepository extends CrudRepository<WorkPlace,Long> {

    List<WorkPlace> findAll();
}
