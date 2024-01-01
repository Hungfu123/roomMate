package com.roommate.snakewatchers.domain.model;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private Long bookingID;
    private Long workplaceId;
    private Long roomId;
    private String roomName;
//    private Long userId;
    private LocalDateTime dateTimeFrom;
    private LocalDateTime dateTimeTo;

    private Set<Equipment> equipments;
    private String userName;

    // Weil noch keine Datenbank haben, nutzen wir die selbsterstellte Klasse RandomID die uns einfach Ids generiert, wenn wir eine Buchung bestätigen
//    public Booking() {
//        this.bookingID = generateRandomLongId();
//        this.workingSpaceId = null;
//        this.roomId = null;
//        this.roomName = null;
//        this.userId = userId;
//        this.dateTimeFrom = dateTimeFrom;
//        this.dateTimeTo = dateTimeTo;
//        this.equipments = equipments;
//    }


    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingID=" + bookingID +
//                ", roomID=" + roomId +
//                ", roomName=" + roomName +
//                ", workingSpaceId=" + workplaceId +
                ", dateTimeFrom=" + dateTimeFrom +
                ", dateTimeTo=" + dateTimeTo +
                ", equipmentSet=" + equipments +
                '}';
    }
}
