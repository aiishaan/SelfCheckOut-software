package com.autovend.software.controllers;

import com.autovend.devices.SelfCheckoutStation;

import java.util.HashMap;

public class AttendantController {
    // Handles the Operations of the attendant

    // Attendant user ID
    public String user_id;
    // Attendant Password
    public String password;

    // Attendant Lists for the Software
    public HashMap<String, String> AttendantList = new HashMap<String, String>();

    /**
     * Constructs an Attendant with user ID and Password
     *
     * @param username
     * @param key
     */
    public AttendantController(String username, String pass) {
        this.user_id = username;
        this.password = pass;
        // Adding Some Current Attendants to the List
        AttendantList.put("James", "4628");
        AttendantList.put("Wayne", "331");
        AttendantList.put("Shaw", "unux89");
        AttendantList.put("Pablo", "12345678");
    }

    /**
     * Adds the userID and password for a new attendant
     **/
    public void add_attendant(String username, String password) {

        AttendantList.put(username, password);

    }

    /**
     * Method to remove attendant
     **/

    public void remove_attendant(String username, String pass) {

        AttendantList.remove(username, pass);
    }

    // Getter method for Attendant ID
    public String getUser_id() {
        return user_id;
    }


    public void attendantStationStartup(CheckoutController checkoutController, SelfCheckoutStation scs) {
        checkoutController.stationStartup(scs);
    }

    public void attendantStationShutdown(CheckoutController checkoutController, SelfCheckoutStation scs) {
        checkoutController.stationShutdown(scs);
    }

}