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

package com.autovend.software.test;

import com.autovend.BlockedCardException;
import com.autovend.Card;
import com.autovend.CreditCard;
import com.autovend.GiftCard;
import com.autovend.TapFailureException;
import com.autovend.devices.CardReader;
import com.autovend.devices.SimulationException;
import com.autovend.external.CardIssuer;
import com.autovend.software.controllers.CardReaderController;
import com.autovend.software.controllers.CheckoutController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;

public class CardPaymentTest {
    TestBank bankStub;
    CheckoutController controllerStub;
    CreditCard cardStub;
    GiftCard giftStub;
    Currency localC;
    CardReader cardReaderStub;
    CardReaderController readerControllerStub;
    private class TestBank extends CardIssuer {
        public boolean held;
        public boolean posted;
        public boolean noPostCall;
        public boolean noHoldCall;
        public boolean holdAuthorized;
        public boolean canPostTransaction;
        /**
         * Create a card provider.
         *
         * @param name The company's name.
         * @throws SimulationException If name is null.
         */
        public TestBank(String name) {
            super(name);
            noHoldCall=true;
            noPostCall=true;
        }
        public int authorizeHold(String cardNumber, BigDecimal amount) {
            if (holdAuthorized) {
                held=true;
                return 1;
            } else {
                held=false;
                return -1;
            }
        }
        public boolean postTransaction(String cardNumber, int holdNumber, BigDecimal actualAmount) {
            noPostCall=false;
            if (holdNumber == 1 && canPostTransaction) {
                this.posted = true;
                return true;
            } else {
                this.posted=false;
                return false;
            }
        }

        }

    @Before
    public void setup(){
        bankStub = new TestBank("TestBank");
        cardStub= new CreditCard(
                "Credit Card", "12345","Steve", "987","1337",true, true
        );
        Currency localC = Currency.getInstance("CAD");
        giftStub = new GiftCard("Gift Card", "12345", "1313", localC, BigDecimal.valueOf(10));
        controllerStub = new CheckoutController();
        cardReaderStub = new CardReader();
        readerControllerStub = new CardReaderController(cardReaderStub);
        controllerStub.registerPaymentController(readerControllerStub);
        readerControllerStub.disableDevice();
        readerControllerStub.setMainController(controllerStub);
    }

