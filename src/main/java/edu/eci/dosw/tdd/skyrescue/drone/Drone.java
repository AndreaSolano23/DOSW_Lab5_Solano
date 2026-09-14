package edu.eci.dosw.tdd.skyrescue.drone;

import java.util.Objects;

public class Drone {

    private final String id;
    private final String model;
    private final int maxRangeKm;
    private boolean available;

    public Drone(String id, String model, int maxRangeKm) {
        this.id = id;
        this.model = model;
        this.maxRangeKm = maxRangeKm;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getMaxRangeKm() {
        return maxRangeKm;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Drone)) {
            return false;
        }
        Drone other = (Drone) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}