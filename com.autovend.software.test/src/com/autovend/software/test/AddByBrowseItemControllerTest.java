/**
 * 
 */
package com.autovend.software.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.autovend.Barcode;
import com.autovend.BarcodedUnit;
import com.autovend.Numeral;
import com.autovend.SellableUnit;
import com.autovend.devices.ElectronicScale;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.devices.TouchScreen;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;
import com.autovend.products.Product;
import com.autovend.software.controllers.AddByBrowseItemController;
import com.autovend.software.controllers.BarcodeScannerController;
import com.autovend.software.controllers.CheckoutController;
import com.autovend.software.controllers.ElectronicScaleController;

/**
 * @author hossa
 *
 */
public class AddByBrowseItemControllerTest {
	TouchScreen stubSreen; 
	ElectronicScale stubScale;
	
	private AddByBrowseItemController browseItemController; 
	private CheckoutController checkoutController;
	private ElectronicScaleController scaleController;
	

	BarcodedProduct testProduct;
	BigDecimal testprice = new BigDecimal("55.0");
	Barcode testBarcode;
	BarcodedUnit testProductUnit;
	SellableUnit testSellableProduct;
	int inventory = 5;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//eScale = new ElectronicScale(1000, 1);
		checkoutController = new CheckoutController();
		browseItemController = new AddByBrowseItemController(new TouchScreen());
		scaleController = new ElectronicScaleController(new ElectronicScale(1000,1));
		
		// add barcoded product
		testBarcode = new Barcode(Numeral.zero, Numeral.one, Numeral.two);
		testProduct = new BarcodedProduct(testBarcode, "Test Product", testprice, 10.0);
		testSellableProduct = new BarcodedUnit(testBarcode, 10.0);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(testBarcode, testProduct);	

		stubSreen = new TouchScreen();
		stubScale = new ElectronicScale(1000, 1);
		
		browseItemController.setMainController(checkoutController);
		scaleController = new ElectronicScaleController(stubScale);
		scaleController.setMainController(checkoutController);
		
		stubSreen.register(browseItemController);
		stubScale.register(scaleController);
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	
		testBarcode = null;
		testProduct = null;
		testSellableProduct = null;
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		
	}

	@Test
	public void test() {
		System.out.println("test");
		String Item = "Test Product";
		BigDecimal total = testProduct.getPrice();
		
		//add item by browse 
		browseItemController.AddByBrowsingEvent(Item);
		
		//test if item has been added to order 
		assertEquals(1, checkoutController.getOrder().size());
		//test if the cost order has updated 
		assertEquals(total, checkoutController.getCost());
		
		

		
	}

}
