package com.autovend.software.controllers;
import com.autovend.BarcodedUnit;
import com.autovend.devices.SelfCheckoutStation;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;

import java.math.BigDecimal;
import java.util.*;



public class AddByBrowseItemController {
	private SelfCheckoutStation selfCheckoutStation;
	List<BarcodedProduct> browsedItem = new ArrayList<BarcodedProduct>();
	BarcodedUnit BarcodedItem;
	BigDecimal itemPrice;
	
	
	/*
	 * get browsed Item 
	 * parameter(item name, quantity of items)
	 * set the value of barcoded item and the price (Barcode, expected weight)
	 */
	public void SelecetedProduct(String itemName, String quantity) {
		//store value of quantity wanted in var
		BigDecimal itemQuntity = new BigDecimal(quantity);
		for(BarcodedProduct product : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
			if(product.getDescription().equals(itemName)) {
				BarcodedItem = new BarcodedUnit(product.getBarcode(), product.getExpectedWeight());
				itemPrice = product.getPrice().multiply(itemQuntity);
			}
		}		
	}
	
	/*
	 * not sure if a method described below is need or its done thorugh the gui
	 */

	//selects a brocaded item
		//add item weight to expected weight  
		//signal to system that item has been added,  item info to display (gui?)
		//block system 
	
			//if( check item is bagging area = weight has changed as expected )
		
			//else if (weight has changed incorrect amount)

				//call attendant

		 
	/*
	 * Block Station method 
	 */
	public void blockStation() {
		//disable station 
		selfCheckoutStation.mainScanner.disable();
		selfCheckoutStation.handheldScanner.disable();
		selfCheckoutStation.printer.disable();
		selfCheckoutStation.billInput.disable();
		selfCheckoutStation.billOutput.disable();
		selfCheckoutStation.billStorage.disable();
		selfCheckoutStation.billValidator.disable();
		selfCheckoutStation.screen.disable();

	}
	
	/*
	 * UnBlock Station method 
	 */
	public void unBlockStation() {
		//enable station 
		selfCheckoutStation.mainScanner.enable();
		selfCheckoutStation.handheldScanner.enable();
		selfCheckoutStation.printer.enable();
		selfCheckoutStation.billInput.enable();
		selfCheckoutStation.billOutput.enable();
		selfCheckoutStation.billStorage.enable();
		selfCheckoutStation.billValidator.enable();
		selfCheckoutStation.screen.enable();
	}
	

}
