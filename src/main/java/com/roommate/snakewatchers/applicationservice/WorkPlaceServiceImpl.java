package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.domain.model.Equipment;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.adapter.db.RoomRepositoryImpl;
import com.roommate.snakewatchers.adapter.db.WorkPlaceRepositoryImpl;
import com.roommate.snakewatchers.adapter.web.WorkPlaceService;
import com.roommate.snakewatchers.domain.model.WorkPlace;
import com.roommate.snakewatchers.mapper.WorkPlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class WorkPlaceServiceImpl implements WorkPlaceService {

    private WorkPlaceRepository workRepository;
    private RoomRepository roomRepository;

    public WorkPlaceServiceImpl(WorkPlaceRepository workRepository, RoomRepository roomRepository) {
        this.workRepository = workRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public WorkPlaceDTO createWorkPlace(Long roomId) {
        return new WorkPlaceDTO();
    }

    @Override
    public void addWorkSpace(Long roomId, WorkPlaceDTO workplace) {
        RoomDTO room = roomRepository.findById(roomId);
        room.addWorkPlace(workplace);
       roomRepository.save(room);
    }

    @Override
    public void deleteWorkPlace(Long workplaceId) {
        workRepository.deleteById(workplaceId);
    }

    @Override
    public List<WorkPlaceDTO> findWithEquipment(Set<Equipment> equipmentSet) {
        return workRepository.findAll().stream()
                .filter(p -> p.getEquipments().containsAll(equipmentSet))
                .distinct()
                .toList();
    }

    @Override
    public WorkPlaceDTO updatedWorkPlaceEq(WorkPlaceDTO workPlaceDTO, Equipment eq) {
        workPlaceDTO.addEquipment(eq);
        return workRepository.save(workPlaceDTO);
    }


    @Override
    public List<WorkPlaceDTO> getAllWorkPlaces() {
        return workRepository.findAll().stream().toList();
    }
    @Override
    public WorkPlaceDTO getWorkPlaceById(Long workplaceId) {
        return workRepository.findById(workplaceId);
    }

    @Override
    public Set<Equipment> deleteEqFromWorkPlace(String equipmentName, WorkPlaceDTO workPlace) {
        Set<Equipment> equipments = workPlace.getEquipments();

        equipments.removeIf(equipment -> equipment.getDisplayName().equals(equipmentName));
        workPlace.setEquipments(equipments);
        workRepository.save(workPlace);
        return equipments;
    }
}
