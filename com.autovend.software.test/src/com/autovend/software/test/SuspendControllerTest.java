package com.autovend.software.test;
import com.autovend.software.controllers.SuspendController;

import org.junit.Test;

import sevenWonder.SuspendController.Station;

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

}