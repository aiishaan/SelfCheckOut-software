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

import com.autovend.BlockedCardException;
import com.autovend.Card;
import com.autovend.Card.CardData;
import com.autovend.ChipFailureException;
import com.autovend.GiftCard;
import com.autovend.GiftCard.GiftCardInsertData;
import com.autovend.TapFailureException;
import com.autovend.devices.CardReader;
import com.autovend.devices.observers.CardReaderObserver;
import com.autovend.external.CardIssuer;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CardReaderController extends PaymentController<CardReader, CardReaderObserver>
		implements CardReaderObserver {
	public boolean isPaying;
	public boolean insertPayment;
	public boolean tapPayment;
	public boolean swipePayment;
	public Card card;
	public GiftCardInsertData giftData;
	public CardData data;
	String userPin;
	public CardIssuer bank;
	private BigDecimal amount;
	public boolean paymentFailure;
	public boolean giftPayment = false;
	public GiftCard giftCard;
	public CardReaderController(CardReader newDevice) {
		super(newDevice);
	}


	/**
	 * This is a method that takes care of the bank side of the payment use case. Originally a part of reactToCardDataReadEvent, moved here to support other parts of the use case (tap/swipe).
	 * @param localdata Local card data, given from other payment methods.
	 * @param localbank Bank data, should also have been given from detected credit card.
	 * @throws BlockedCardException If something goes wrong with the transaction.
	 */
	public void bankPayment(CardData localdata, CardIssuer localbank) throws BlockedCardException {
		int holdNum = localbank.authorizeHold(localdata.getNumber(), this.amount);
		if (holdNum !=-1 && (localbank.postTransaction(localdata.getNumber(), holdNum, this.amount))) {
			getMainController().addToAmountPaid(this.amount);
		}
		else {
			throw new BlockedCardException();
		}
	}
	
	/**
	 * This method handles the main system side of the payment for gift cards.
	 * @param localdata Local card data, given from other payment methods.
	 * @throws ChipFailureException In case the gift card data can't be read, thrown.
	 */
	public void giftPayment (GiftCardInsertData localdata) throws ChipFailureException {
		BigDecimal balance = localdata.getRemainingBalance();
		if(balance.compareTo(amount) == 1 || balance.compareTo(amount) == 0){
			System.out.println("Made it to payment");
			localdata.deduct(amount);
			getMainController().addToAmountPaid(this.amount);
		}
		else {
			throw new ChipFailureException();
		}
	}
	
	/**
	 * This method handles a tap credit or debit payment.
	 * @param card The card tapped against the card reader.
	 * @param data Data from the tapped card.
	 * @throws TapFailureException If the tap fails, throw exception. Will likely communicate something when GUI is up.
	 * @throws BlockedCardException If the card is rejected by the bank, thrown.
	 */
	public void tapPayment(Card localCard, CardData localData) throws TapFailureException, BlockedCardException {
		if(!localCard.hasChip || !localCard.isTapEnabled) {
			throw new TapFailureException();
		}
		else {
			bankPayment(localData, this.bank);
		}
	}
	
	/**
	 * This method handles when a credit card is inserted into the card reader.
	 * @param card The card inserted into the card reader.
	 * @param data Data from the inserted card.
	 * @throws ChipFailureException If the chip isn't read properly, throw exception.
	 * @throws BlockedCardException If the card is rejected by the bank, thrown.
	 */
	public void insertPayment(Card localCard, CardData localData) throws ChipFailureException, BlockedCardException {
		if(!localCard.hasChip) {
			throw new ChipFailureException();
		}
		else {
			bankPayment(localData, this.bank);
		}
	}
	
	/**
	 * This method handles when a gift card is inserted into the card reader.
	 * @param localCard The gift card involved in the transaction.
	 * @param localData The data of the gift card.
	 * @throws ChipFailureException If the data on the gift card can't be read, thrown.
	 */
	public void insertGiftPayment(GiftCard localCard, GiftCardInsertData localData) throws ChipFailureException {
		giftPayment(localData);
	}
	
	/**
	 * This method handles when a credit card is swiped against the card reader.
	 * @param localCard The card inserted into the card reader.
	 * @param localData Data from the inserted card.
	 * @throws BlockedCardException If the card is rejected by the bank, thrown.
	 */
	public void swipePayment(Card localCard, CardData localData) throws BlockedCardException {
		bankPayment(localData, this.bank);
	}
	
	@Override
	public void reactToCardInsertedEvent(CardReader reader) {
		//Sets internal flag for insert payment.
		this.isPaying = true;
		this.insertPayment = true;
	}

	@Override
	public void reactToCardRemovedEvent(CardReader reader) {
		this.isPaying = false;
	}

	@Override
	public void reactToCardTappedEvent(CardReader reader) {
		//Sets internal flag for tap payment.
		this.isPaying = true;
		this.tapPayment = true;
	}

	@Override
	public void reactToCardSwipedEvent(CardReader reader) {
		//Sets internal flag for swipe payment.
		this.isPaying = true;
		this.swipePayment = true;
	}

	@Override
	public void reactToCardDataReadEvent(CardReader reader, Card.CardData data)  {
		this.isPaying = true;
		//Data is harvested from the card and saved to the reader.
		this.data = data;
		if(giftPayment) {
			giftData = (GiftCardInsertData) data;
		}
		
		if (reader != this.getDevice() || !this.isPaying || this.bank==null) {
			return;
		}
		//One of these three flags should have been set before this event happens.
		//Tap payment, set during cardTappedEvent.
		if(this.tapPayment && !this.giftPayment) {
			try {
				tapPayment(this.card, this.data);
			} catch (TapFailureException e) {
				// This will likely jump back to another method once GUI is set up, possible second payment attempt?
				paymentFailure = true;
			} catch (BlockedCardException e) {
				// This will likely jump back to another method once GUI is set up, possible second payment attempt?
				paymentFailure = true;
				return;
			}	
		}
		//Insert payment, set during cardInsertedEvent.
		if(this.insertPayment && !this.giftPayment) {
			try {
				insertPayment(this.card, this.data);
			} catch (ChipFailureException | BlockedCardException e) {
				// This will likely jump back to another method once GUI is set up, possible second payment attempt?
				paymentFailure = true;
				
				return;
			}
		}
		//Swipe payment, set during cardSwipedEvent
		if(this.swipePayment && !this.giftPayment) {
			try {
				swipePayment(this.card, this.data);
			} catch (BlockedCardException e) {
				// This will likely jump back to another method once GUI is set up, possible second payment attempt?
				paymentFailure = true;
			}
		}
		if(this.giftPayment) {
			try {
				insertGiftPayment(this.giftCard, giftData);
			} catch (ChipFailureException e) {
				// This will likely jump back to another method once GUI is set up, possible second payment attempt?
				paymentFailure = true;
			}
		}
		this.isPaying = false;
		this.insertPayment = false;
		this.swipePayment = false;
		this.tapPayment = false;
		this.disableDevice();
		this.bank = null;
		this.giftPayment = false;
	}

	/**
	 * This activates the card reader for payment.
	 * @param issuer Bank that issued the card 
	 * @param amount Amount to be paid.
	 */
	public void enablePayment(CardIssuer issuer, Card localCard, BigDecimal amount) {
		this.enableDevice();
		this.card = localCard;
		this.bank = issuer;
		this.amount = amount;
	}
	
	/**
	 * This activates the card reader for a gift cardpayment.
	 * @param localGift Gift card to be used for the transaction.
	 * @param amount Amount to be paid.
	 */
	public void enableGiftPayment(GiftCard localGift, BigDecimal amount) {
		this.enableDevice();
		this.giftCard = localGift;
		this.amount = amount;
		giftPayment = true;
		
	}
}
