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

import com.autovend.IllegalDigitException;

public class MembershipCardController {
    public String membershipNumber;
    private final boolean isActive = false;
    // Did a max tries of 3, having a limit would help with like not having a
    // infinite input that is invalid,
    // and after the three invalid attempts it will return null

    public boolean getIsActive() {
        return this.isActive;
    }

    /*
     * The isValid method will first check if memberNUM is null or if its length is
     * not equal to 12. If any of those conditions are true then it will through the
     * exception "IllegalDigitException" and say that it needs to be exactly 12
     * digits long. Then it will use the Character.isDigit to check each digit in
     * memberNum and see if there are any non-digits and that it is a digit between
     * 0-9. If there is a non-digit it will throw the "IllegalDigitException" saying
     * that it should be a digit between 0-9.
     */

    public boolean isValid(String memberNum) throws IllegalDigitException {
        if (memberNum == null || memberNum.length() != 12) {
            throw new IllegalDigitException("The Membership number should be exactly 12 digits long.");
        }
        for (int i = 0; i < memberNum.length(); i++) {
            char c = memberNum.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalDigitException("The Membership number should only contain digits between 0-9.");
            }
        }
        return true;
    }
}