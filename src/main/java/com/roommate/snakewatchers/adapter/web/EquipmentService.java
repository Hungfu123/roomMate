package com.roommate.snakewatchers.adapter.web;

import com.roommate.snakewatchers.domain.model.Equipment;

import java.util.Set;

public interface EquipmentService {


    Set<Equipment> findAll();
    Equipment findByName(String name);
}
