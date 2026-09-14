package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RescueCenterTest {

    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("D1", "Falcon", 50);

        boolean result = center.addDrone(drone);

        assertTrue(result);
    }
}