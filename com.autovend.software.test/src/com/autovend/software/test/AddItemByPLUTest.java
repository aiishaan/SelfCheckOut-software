/*
package com.autovend.software.test;

import com.autovend.products.PLUCodedProduct;
import com.autovend.software.controllers.CheckoutController;
import com.autovend.software.controllers.ItemAdderController;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class AddItemByPLUTest {

    private CheckoutController checkoutController;
    private Map<String, PLUCodedProduct> pluProductDatabase;
    private StubItemAdderController stubItemAdderController;

    // Stub class for simulating the database
    public class StubDatabase {
        public final Map<String, PLUCodedProduct> PLU_PRODUCT_DATABASE = new HashMap<>();
    }

    // Stub class for ItemAdderController
    public class StubItemAdderController extends ItemAdderController {

        public StubItemAdderController() {
            super(null);
        }
    }

    @Before
    public void setUp() {
        pluProductDatabase = new HashMap<>();
        pluProductDatabase.put("1234", new PLUCodedProduct("1234", "Apple", new BigDecimal("1.99")));
        pluProductDatabase.put("5678", new PLUCodedProduct("5678", "Banana", new BigDecimal("0.99")));
        pluProductDatabase.put("9012", new PLUCodedProduct("9012", "Orange", new BigDecimal("1.49")));

        StubDatabase stubDatabase = new StubDatabase();
        stubDatabase.PLU_PRODUCT_DATABASE.putAll(pluProductDatabase);

        checkoutController = new CheckoutController(stubDatabase);
        stubItemAdderController = new StubItemAdderController();
    }

    @Test
    public void testAddItemByPLU() {
        String pluCode = "1234";
        BigDecimal expectedWeight = new BigDecimal("0.5");
        checkoutController.addItemByPLU(stubItemAdderController, pluCode, expectedWeight.toString());

        // Add assertions to validate the expected behavior
        // For example, check if the item is added to the bill or the expected weight is updated
    }

    @Test(expected = NoSuchElementException.class)
    public void testAddItemByPLUInvalidCode() {
        String invalidPLUCode = "1111";
        BigDecimal expectedWeight = new BigDecimal("0.5");
        checkoutController.addItemByPLU(stubItemAdderController, invalidPLUCode, expectedWeight.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemByPLUWithNegativeWeight() {
        String pluCode = "1234";
        BigDecimal negativeWeight = new BigDecimal("-0.5");
        checkoutController.addItemByPLU(stubItemAdderController, pluCode, negativeWeight.toString());
    }
}
*/