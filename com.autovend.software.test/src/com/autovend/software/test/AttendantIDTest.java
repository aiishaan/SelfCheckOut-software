package com.autovend.software.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.SimulationException;
import com.autovend.software.controllers.AttendantController;
import com.autovend.software.controllers.CheckoutController;

//Contains 

public class AttendantIDTest{
	Currency c = Currency.getInstance(Locale.CANADA);
	int[] BillDenomiantions = {5, 10, 20, 50, 100};
	BigDecimal[] coinDenominations = {new BigDecimal("0.05"), new BigDecimal("0.1"), new BigDecimal("0.25"), new BigDecimal("0.5"), new BigDecimal("1"), new BigDecimal("2")};
	
	
	SelfCheckoutStation station = new SelfCheckoutStation(c, BillDenomiantions, coinDenominations, 1000000, 1);
	
	// Failed to Log in as the user does not exist
	@Test (expected = SimulationException.class)
	public void failed_Log_in() {
		// Creating Station Instance for Test 1
		CheckoutController stationTest1= new CheckoutController(station);
		// Adding New Attendant
		stationTest1.Attendant.add_attendant("Samuel", "oper14xj65");
		// Calling Log in
		stationTest1.Log_in_Attendant("Tom", "3523rq12ui3r");
		
	}
	// Successful Log in with correct credentials
	
	@Test 
	public void Successful_Log_in(){
		// Creating Station Instance for Test 1
		CheckoutController stationTest2= new CheckoutController(station);
		// Adding New Attendant
		stationTest2.Attendant.add_attendant("Samuel", "oper14xj65");
		// Calling Log in
		stationTest2.Log_in_Attendant("Samuel", "oper14xj65");
		assertTrue(stationTest2.Log_in_Status);
		
	}

	//Check when the user is already logged in Fails
	@Test (expected = SimulationException.class)
	public void Overlapping_Log_in(){
		// Creating Station Instance for Test 1
		CheckoutController stationTest3= new CheckoutController(station);
		// Adding New Attendant
		stationTest3.Attendant.add_attendant("Samuel", "oper14xj65");
		stationTest3.Attendant.add_attendant("Babar", "52618");
		
		// Calling Log in
		stationTest3.Log_in_Attendant("Samuel", "oper14xj65");
		stationTest3.Log_in_Attendant("Babar", "52618");
		
		
	}
	
}
