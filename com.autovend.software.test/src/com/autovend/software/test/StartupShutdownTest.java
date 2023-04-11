package com.autovend.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import com.autovend.devices.SelfCheckoutStation;
import com.autovend.software.controllers.AttendantController;
import com.autovend.software.controllers.CheckoutController;

public class StartupShutdownTest {

	private Currency currency = Currency.getInstance("CAD");
	private int[] billDenom = {1,5};
	private BigDecimal bd = new BigDecimal(1);
	private BigDecimal[] coinDenom = {bd};
	private SelfCheckoutStation scs = new SelfCheckoutStation(currency, billDenom, coinDenom, 1, 1);
	private CheckoutController checkoutController = new CheckoutController(scs);
	private AttendantController attendantController = new AttendantController("Bob", "69");
	
	
	@Test
	public void shutdownTest() {
		attendantController.attendantStationStartup(checkoutController, scs);
		attendantController.attendantStationShutdown(checkoutController, scs);
		boolean test = scs.baggingArea.isDisabled();
		assertTrue(test);
	}
	
	@Test
	public void startupTest() {
		attendantController.attendantStationShutdown(checkoutController, scs);
		attendantController.attendantStationStartup(checkoutController, scs);
		boolean test = scs.baggingArea.isDisabled();
		assertFalse(test);
	}
	
}
