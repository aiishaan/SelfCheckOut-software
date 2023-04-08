/*
SENG 300 Project Iteration 2
Group 7
Niran Malla 30086877
Saksham Puri 30140617
Fatema Chowdhury 30141268
Janet Tesgazeab 30141335
Fabiha Fairuzz Subha 30148674
Ryan Janiszewski 30148838
Umesh Oad 30152293
Manvi Juneja 30153525
Daniel Boettcher 30153811
Zainab Bari 30154224
Arie Goud 30163410
Amasil Rahim Zihad 30164830
*/

package com.autovend.software.controllers;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.ElectronicScale;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.software.controllers.*;

abstract class ItemAdderController<D extends AbstractDevice<O>, O extends AbstractDeviceObserver>
		extends DeviceController<D, O> {
	private CheckoutController mainController;

	public ItemAdderController(D newDevice) {
		super(newDevice);
	}

	public final CheckoutController getMainController() {
		return this.mainController;
	}

	public final void setMainController(CheckoutController newMainController) {
		if (this.mainController != null) {
			this.mainController.deregisterItemAdderController(this);
		}
		this.mainController = newMainController;
		if (this.mainController != null) {
			this.mainController.registerItemAdderController(this);
		}
	}
	
	public final void doNotAddItemToBaggingArea(ElectronicScale scale, double weight) {
		//lock from adding more items
		mainController.baggingItemLock = true;
		
		//TODO: System: Signals to the Attendant I/O that a no-bagging request is in progress.
		//TODO: Approve request (function created below)
		
		//if attendant approved, reduce expected weight
		if (mainController.AttendantApproved) {
			for (BaggingAreaController baggingController : mainController.getValidBaggingControllers()) {
				ElectronicScaleController scaleController = new ElectronicScaleController(scale);
				scaleController.removeAddedWeight(weight);
			}
		}
		//unlock system when done so they can continue adding items
		mainController.baggingItemLock = false;
		
	}
	
	public final void approveRequest() {
		mainController.AttendantApproved = true;
	}
	
	
}