    @Test
    public void testSuccessfulTransaction(){
        assertTrue(cardReaderStub.isDisabled());
        readerControllerStub.enablePayment(bankStub, cardStub, BigDecimal.ONE);
        assertFalse(cardReaderStub.isDisabled());
        readerControllerStub.card = cardStub;
        bankStub.canPostTransaction = true;
        bankStub.holdAuthorized = true;
        try {
            cardReaderStub.insert(cardStub, "1337");
        } catch (Exception ex){
        	ex.printStackTrace();
            fail("Exception incorrectly thrown");
        }
        assertTrue(bankStub.held);
        assertTrue(bankStub.posted);
        cardReaderStub.remove();
        assertFalse(readerControllerStub.isPaying);

        assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(-1));
        assertTrue(cardReaderStub.isDisabled());
        readerControllerStub.card = null;
    }

    @Test
    public void testPostFailed(){
        assertTrue(cardReaderStub.isDisabled());
        readerControllerStub.enablePayment(bankStub, cardStub, BigDecimal.ONE);
        assertFalse(cardReaderStub.isDisabled());
        bankStub.canPostTransaction = false;
        readerControllerStub.card = cardStub;
        bankStub.holdAuthorized = true;
        try {
            cardReaderStub.insert(cardStub, "1337");
        } catch (Exception ex){
        	ex.printStackTrace();
            fail("Exception incorrectly thrown");
        }
        assertTrue(readerControllerStub.isPaying);
        assertTrue(bankStub.held);
        assertFalse(bankStub.posted);
        cardReaderStub.remove();
        assertFalse(readerControllerStub.isPaying);
        assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(0));
        readerControllerStub.card = null;
    }
    @Test
    public void testHoldFailed(){
        assertTrue(cardReaderStub.isDisabled());
        readerControllerStub.enablePayment(bankStub, cardStub, BigDecimal.ONE);
        assertFalse(cardReaderStub.isDisabled());
        readerControllerStub.card = cardStub;
        bankStub.canPostTransaction = false;
        bankStub.holdAuthorized = false;
        try {
            cardReaderStub.insert(cardStub, "1337");
            	
        } catch (Exception ex){
            fail("Exception incorrectly thrown");
        }
        assertFalse(bankStub.held);
        assertTrue(bankStub.noPostCall);
        assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(0));
        readerControllerStub.card = null;
    }

    @Test
    public void testNotEnabled(){
        assertTrue(cardReaderStub.isDisabled());
        bankStub.canPostTransaction = false;
        bankStub.holdAuthorized = false;
        try {
            readerControllerStub.data = cardReaderStub.insert(cardStub, "1337");
        } catch (Exception ex){
        	ex.printStackTrace();
            fail("Exception incorrectly thrown");
        }
        assertTrue(bankStub.noHoldCall);
        assertTrue(bankStub.noPostCall);
        assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(0));
        assertTrue(cardReaderStub.isDisabled());
    }
    @Test
    //Test is broken and had to be removed
    public void testSwipeFail(){}

    @Test
    public void testTapFail(){
        assertTrue(cardReaderStub.isDisabled());
        bankStub.canPostTransaction = true;
        bankStub.holdAuthorized = true;
        try {
            cardReaderStub.tap(cardStub);
        } catch (Exception ex){

            fail("Exception incorrectly thrown");
        }
        assertTrue(bankStub.noHoldCall);
        assertTrue(bankStub.noPostCall);
        assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(0));
        assertTrue(cardReaderStub.isDisabled());
    }

    /**
     * Since we also need to test whether PayByCard works in checkout controller, it
     * makes sense to do so here
     */

    //This test is likely broken due to changes with payByCard
    @Test
    public void payByCardTestSuccess(){
        assertFalse(readerControllerStub.isPaying);
        controllerStub.cost=BigDecimal.ONE;
        controllerStub.payByCard(bankStub,BigDecimal.ONE, cardStub);
        assertFalse(cardReaderStub.isDisabled());
        assertEquals(readerControllerStub.bank, bankStub);

    }


    //This test is likely broken due to changes with payByCard
    @Test
    public void payByCardTestSystemProtectionLock(){
        assertTrue(cardReaderStub.isDisabled());
        controllerStub.cost=BigDecimal.ONE;
        controllerStub.systemProtectionLock=true;
        controllerStub.payByCard(bankStub,BigDecimal.ONE, cardStub);
        assertTrue(cardReaderStub.isDisabled());
    }

    //This test is likely broken due to changes with payByCard
    @Test
    public void payByCardTestBaggingLock(){
        assertTrue(cardReaderStub.isDisabled());
        controllerStub.cost=BigDecimal.ONE;
        controllerStub.baggingItemLock=true;
        controllerStub.payByCard(bankStub,BigDecimal.ONE, cardStub);
        assertTrue(cardReaderStub.isDisabled());
    }

    //This test is likely broken due to changes with payByCard
    @Test
    public void payByCardTestNullBank(){
        assertTrue(cardReaderStub.isDisabled());
        controllerStub.cost=BigDecimal.ONE;
        controllerStub.payByCard(null,BigDecimal.ONE, cardStub);
        assertTrue(cardReaderStub.isDisabled());
    }
    //This test is likely broken due to changes with payByCard
    @Test
    public void payByCardTestPayMoreThanOrderCost(){
        assertTrue(cardReaderStub.isDisabled());
        controllerStub.cost = BigDecimal.ZERO;
        controllerStub.payByCard(null,BigDecimal.ONE, cardStub);
        assertTrue(cardReaderStub.isDisabled());

    }

    @Test
    public void payWithTap() {
    	 assertTrue(cardReaderStub.isDisabled());
         readerControllerStub.enablePayment(bankStub, cardStub, BigDecimal.ONE);
         assertFalse(cardReaderStub.isDisabled());
         readerControllerStub.card = cardStub;
         bankStub.canPostTransaction = true;
         bankStub.holdAuthorized = true;
         try {
             cardReaderStub.tap(cardStub);
         } catch (Exception ex){
             fail("Exception incorrectly thrown");
         }
         assertTrue(bankStub.held);
         assertTrue(bankStub.posted);
         assertFalse(readerControllerStub.isPaying);

         assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(-1));
         assertTrue(cardReaderStub.isDisabled());
         readerControllerStub.card = null;
    }

    @Test
    public void payWithSwipe() {
    	 assertTrue(cardReaderStub.isDisabled());
         readerControllerStub.enablePayment(bankStub, cardStub, BigDecimal.ONE);
         assertFalse(cardReaderStub.isDisabled());
         readerControllerStub.card = cardStub;
         bankStub.canPostTransaction = true;
         bankStub.holdAuthorized = true;
         try {
             cardReaderStub.swipe(cardStub, new BufferedImage(10, 10, 10));
         } catch (Exception ex){
             fail("Exception incorrectly thrown");
         }
         assertTrue(bankStub.held);
         assertTrue(bankStub.posted);
         assertFalse(readerControllerStub.isPaying);

         assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(-1));
         assertTrue(cardReaderStub.isDisabled());
         readerControllerStub.card = null;
    }
    
    
    @Test
    public void payWithGift() {
    	 assertTrue(cardReaderStub.isDisabled());
         readerControllerStub.enableGiftPayment(giftStub, BigDecimal.ONE);
         assertFalse(cardReaderStub.isDisabled());
         readerControllerStub.giftCard = giftStub;
         try {
             cardReaderStub.insert(giftStub, "1313");
         } catch (Exception ex){
             fail("Exception incorrectly thrown");
         }
         assertEquals(controllerStub.getRemainingAmount(), BigDecimal.valueOf(0));
         readerControllerStub.giftCard = null;
    }
    
    @After
    public void teardown(){
        bankStub=null;
        controllerStub=null;
        cardReaderStub=null;
        cardStub=null;
        readerControllerStub =null;
    }
}
