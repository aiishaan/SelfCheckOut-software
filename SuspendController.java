package com.autovend.software.controllers;

import java.util.ArrayList;
import java.util.List;

public class SuspendController {
    private boolean suspended; // Indicates if the station is currently suspended

    // Constructor
    public void Station() {
        this.suspended = true; // Initialize the station as suspended
    }

    // Method to suspend the station
    public void suspend() {
        if (!suspended) {
            System.out.println("Station has been suspended.");
            suspended = true;
        } else {
            System.out.println("Station is already suspended.");
        }
    }

    // Method to unsuspended the station after maintenance
    public void unsuspend() {
        if (suspended) {
            System.out.println("Station has been unsuspended and is ready for use.");
            suspended = false;
        } else {
            System.out.println("Station is not currently suspended.");
        }
    }

    // Method to check if the station is currently suspended
    public boolean isSuspended() {
        return suspended;
    }


    public static void main(String[] args) {
        // Create a list of stations
        List<SuspendController> stations = new ArrayList<>();
        stations.add(new SuspendController());
        stations.add(new SuspendController());
        stations.add(new SuspendController());

        // Attendant I/O: Display the list of stations for selection
        System.out.println("Select a station to unsuspend:");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". Station " + (i + 1));
        }

        // Attendant I/O: Get the selected station
        int selectedStation = 1; // Example: Attendant selects station 1
        System.out.println("Attendant selected Station " + selectedStation);

        // System: Communicate with the Customer I/O to unsuspended the selected station
        try {
        	SuspendController stationToUnsuspend = stations.get(selectedStation - 1);
            if (stationToUnsuspend.isSuspended()) {
                stationToUnsuspend.unsuspend();
                // Customer I/O: Ready for further customer interaction
                System.out.println("Station " + selectedStation + " is ready for customer interaction.");
            } else {
                System.out.println("Station " + selectedStation + " is not currently suspended.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid selection. Please try again.");
        } catch (Exception e) {
            System.out.println("Failed to unsuspend the station due to an error: " + e.getMessage());
        }
    }
}
