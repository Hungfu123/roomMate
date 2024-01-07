package com.roommate.snakewatchers.adapter.db;

import com.roommate.snakewatchers.applicationservice.WorkPlaceRepository;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.WorkPlace;
import com.roommate.snakewatchers.mapper.WorkPlaceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class WorkPlaceRepositoryImpl implements WorkPlaceRepository {


    private final DBWorkPlaceRepository repository;

    public WorkPlaceRepositoryImpl(DBWorkPlaceRepository repository) {
        this.repository = repository;
    }
@Override
    public List<WorkPlaceDTO> findAll() {
        List<WorkPlace> workplaces = repository.findAll();
        return workplaces.stream()
                .map(WorkPlaceMapper::mapToWorkPlaceDTO)
                .toList();

    }

    @Override
    public WorkPlaceDTO save(WorkPlaceDTO workPlaceDTO) {
//        workPlaces.add(workPlace);
        WorkPlace wp = WorkPlaceMapper.mapToWorkPlace(workPlaceDTO);
        WorkPlace workPlace = repository.save(wp);
        return WorkPlaceMapper.mapToWorkPlaceDTO(workPlace);
    }
    @Override
    public void deleteById(Long workplaceId) {
        repository.deleteById(workplaceId);
    }
    @Override
    public WorkPlaceDTO findById(Long workplaceId) {
        Optional<WorkPlace> workPlaceOptional = repository.findById(workplaceId);
        WorkPlaceDTO workplace = WorkPlaceMapper.mapToWorkPlaceDTO(workPlaceOptional
                .orElseThrow(() -> new NoSuchElementException("Kein WorkPlace mit der ID" + workplaceId + "gefunden")));
        return workplace;
    }
}
