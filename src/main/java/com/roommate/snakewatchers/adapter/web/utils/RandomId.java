package com.roommate.snakewatchers.adapter.web.utils;

import com.roommate.snakewatchers.domain.model.Equipment;

import java.util.*;
import java.util.stream.Collectors;

public class RandomId {

    public static Long generateRandomLongId() {
        Random random = new Random();
        return random.nextLong(100) + 1;
    }
    public static Set<Equipment> generateRandomEquipments() {
        List<Equipment> allEquipments = Arrays.asList(Equipment.values());
        Random random = new Random();

        // Mindestens eine Ausrüstung auswählen
        int numberOfEquipments = random.nextInt(allEquipments.size()) + 1;

        // Zufällige Reihenfolge der Ausrüstungen mischen
        Collections.shuffle(allEquipments);

        // Nur die ersten numberOfEquipments Ausrüstungen auswählen
        return allEquipments.stream().collect(Collectors.toSet());
    }
}
