package edu.eci.dosw.tdd.skyrescue.mission;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import java.time.LocalDateTime;

public class Mission {

    private final String id;
    private final String location;
    private final int distanceKm;
    private final Drone drone;
    private final RescueOperator operator;
    private final LocalDateTime startDate;

    private LocalDateTime endDate;
    private MissionStatus status;

    public Mission(
            String id,
            String location,
            int distanceKm,
            Drone drone,
            RescueOperator operator,
            LocalDateTime startDate,
            MissionStatus status) {
        this.id = id;
        this.location = location;
        this.distanceKm = distanceKm;
        this.drone = drone;
        this.operator = operator;
        this.startDate = startDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getDistanceKm() {
        return distanceKm;
    }

    public Drone getDrone() {
        return drone;
    }

    public RescueOperator getOperator() {
        return operator;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }
}