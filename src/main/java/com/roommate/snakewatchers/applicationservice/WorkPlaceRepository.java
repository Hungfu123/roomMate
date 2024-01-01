package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface WorkPlaceRepository {

    Collection<WorkPlaceDTO> findAll();
//    Set<WorkPlaceDTO> findAllByRoomId(Long roomId);
    WorkPlaceDTO save(WorkPlaceDTO workPlace);
    void deleteById(Long workplaceId);
    WorkPlaceDTO findById(Long workplaceId);
}
