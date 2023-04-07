package com.autovend.software.controllers;
import com.autovend.BarcodedUnit;
import com.autovend.devices.SimulationException;
import com.autovend.devices.TouchScreen;
import com.autovend.devices.observers.TouchScreenObserver;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;

import java.math.BigDecimal;
import java.util.*;



public class AddByBrowseItemController extends ItemAdderController<TouchScreen, TouchScreenObserver> implements TouchScreenObserver{
	
	public AddByBrowseItemController(TouchScreen newDevice) {
		super(newDevice);
		// TODO Auto-generated constructor stub
	}

	List<BarcodedProduct> browsedItem = new ArrayList<BarcodedProduct>();
	BarcodedUnit barcodedUnit;
	BigDecimal itemPrice;
	BigDecimal itemTotalPrice;
	BarcodedProduct barcodedItem;
    double itemWeight;
    
	/*
	 * get browsed Item
	 * parameter(item name, quantity of items) get these from gui
	 * set the value of barcoded item and the price (Barcode, expected weight)
	 */
	public void SelecetedProduct(String itemName, String quantity) {
		//store value of quantity wanted in var
		BigDecimal itemQuntity = new BigDecimal(quantity);
		for(BarcodedProduct product : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
			if(product.getDescription().equals(itemName)) {
				barcodedItem = product;
				itemPrice = product.getPrice();	//price of item 
				itemTotalPrice = product.getPrice().multiply(itemQuntity); //item price x quantity 
				itemWeight = product.getExpectedWeight(); //item weight 
			}
		}		
	}
	
	
	/*
	 * Event When item is added by browsing 
	 */
	public void AddByBrowsingEvent(String selectedItem, String SelectedQuantity) {
		
		//display visual catalog (gui)
			//selects product name (gui?) return itemName
		String item = selectedItem; 
		
		
			//and product quantity (gui?) return quantity
		String quantity = SelectedQuantity;
		
		//cancel browse option (gui)
			//return user back to original state prior to catalog screen 
		
		
		disableDevice();  // Blocks the self-checkout system from further customer interaction.
		//run the selectedProduct method to get info 
		SelecetedProduct(item, quantity);
		
		if (barcodedItem != null) {
			this.getMainController().addItem(this, barcodedItem, itemWeight);
			
		}
		else {
			throw new SimulationException("Null Product");
		}
		
		//signal to customer to place item in baggin area (gui)
		
		enableDevice();	//Unblocks the self-checkout system
		//return back to state prior to entering catalog (gui) 
	}
	

}
