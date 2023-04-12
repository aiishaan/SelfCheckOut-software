package com.autovend.software.test;

import com.autovend.software.controllers.SuspendController;
import org.junit.Test;
import org.junit.Before;
import com.autovend.software.controllers.SuspendController.Station;
import static org.junit.Assert.*;

public class SuspendControllerTest {

    private SuspendController suspendController;

    /**
	 * Set up work station and suspendController object 
	 */
    @Before 
    public void setUpTests() {
    	suspendController = new SuspendController(null);
    	Station station = suspendController.new Station("Test Station" , false);
    	suspendController = new SuspendController(station);
    	suspendController.stationSetUp(); 
    }
    
    /**
     * Test if the method stationSeetUp() actually sets the field suspended to true 
     * Expected: public field suspended should be true after calling this method 
     */
    @Test
    public void testStationSetUp() {
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
    
     /**
     * Test if the method unSuspend() sets the boolean value suspended to false if it is currently true 
     * Expected: public field suspended should be false after calling this method 
     */
    @Test 
    public void unSuspend_Suspended_Test() {
    	suspendController.unsuspend();
    	assertFalse(suspendController.isSuspended());
    }
    
    /**
     * Test if the method unSuspend() does not change the boolean value suspended if it is currently false 
     * Expected: public field suspended should be false after calling this method 
     */
    @Test 
    public void UnSuspend_NotSuspended_Test() {
    	suspendController.suspended = false; 
    	assertFalse(suspendController.isSuspended());
    }
    

}
