package com.roommate.snakewatchers.applicationservice;

import com.roommate.snakewatchers.adapter.web.EquipmentService;
import com.roommate.snakewatchers.domain.model.Equipment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class EquipmentServiceImpl implements EquipmentService {


    Set<Equipment> equipmentSet = new HashSet<>();

    public Set<Equipment> findAll () {

        equipmentSet.addAll(Arrays.asList(Equipment.values()));

        return equipmentSet;
    }
    public Equipment findByName(String name) {
        for (Equipment equipment : equipmentSet) {
            if (equipment.getDisplayName().equalsIgnoreCase(name)) {
                return equipment;
            }
        }
        return null; // Return null if no match is found
    }
}
