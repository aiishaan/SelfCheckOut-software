package com.autovend.software.controllers;

import com.autovend.devices.AbstractDevice;
import com.autovend.devices.observers.AbstractDeviceObserver;
import com.autovend.external.ProductDatabases;
import com.autovend.products.BarcodedProduct;

import java.math.BigDecimal;

public class AddItemByPLUController extends ItemAdderController<AbstractDevice<AbstractDeviceObserver>, AbstractDeviceObserver> {

    public AddItemByPLUController(AbstractDevice<AbstractDeviceObserver> newDevice) {
        super(newDevice);
    }

    BarcodedUnit barcodedUnit;
    BigDecimal itemPrice;
    BigDecimal itemTotalPrice;
    BarcodedProduct barcodedItem;
    double itemWeight;

    public void selectProductByPLU(String pluCode, String quantity) {
        BigDecimal itemQuantity = new BigDecimal(quantity);
        for (BarcodedProduct product : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
            if (product.getPLUCode().equals(pluCode)) {
                barcodedItem = product;
                itemPrice = product.getPrice();
                itemTotalPrice = product.getPrice().multiply(itemQuantity);
                itemWeight = product.getExpectedWeight();
            }
        }
    }

    public void addByPLUEvent(String enteredPLU, String selectedQuantity) {
        disableDevice();

        selectProductByPLU(enteredPLU, selectedQuantity);

        if (barcodedItem != null) {
            this.getMainController().addItem(this, barcodedItem, itemWeight);
        } else {
            throw new SimulationException("Invalid PLU code");
        }

        enableDevice();
    }
}