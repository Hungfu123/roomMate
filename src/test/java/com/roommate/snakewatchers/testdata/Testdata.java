package com.roommate.snakewatchers.testdata;//package com.roommate.snakewatchers.testdata;
//
//import com.roommate.snakewatchers.domain.DTO.BookingDTO;
//import com.roommate.snakewatchers.domain.model.Equipment;
//import com.roommate.snakewatchers.domain.DTO.RoomDTO;
//import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
//
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//
//public class Testdata {
////    public List<RoomDTO> rooms = new ArrayList<>(List.of(
////            new RoomDTO(1L, "Python",  new ArrayList<>(List.of(
////                    new WorkPlaceDTO(1L, 11L, new HashSet<>(List.of(Equipment.MONITOR, Equipment.DOCKINGSTATION))),
//                    new WorkPlaceDTO(2L, 11L, new HashSet<>(List.of(Equipment.MONITOR, Equipment.DOCKINGSTATION))),
////                    new WorkPlaceDTO(3L, 11L, new HashSet<>(List.of(Equipment.TABLE_ADJUSTABLE, Equipment.DOCKINGSTATION_USBC))),
////                    new WorkPlaceDTO(4L, 11L, new HashSet<>(List.of(Equipment.MONITOR)))
////            ))
////            ),
////            new RoomDTO(7L, "Viper",  new ArrayList<>(List.of(
////                    new WorkPlaceDTO(5L, 71L, new HashSet<>(List.of(Equipment.MONITOR, Equipment.DOCKINGSTATION))),
////                    new WorkPlaceDTO(6L, 71L, new HashSet<>(List.of(Equipment.MONITOR, Equipment.DOCKINGSTATION))),
////                    new WorkPlaceDTO(7L, 71L, new HashSet<>(List.of(Equipment.TABLE_ADJUSTABLE, Equipment.DOCKINGSTATION))),
////                    new WorkPlaceDTO(8L, 71L, new HashSet<>(List.of(Equipment.MONITOR))),
////                    new WorkPlaceDTO(8L, 71L, new HashSet<>(List.of(Equipment.MONITOR,Equipment.TABLE_ADJUSTABLE,Equipment.DOCKINGSTATION))),
////                    new WorkPlaceDTO(9L, 71L, new HashSet<>(List.of(Equipment.TABLE_ADJUSTABLE)))
////            ))
////            )
////    ));
////
////    public RoomDTO getTestRoom () {
////        return rooms.get(0);
////    }
////
////    public List<BookingDTO> bookings = new ArrayList<>(List.of(
////            new BookingDTO(1L, 1L, 1L, "Python",9L, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), new HashSet<>(List.of(Equipment.MONITOR, Equipment.DOCKINGSTATION)),"Test"),
////            new BookingDTO(2L, 5L, 7L, "Viper", 9L, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), new HashSet<>(List.of(Equipment.MONITOR, Equipment.TABLE_ADJUSTABLE)),"Testuser2"),
////            new BookingDTO(3L, 2L, 1L, "Python", 9L, LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), new HashSet<>(List.of(Equipment.DOCKINGSTATION_USBC)),"Testuser3")
////    ));
////
//
//
//    public Set<WorkPlaceDTO> workPlaces = new HashSet<>();
//
//    public Testdata() {
//        // Populate workPlaces based on the rooms list
//        for (RoomDTO room : rooms) {
//            workPlaces.addAll(room.getWorkPlaces());
//        }
//    }
//}
