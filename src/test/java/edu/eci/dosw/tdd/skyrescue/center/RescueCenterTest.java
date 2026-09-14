package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
}
