package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.adapter.web.WorkPlaceService;
import com.roommate.snakewatchers.applicationservice.RoomRepository;
import com.roommate.snakewatchers.applicationservice.WorkPlaceRepository;
import com.roommate.snakewatchers.applicationservice.WorkPlaceServiceImpl;
import com.roommate.snakewatchers.domain.DTO.RoomDTO;
import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
import com.roommate.snakewatchers.domain.model.Equipment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WorkPlaceServiceTest {
    WorkPlaceRepository workRepo = mock(WorkPlaceRepository.class);
    RoomRepository roomRepo = mock(RoomRepository.class);

    @Test
    @DisplayName("Ein WorkPlaceDTO wurde erstellt und wird in Room hinzugefügt")
    void test01() {
        //Arrange
        WorkPlaceService service = new WorkPlaceServiceImpl (workRepo, roomRepo);
        RoomDTO roomDTO = TestRooms.diablo().withId(1L).build();
        when(roomRepo.save(any())).thenReturn(new RoomDTO());
        when(roomRepo.findById(any())).thenReturn(roomDTO);
        WorkPlaceDTO wp = TestWorkPlaces.w1().build();
//        WorkPlaceDTO wp2 = TestWorkPlaces.w2().build();

        //Act
        service.addWorkSpace(roomDTO.getId(), wp);
        ArgumentCaptor<RoomDTO> captor = ArgumentCaptor.forClass(RoomDTO.class);

        //Assert
        verify(roomRepo).save(captor.capture());
        RoomDTO savedRoomDTO = captor.getValue();

        assertThat(savedRoomDTO.getRoomName()).isEqualTo("Diablo");
        assertThat(roomDTO.getWorkPlaces().contains(wp));
        assertThat(roomDTO.getWorkPlaces().size()).isEqualTo(1);

    }
    @Test
    @DisplayName("Ein WorkPlaceDTO wurde erstellt und wird NICHT in Room hinzugefügt")
    void test02() {
        //Arrange
        WorkPlaceService service = new WorkPlaceServiceImpl (workRepo, roomRepo);
        RoomDTO roomDTO = TestRooms.diablo().withId(1L).build();
        when(roomRepo.save(any())).thenReturn(new RoomDTO());
        when(roomRepo.findById(any())).thenReturn(roomDTO);
        WorkPlaceDTO wp = TestWorkPlaces.w1().build();

        //Act
        service.addWorkSpace(roomDTO.getId(), null);
        ArgumentCaptor<RoomDTO> captor = ArgumentCaptor.forClass(RoomDTO.class);

        //Assert
        verify(roomRepo).save(captor.capture());
        RoomDTO savedRoomDTO = captor.getValue();

        assertThat(savedRoomDTO.getRoomName()).isEqualTo("Diablo");
        assertThat(roomDTO.getWorkPlaces()).doesNotContain(wp);
    }

    @Test
    @DisplayName("Ein WorkPlaceDTO deleteById wird aufgerufen")
    void test03() {
        //Arrange
        WorkPlaceService service = new WorkPlaceServiceImpl (workRepo, roomRepo);
        WorkPlaceDTO wp = TestWorkPlaces.w1().withId(2L).build();

        //Act
        service.deleteWorkPlace(wp.getId());

        // Assert
        verify(workRepo).deleteById(wp.getId());
    }


    @Test
    @DisplayName("WorkPlaceDTO ist updated mit Equipment")
    void test04() {
        // Arrange
        WorkPlaceService service = new WorkPlaceServiceImpl (workRepo, roomRepo);
        WorkPlaceDTO wp = TestWorkPlaces.w1().withId(2L).build();
        Equipment eq = Equipment.DOCKINGSTATION;


        // Act
        service.updatedWorkPlaceEq(wp, eq);
        ArgumentCaptor<WorkPlaceDTO> captor = ArgumentCaptor.forClass(WorkPlaceDTO.class);

        verify(workRepo).save(captor.capture());
        WorkPlaceDTO savedWorkPlaceDTO = captor.getValue();

        // Assert
        assertThat(savedWorkPlaceDTO.getEquipments()).contains(Equipment.DOCKINGSTATION);
        assertThat(savedWorkPlaceDTO.getEquipments().size()).isEqualTo(1);
    }


}
