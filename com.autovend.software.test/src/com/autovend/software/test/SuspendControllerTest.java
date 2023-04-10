package com.autovend.software.test;
import com.autovend.software.controllers.SuspendController;

import org.junit.Test;

import com.autovend.software.controllers.SuspendController.Station;

import static org.junit.Assert.*;

public class SuspendControllerTest {

    private SuspendController suspendController;

    @Test
    public void testStationSetUp() {
        SuspendController suspendController = new SuspendController(null);
        Station testStation = suspendController.new Station("Test Station" , false);
        suspendController = new SuspendController(testStation);
        suspendController.stationSetUp();
        assertTrue(suspendController.isSuspended());
    }

    // Testing when station is not in used by any customer and is not currently suspended
    @Test
    public void testSuspend() {
            SuspendController suspendController = new SuspendController(null);
            Station testStation = suspendController.new Station("Test Station" , false);
            suspendController = new SuspendController(testStation);
            suspendController.suspend();
            assertTrue(suspendController.isSuspended());
    }

    //Trying to test if the station is already suspended.
    @Test
    public void testAlreadySuspend() {
        SuspendController suspendController = new SuspendController(null);
        Station testStation = suspendController.new Station("Test Station" , false);
        suspendController = new SuspendController(testStation);
        suspendController.suspended = true;
        suspendController.suspend();
        assertFalse(suspendController.isSuspended());
    }

    //Testing when the station is not suspened and is in used by customer.
    @Test
    public void testSuspendCustomerInUse() {
        SuspendController suspendController = new SuspendController(null);
        Station testStation = suspendController.new Station("Test Station" , true);
        suspendController = new SuspendController(testStation);
        suspendController.suspend();
        assertFalse(suspendController.isSuspended());
    }






}