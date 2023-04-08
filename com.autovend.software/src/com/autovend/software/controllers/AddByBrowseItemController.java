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
	BarcodedProduct barcodedItem;
    double itemWeight;
    
	/*
	 * get browsed Item
	 * parameter(item name, quantity of items)
	 * 
	 */
	public void SelecetedProduct(String itemName) {
		for(BarcodedProduct product : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
			//if product exist in database
			if(product.getDescription().equals(itemName)) {
				barcodedItem = product;
				itemPrice = product.getPrice();	//price of item 
				itemWeight = product.getExpectedWeight(); //item weight 
			}
		}		
	}
	
	
	/*
	 * Event When item is added by browsing 
	 */
	public void AddByBrowsingEvent(String selectedItem) {
		String item = selectedItem; 
		
		SelecetedProduct(item);
		
		//if inputed item is not null
		if (barcodedItem != null) {
			//add item to order
			this.getMainController().addItem(this, barcodedItem, itemWeight);

		}
		//if item added does not exit through exception
		else {
			throw new SimulationException("Product does not exist");
		}
		this.getMainController().baggedItemsValid(null);
			
		
	}
	

}
