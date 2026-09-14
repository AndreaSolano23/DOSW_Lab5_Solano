package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RescueCenterTest {

    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("D1", "Falcon", 50);

        boolean result = center.addDrone(drone);

        assertTrue(result);
    }

    @Test
    void shouldNotRegisterNullDrone() {
    RescueCenter center = new RescueCenter();

    boolean result = center.addDrone(null);

    assertFalse(result);}

    @Test
    void shouldNotRegisterDroneWithBlankId() {
    RescueCenter center = new RescueCenter();
    Drone drone = new Drone("", "Falcon", 50);

    boolean result = center.addDrone(drone);

    assertFalse(result);}

    @Test
    void shouldNotRegisterDroneWithDuplicateId() {
    RescueCenter center = new RescueCenter();
    Drone firstDrone = new Drone("D1", "Falcon", 50);
    Drone secondDrone = new Drone("D1", "Phantom", 30);

    center.addDrone(firstDrone);
    boolean result = center.addDrone(secondDrone);

    assertFalse(result);}

    @Test
    void shouldAssignMissionWhenDataIsValid() {
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("D1", "Falcon", 50);
        RescueOperator operator = new RescueOperator("O1", "Ana");
        center.addDrone(drone);
        center.addOperator(operator);
        
        Mission mission = center.assignMission("O1", "D1", "Downtown", 30);
        assertEquals(MissionStatus.ACTIVE, mission.getStatus());
        assertFalse(drone.isAvailable());
    }

    @Test
    void shouldThrowExceptionWhenDroneDoesNotExist() {
        RescueCenter center = new RescueCenter();
        RescueOperator operator = new RescueOperator("O1", "Ana");
        center.addOperator(operator);
        
        assertThrows(IllegalArgumentException.class, () -> 
        center.assignMission("O1", "D-NOT-EXIST", "Downtown", 30));
    }

    @Test
    void shouldThrowExceptionWhenDroneIsAlreadyBusy() {
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("D1", "Falcon", 50);
        RescueOperator operator1 = new RescueOperator("O1", "Ana");
        RescueOperator operator2 = new RescueOperator("O2", "Luis");
        center.addDrone(drone);
        center.addOperator(operator1);
        center.addOperator(operator2);
        center.assignMission("O1", "D1", "Downtown", 30);
        
        assertThrows(IllegalStateException.class, () ->
        center.assignMission("O2", "D1", "Uptown", 20));
    }
}
