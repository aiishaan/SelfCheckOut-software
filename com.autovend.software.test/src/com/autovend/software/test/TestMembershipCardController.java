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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.autovend.Card;
import com.autovend.MembershipCard;
import com.autovend.devices.CardReader;
import com.autovend.software.controllers.CardReaderController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.autovend.IllegalDigitException;
import com.autovend.software.controllers.MembershipCardController;

@SuppressWarnings("unused")

public class TestMembershipCardController {

	@Before
	public void setup() {
		membershipCard = new MembershipCard("Membership", "123123123123", "XZ", true);
	}

	MembershipCardController mcc = new MembershipCardController();
	Scanner scanner = new Scanner(System.in);
	InputStreamReader inputReader;
	CardReader cr = new CardReader();
	CardReaderController crc = new CardReaderController(cr);
	Card membershipCard;


	@After
	public void teardown() {
	}

	@Test
	public void testIsValidNullValue() throws IllegalDigitException {
		String expectedMessage = "The Membership number should be exactly 12 digits long.";
		Exception exception = assertThrows(IllegalDigitException.class, () -> mcc.isValid(null));
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	public void testIsValidLessDigits() throws IllegalDigitException {
		String expectedMessage = "The Membership number should be exactly 12 digits long.";
		Exception exception = assertThrows(IllegalDigitException.class, ()->mcc.isValid("123456"));
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testIsValidNan() throws IllegalDigitException {
		String expectedMessage = "The Membership number should only contain digits between 0-9.";
		Exception exception = assertThrows(IllegalDigitException.class,
				() -> mcc.isValid("abc234567890"));
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testIsValidReturnsTrue() {
		boolean expected = true;
		boolean actual = mcc.isValid("564823890124");
		assertEquals(expected, actual);
	}


}
