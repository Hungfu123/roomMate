package com.roommate.snakewatchers.domain.model;

public enum Equipment {

    DOCKINGSTATION("Dockingstation"),
    DOCKINGSTATION_USBC("Dockingstation mit USB-C"),
    MONITOR("Montior"),
    TABLE_ADJUSTABLE("Höherverstellbarer Tisch"),
    WEBCAM("Webcam"),
    MAUS("Maus"),
    TASTATUR("Tastatur"),
    HEADSET("Headset");

    public final String displayName;

    Equipment(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
