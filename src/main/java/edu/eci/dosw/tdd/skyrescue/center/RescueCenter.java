package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

/**
 * Coordinates drones, operators and emergency missions.
 */
public class RescueCenter {

    private final List<RescueOperator> operators;
    private final Map<String, Drone> drones;
    private final List<Mission> missions;

    public RescueCenter() {
        this.operators = new ArrayList<>();
        this.drones = new HashMap<>();
        this.missions = new ArrayList<>();
    }

    /**
     * Registers a drone in the rescue center.
     *
     * Rules:
     * - The drone cannot be null.
     * - The drone id cannot be null or blank.
     * - Two drones cannot have the same id.
     * - A valid drone is stored as available.
     *
     * @param drone drone to register.
     * @return true if it was registered; false otherwise.
     */
   public boolean addDrone(Drone drone) {
    if (isInvalid(drone) || drones.containsKey(drone.getId())) {
        return false;
    }
    drones.put(drone.getId(), drone);
    return true;}
    
    private boolean isInvalid(Drone drone) {
        return drone == null || drone.getId() == null || drone.getId().isBlank();
    }

    /**
     * Assigns an emergency mission to an operator and an available drone.
     *
     * Rules:
     * - operatorId, droneId and location must be valid.
     * - The operator must exist.
     * - The drone must exist and be available.
     * - distanceKm must be greater than zero.
     * - distanceKm cannot exceed the drone maxRangeKm.
     * - The same operator cannot have two ACTIVE missions.
     * - On success, create an ACTIVE mission with the current date.
     * - On success, the selected drone becomes unavailable.
     * - The created mission must be stored in the center.
     *
     * Suggested error policy:
     * - Invalid/nonexistent data -> IllegalArgumentException.
     * - Valid resource but invalid state -> IllegalStateException.
     *
     * @param operatorId operator identifier.
     * @param droneId drone identifier.
     * @param location emergency location description.
     * @param distanceKm mission distance in kilometers.
     * @return created mission.
     */
    public Mission assignMission(
        String operatorId,
        String droneId,
        String location,
        int distanceKm) {

    RescueOperator operator = findOperator(operatorId);
    Drone drone = drones.get(droneId);

    validateAssignment(operator, operatorId, drone, droneId, distanceKm);

    Mission mission = new Mission(
            generateMissionId(),
            location,
            distanceKm,
            drone,
            operator,
            LocalDateTime.now(),
            MissionStatus.ACTIVE);

    drone.setAvailable(false);
    missions.add(mission);

    return mission;
}

private void validateAssignment(
        RescueOperator operator,
        String operatorId,
        Drone drone,
        String droneId,
        int distanceKm) {

    if (operator == null) {
        throw new IllegalArgumentException("Operator does not exist: " + operatorId);
    }
    if (drone == null) {
        throw new IllegalArgumentException("Drone does not exist: " + droneId);
    }
    if (!drone.isAvailable()) {
        throw new IllegalStateException("Drone is already busy: " + droneId);
    }
    if (distanceKm <= 0 || distanceKm > drone.getMaxRangeKm()) {
        throw new IllegalArgumentException("Invalid distance for this drone: " + distanceKm);
    }
    if (hasActiveMission(operatorId)) {
        throw new IllegalStateException("Operator already has an active mission: " + operatorId);
    }
}

    private boolean hasActiveMission(String operatorId) {
        return missions.stream()
        .anyMatch(m -> m.getOperator().getId().equals(operatorId) && m.getStatus() == MissionStatus.ACTIVE);
    }

    private RescueOperator findOperator(String operatorId) {
    return operators.stream()
            .filter(op -> op.getId().equals(operatorId))
            .findFirst()
            .orElse(null);}
            
    private String generateMissionId() {
        return "M" + (missions.size() + 1);
    }

    /**
     * Completes an active mission.
     *
     * Rules:
     * - missionId must be valid.
     * - The mission must exist.
     * - An already COMPLETED mission cannot be completed again.
     * - The mission status changes to COMPLETED.
     * - The end date is the current date/time.
     * - The drone assigned to the mission becomes available again.
     *
     * Suggested error policy:
     * - Invalid/nonexistent mission -> IllegalArgumentException.
     * - Mission already completed -> IllegalStateException.
     *
     * @param missionId mission identifier.
     * @return completed mission.
     */
    public Mission completeMission(String missionId) {
        // TODO Implement using TDD.
        return null;
    }

    public boolean addOperator(RescueOperator operator) {
        return operators.add(operator);
    }
}