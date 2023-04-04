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

import com.autovend.Card;
import com.autovend.Card.CardData;
import com.autovend.TapFailureException;
import com.autovend.devices.CardReader;
import com.autovend.devices.observers.CardReaderObserver;
import com.autovend.external.CardIssuer;

import java.math.BigDecimal;

public class CardReaderController extends PaymentController<CardReader, CardReaderObserver>
		implements CardReaderObserver {
	public boolean isPaying;
	public boolean insertPayment;
	public boolean tapPayment;
	public boolean swipePayment;
	public Card card;
	public CardData data;
	String userPin;

	public CardReaderController(CardReader newDevice) {
		super(newDevice);
	}

	public CardIssuer bank;
	private BigDecimal amount;

	public void bankPayment(CardData localdata, CardIssuer localbank) {
		int holdNum = bank.authorizeHold(localdata.getNumber(), this.amount);
		if (holdNum !=-1 && (localbank.postTransaction(localdata.getNumber(), holdNum, this.amount))) {
			getMainController().addToAmountPaid(this.amount);
		}
	}
	
	public void tapPayment(Card card, CardData data) throws TapFailureException {
		if(!card.hasChip || !card.isTapEnabled) {
			throw new TapFailureException();
		}
		else {
			bankPayment(data, this.bank);
		}
		this.isPaying = false;
	}
	
	public void insertPayment(Card card, CardData data) {
		bankPayment(data, this.bank);
	}
	
	// TODO: Add Messages And Stuff
	@Override
	public void reactToCardInsertedEvent(CardReader reader) {
		this.isPaying = true;
		this.insertPayment = true;
	}

	@Override
	public void reactToCardRemovedEvent(CardReader reader) {
		this.isPaying = false;
	}

	@Override
	public void reactToCardTappedEvent(CardReader reader) {
		this.isPaying = true;
		this.tapPayment = true;
	}

	@Override
	public void reactToCardSwipedEvent(CardReader reader) {
		this.isPaying = true;
		this.swipePayment = true;
	}

	@Override
	public void reactToCardDataReadEvent(CardReader reader, Card.CardData data)  {
		this.data = data;
		if (reader != this.getDevice() || !this.isPaying || this.bank==null) {
			return;
		}
		if(this.tapPayment) {
			try {
				tapPayment(this.card, this.data);
			} catch (TapFailureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		if(this.insertPayment)
			insertPayment(this.card, this.data);
		this.disableDevice();
		this.amount = BigDecimal.ZERO;
		this.bank = null;
		// Clear bank and such if it fails to hold or not (might change this, I am tired
		// rn so might be dumb here)
	}

	public void enablePayment(CardIssuer issuer, BigDecimal amount) {
		this.enableDevice();
		this.bank = issuer;
		this.amount = amount;
	}
}
