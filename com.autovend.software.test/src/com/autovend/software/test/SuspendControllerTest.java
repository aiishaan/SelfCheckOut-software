package com.autovend.software.test;
import com.autovend.software.controllers.SuspendController;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SuspendControllerTest {
    private SuspendController station;

    @Before
    public void setUp() {
        station = new SuspendController();
    }

    @Test
    public void testInitialSuspendedStatus() {
        assertTrue(station.isSuspended());
    }

    @Test
    public void testSuspend() {
        station.suspend();
        assertTrue(station.isSuspended());
    }

    @Test
    public void testUnsuspend() {
        station.suspend();
        station.unsuspend();
        assertFalse(station.isSuspended());
    }

    @Test
    public void testUnsuspendWhenNotSuspended() {
        station.unsuspend();
        assertFalse(station.isSuspended());
    }
}
