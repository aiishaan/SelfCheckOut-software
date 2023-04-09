package com.autovend.software.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.autovend.Barcode;
import com.autovend.BarcodedUnit;
import com.autovend.Numeral;
import com.autovend.PriceLookUpCode;
import com.autovend.PriceLookUpCodedUnit;
import com.autovend.devices.BarcodeScanner;
import com.autovend.devices.ElectronicScale;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;
import com.autovend.products.PLUCodedProduct;
import com.autovend.software.controllers.BarcodeScannerController;
import com.autovend.software.controllers.CheckoutController;
import com.autovend.software.controllers.ElectronicScaleController;

public class AddItemByTextSearchTest {
	//will test for add when in plu database
	//will test for add when in barcode database
	//will test for when in neither database
	
	
	private CheckoutController checkoutController;
	private BarcodeScannerController scannerController;
	private ElectronicScaleController scaleController;
	private BarcodedProduct databaseItem1;
	private PLUCodedProduct databaseItem2;
	private BarcodedUnit validUnit1;
	private PriceLookUpCodedUnit validUnit2;

	BarcodeScanner stubScanner;
	ElectronicScale stubScale;

	/**
	 * Setup for testing
	 */
	@Before
	public void setup() {
		checkoutController = new CheckoutController();
		scannerController = new BarcodeScannerController(new BarcodeScanner());
		scaleController = new ElectronicScaleController(new ElectronicScale(1000, 1));

		// First item to be scanned
		databaseItem1 = new BarcodedProduct(new Barcode(Numeral.three, Numeral.three), "milk",
				BigDecimal.valueOf(83.29), 359.0);

		// Second item to be scanned
		databaseItem2 = new PLUCodedProduct(new PriceLookUpCode(Numeral.four, Numeral.five), "bread",
				BigDecimal.valueOf(42));

		validUnit1 = new BarcodedUnit(new Barcode(Numeral.three, Numeral.three), 359.0);
		validUnit2 = new PriceLookUpCodedUnit(new PriceLookUpCode(Numeral.four, Numeral.five), 42.0);

		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(databaseItem1.getBarcode(), databaseItem1);
		ProductDatabases.PLU_PRODUCT_DATABASE.put(databaseItem2.getPLUCode(), databaseItem2);

		stubScanner = new BarcodeScanner();
		stubScale = new ElectronicScale(1000, 1);

		scannerController = new BarcodeScannerController(stubScanner);
		scannerController.setMainController(checkoutController);
		scaleController = new ElectronicScaleController(stubScale);
		scaleController.setMainController(checkoutController);

		stubScanner.register(scannerController);
		stubScale.register(scaleController);

	}

	/**
	 * Tears down objects so they can be initialized again with setup
	 */
	@After
	public void teardown() {
		checkoutController = null;
		scannerController = null;
		scaleController = null;
		stubScale = null;
		stubScanner = null;

	}
	
	/**
	 * Tests addItem by adding a barcoded Item
	 */
	@Test
	public void testAddItemInBarcodeDatabase() {

		// Adds item
		checkoutController.addItemByTextSearch(scannerController, "milk");

		// Adds the cost of the first item to the total
		BigDecimal total = databaseItem1.getPrice();

		// Checks that the item was added and the order was updated to 1
		assertEquals(1, checkoutController.getOrder().size());

		// Checks that the total cost was updated
		assertEquals(total, checkoutController.getCost());
	}
	
	/**
	 * Tests addItem by adding a plu-coded Item
	 */
	@Test
	public void testAddItemInPluDatabase() {

		// Adds item
		checkoutController.addItemByTextSearch(scannerController, "bread");

		// Adds the cost of the first item to the total
		BigDecimal total = databaseItem1.getPrice();

		// Checks that the item was added and the order was updated to 1
		assertEquals(1, checkoutController.getOrder().size());

		// Checks that the total cost was updated
		assertEquals(total, checkoutController.getCost());
	}
	
	/**
	 * Tests addItem by attempting to add an item that not in our database
	 */
	@Test(expected = NoSuchElementException.class)
	public void testAddItemInNeitherDatabase() {
		// Adds item
		checkoutController.addItemByTextSearch(scannerController, "orange");

	}
}
