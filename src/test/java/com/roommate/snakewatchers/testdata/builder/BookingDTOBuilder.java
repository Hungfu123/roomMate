package com.roommate.snakewatchers.testdata.builder;


import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.model.Equipment;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingDTOBuilder {

    private Long bookingID = 1L;
    private Long workingSpaceId = 2L;
    private Long roomId = 2L;
    private String roomName = "Python";
    private Long userId;
    private LocalDateTime dateTimeFrom = LocalDateTime.now().plusDays(1);
    private LocalDateTime dateTimeTo = LocalDateTime.now().plusDays(2);
    private Set<Equipment> equipments;
    private String userName;

    public BookingDTOBuilder() {
    }

    public static BookingDTOBuilder init() {
        return new BookingDTOBuilder();
    }

    public BookingDTOBuilder withBookingID(Long bookingID) {
        this.bookingID = bookingID;
        return this;
    }

    public BookingDTOBuilder withWorkingSpaceId(Long workingSpaceId) {
        this.workingSpaceId = workingSpaceId;
        return this;
    }

    public BookingDTOBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public BookingDTOBuilder withRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }
    public BookingDTOBuilder withRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public BookingDTOBuilder withDateTimeFrom(LocalDateTime dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
        return this;
    }

    public BookingDTOBuilder withDateTimeTo(LocalDateTime dateTimeTo) {
        this.dateTimeTo = dateTimeTo;
        return this;
    }

    public BookingDTOBuilder withEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
        return this;
    }
    public BookingDTOBuilder withEquipments(Equipment... equipment) {
        this.equipments.addAll(equipments);
        return this;
    }
    public BookingDTOBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public BookingDTO build() {
        return new BookingDTO(
                this.bookingID,
                this.workingSpaceId,
                this.roomId, this.roomName,
                this.userId, this.dateTimeFrom,
                this.dateTimeTo,
                this.equipments,
                this.userName);
    }
}
