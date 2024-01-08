//package com.roommate.snakewatchers.controller;
//
//import com.roommate.snakewatchers.adapter.web.BookingService;
//import com.roommate.snakewatchers.adapter.web.RoomService;
//import com.roommate.snakewatchers.adapter.web.UserBookingController;
//import com.roommate.snakewatchers.adapter.web.WorkPlaceService;
//import com.roommate.snakewatchers.domain.DTO.BookingDTO;
//import com.roommate.snakewatchers.domain.DTO.RoomDTO;
//import com.roommate.snakewatchers.domain.DTO.WorkPlaceDTO;
//import com.roommate.snakewatchers.domain.model.Equipment;
//import com.roommate.snakewatchers.testdata.RequestBookingBuilder;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashSet;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserBookingController.class)
//public class UserBookingControllerTest {
//    private static SecurityMockMvcRequestPostProcessors.OAuth2LoginRequestPostProcessor getOauthWithUser() {
//        return oauth2Login().attributes(a -> {
//            a.put("login", "hutieu");
//            a.put("id", 123);
//            a.put("avatar_url", "url");
//        });
//    }
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    BookingService bookingService;
//    @MockBean
//    RoomService roomService;
//    @MockBean
//    WorkPlaceService workPlaceService;
//
//
//
//    @DisplayName("URL / is available")
//    @Test
//    void test00() throws Exception {
//        mockMvc.perform(get("/").with(getOauthWithUser()))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("URL '/' was invoked and the view is 'user/dashboard'")
//    @Test
//    void test01() throws Exception {
//        MvcResult result = mockMvc.perform(get("/").with(csrf()).with(getOauthWithUser()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/dashboard"))
//                .andReturn();
//
//        BookingDTO bookingDTO = (BookingDTO) result.getModelAndView().getModel().get("booking");
//        assertEquals("hutieu", bookingDTO.getUserName());
//    }
//
//    @DisplayName("Confirm BookingDTO with valid data using Builders")
//    @Test
//    void test02() throws Exception {
//        // BookingDTO Builder erstellen
//        BookingDTO booking = BookingBuilderDTO.init()
//                .withRoomId(1L)
//                .withRoomName("Test RoomDTO")
//                .withDateTimeFrom(LocalDateTime.now())
//                .withDateTimeTo(LocalDateTime.now().plusHours(2))
//                .withEquipments(new HashSet<>(Arrays.asList(Equipment.DOCKINGSTATION, Equipment.MONITOR)))
//                .withUserName("test")
//                .build();
//
//        mockMvc.perform(post("/").with(csrf()).with(getOauthWithUser())
//                .flashAttr("booking", booking))
//                .andExpect(redirectedUrl("/"))
//                .andExpect(flash().attribute("successMessage", "Deine Buchung wurde erfolgreich bestätigt!"));
//
//    }
//
//    @DisplayName("Select BookingDTO View with existing booking")
//    @Test
//    void test03() throws Exception {
//        // BookingBuilder erstellen
//        BookingDTO existingBooking = BookingBuilder.init()
//                .withRoomId(1L)
//                .withRoomName("Test RoomDTO")
//                .withDateTimeFrom(LocalDateTime.now())
//                .withDateTimeTo(LocalDateTime.now().plusHours(2))
//                .withEquipments(new HashSet<>(Arrays.asList(Equipment.DOCKINGSTATION, Equipment.MONITOR)))
//                .build();
//
//        when(bookingService.checkDuplicate(existingBooking)).thenReturn(true);
//
//        mockMvc.perform(RequestBookingBuilder.postSelectBookingView(existingBooking, "01.01.2022 10:00", "01.01.2022 12:00").build())
//                .andExpect(status().isOk())
//                .andExpect(view().name("user/booking_selection"));
//
//        verify(bookingService).checkDuplicate(any());
//        verify(roomService, never()).getRooms();
//    }
//
////    @DisplayName("RoomDTO Overview is available: '/booking/search/overview/room' with BookingDTO Object")
////    @Test
////    public void test_04() throws Exception {
////        Testdata testData = new Testdata();
////        when(roomService.getFilteredRoomsWithWorkPlaces(Mockito.any())).thenReturn(testData.rooms);
////        mockMvc.perform(get("/booking/search/overview/room").with(oauth2Login())
////                        .param("dateTimeFrom", "06.07.2025 10:00")
////                        .param("dateTimeTo", "08.07.2025 12:00")
////                        .param("equipments", "MONITOR", "DOCKINGSTATION"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("user/booking_room_overview"))
////                .andExpect(model().attribute("rooms", testData.rooms));
////    }
//
//    //TODO: Alle Validierungen testen und dann redirect auf booking-search, falsche Inputs
//    @DisplayName("URL overview/room is available")
//    @Test
//    public void test_05() throws Exception{
//        mockMvc.perform(get("/booking/search/overview/room").with(oauth2Login()))
//                .andExpect(status().isOk());
//    }
//    @DisplayName("roomId and roomName were not selected and URL'overview/room/workpalce' have Bad Request")
//    @Test
//    public void test_06() throws Exception{
//        mockMvc.perform(get("/booking/search/overview/room/workplace").with(oauth2Login()))
//                .andExpect(status().isBadRequest());
//    }
//
//    @DisplayName("roomId and roomName were selected  and URL'overview/room/workplace' is available")
//    @Test
//    public void test_07() throws Exception{
//        RoomDTO room = RoomBuilder.init()
//                .withId(1L)
//                .withRoomName("Test")
//                .build();
//        when(roomService.getRoom(Mockito.any())).thenReturn(room);
//        BookingDTO existingBooking = BookingBuilder.init()
//                .withDateTimeFrom(LocalDateTime.now())
//                .withDateTimeTo(LocalDateTime.now().plusHours(2))
//                .withEquipments(new HashSet<>(Arrays.asList(Equipment.DOCKINGSTATION)))
//                .build();
//
//        mockMvc.perform(RequestBookingBuilder.getOverviewWorkplaces(1L, existingBooking)
//                .build())
//                .andExpect(status().isOk());
//    }
//    @DisplayName("RoomDTO was selected  and URL'overview/room/workplace' is available")
//    @Test
//    public void test_08() throws Exception{
//        RoomDTO room = RoomBuilder.init()
//                .withId(1L)
//                .withRoomName("Test")
//                .build();
//        when(roomService.getRoom(Mockito.any())).thenReturn(room);
//        BookingDTO existingBooking = BookingBuilder.init()
//                .withDateTimeFrom(LocalDateTime.now())
//                .withDateTimeTo(LocalDateTime.now().plusHours(2))
//                .withEquipments(new HashSet<>(Arrays.asList(Equipment.DOCKINGSTATION)))
//                .build();
//
//        mockMvc.perform(RequestBookingBuilder.getOverviewWorkplaces(1L, existingBooking)
//                        .build())
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("room and workplace were not selected and Summary Page has Bad Request")
//    @Test
//    public void test_09() throws Exception{
//        mockMvc.perform(get("/booking/search/overview/room/workplace/summary").with(oauth2Login()))
//                .andExpect(status().isBadRequest());
//    }
//
//    @DisplayName("Workplace was selected with RoomId and Summary Page is available with model Attribute")
//    @Test
//    public void test_10() throws Exception{
//
//        WorkPlaceDTO workPlace = WorkPlaceBuilder.init()
//                        .withId(1l)
//
//                        .build();
//
//
//        when(workPlaceService.getWorkPlaceById(any())).thenReturn(workPlace);
//
//        BookingDTO existingBooking = BookingBuilder.init()
//                .withDateTimeFrom(LocalDateTime.now())
//                .withDateTimeTo(LocalDateTime.now().plusHours(2))
//                .withEquipments(new HashSet<>(Arrays.asList(Equipment.DOCKINGSTATION)))
//                .build();
//
//        mockMvc.perform(RequestBookingBuilder.getSummaryPage(workPlace.getId(), existingBooking)
//                        .build())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("booking",existingBooking));
//    }
//
//}
