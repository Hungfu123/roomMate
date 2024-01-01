package com.roommate.snakewatchers.testdata;


import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import com.roommate.snakewatchers.domain.model.Equipment;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingBuilder {

    private Long bookingID;
    private Long workingSpaceId;
    private Long roomId;
    private String roomName;
    private Long userId;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;
    private Set<Equipment> equipments;

    private String userName;

    public BookingBuilder() {
        this.bookingID = null;
        this.workingSpaceId = null;
        this.roomId = null;
        this.roomName = null;
        this.userId = null;
        this.dateTimeFrom = null;
        this.dateTimeTo = null;
        this.equipments = null;
    }

    public static BookingBuilder init() {
        return new BookingBuilder();
    }

    public BookingBuilder withBookingID(Long bookingID) {
        this.bookingID = bookingID;
        return this;
    }

    public BookingBuilder withWorkingSpaceId(Long workingSpaceId) {
        this.workingSpaceId = workingSpaceId;
        return this;
    }

    public BookingBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public BookingBuilder withRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }
    public BookingBuilder withRoomId(Long roomId) {
        this.roomId = roomId;
        return this;
    }

    public BookingBuilder withDateTimeFrom(LocalDateTime dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
        return this;
    }

    public BookingBuilder withDateTimeTo(LocalDateTime dateTimeTo) {
        this.dateTimeTo = dateTimeTo;
        return this;
    }

    public BookingBuilder withEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
        return this;
    }
    public BookingBuilder withUserName(String userName) {
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
