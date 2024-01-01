package com.roommate.snakewatchers.adapter.web;


import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;

import java.util.List;
import java.util.Set;

public interface WorkPlaceService {



    WorkPlaceDTO createWorkPlace(Long roomId);

    void addWorkSpace(Long roomId, WorkPlaceDTO workplace);

    void deleteWorkPlace(Long workplaceId);
    List<WorkPlaceDTO> findWithEquipment(Set<Equipment> equipmentSet);

    List<WorkPlaceDTO> getAllWorkPlaces();
    WorkPlaceDTO getWorkPlaceById(Long workplaceId);

    Set<Equipment> deleteEqFromWorkPlace(String equipmentName, WorkPlaceDTO workPlace);
    WorkPlaceDTO updatedWorkPlaceEq(WorkPlaceDTO workPlaceDTO, Equipment eq);
}
