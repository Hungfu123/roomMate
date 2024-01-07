package com.roommate.snakewatchers.domain.DTO;


import com.roommate.snakewatchers.domain.model.Equipment;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

import static com.roommate.snakewatchers.adapter.web.utils.RandomId.generateRandomLongId;

@Getter
@Setter
@AllArgsConstructor
// Customized Validation
@NoArgsConstructor
@NotBeforeStartDate
@NotBetweenLessOneHour
@Builder
public class BookingDTO {
    private Long bookingID;
    private Long workplaceId;
    private Long roomId;
    private String roomName;
    private Long userId;



    @NotNull(message = "Geben Sie bitte ihren Startdatum an!")
    @FutureOrPresent(message = "Startzeitdatum muss in der Gegenwart oder Zukunft liegen.")
    private LocalDateTime dateTimeFrom;

    @NotNull(message = "Geben Sie bitte ihren Enddatum an!")
    @Future(message = "Endzeitdatum muss in der Zukunft liegen.")
    private LocalDateTime dateTimeTo;

    //    @NotEmpty(message = "Wählen Sie bitte mindestens ein Equipment aus!")
    private Set<Equipment> equipments;

    private String userName;
    public void addEquipment(Set<Equipment> eq) {
        equipments.addAll(eq);
    }


    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingID=" + bookingID +
                ", roomID=" + roomId +
                ", roomName=" + roomName +
                ", workingSpaceId=" + workplaceId +
                ", dateTimeFrom=" + dateTimeFrom +
                ", dateTimeTo=" + dateTimeTo +
                ", equipmentSet=" + equipments +
                '}';
    }

}
