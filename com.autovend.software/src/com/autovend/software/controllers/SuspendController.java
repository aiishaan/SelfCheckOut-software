package com.autovend.software.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuspendController {
    public Station station;
    public boolean suspended; // Indicates if the station is currently suspended
    //Create a Station class for suspendController test.
    class Station {
        private String name;
        private boolean inUse;

        public Station(String name, boolean inUse) {
            this.name = name;
            this.inUse = inUse;
        }

        public String getName() {
            return name;
        }

        public boolean isInUse() {
            return inUse;
        }

        public void setInUse(boolean inUse) {
            this.inUse = inUse;
        }
    }


    // Constructor
    public SuspendController(Station station) {
        this.station = station;
        this.station.inUse=station.inUse;//set the inuse situation as false.
        this.suspended = false; // Initialize the station as not suspended before anything happens.
    }

    public void stationSetUp() {

        this.suspended = true; // Initialize the station as suspended
    }

    // Method to suspend the station
    public void suspend() {
        if (!suspended && !station.isInUse()) {
            // Check if the station is not already suspended and not in use
            System.out.println("The Station has been suspended.");
            suspended = true;
        } else if (suspended) {
            System.out.println("The Station is already suspended.");
        } else {
            //The system won't be suspended when user is in use.
            System.out.println("Cannot suspend the station when customer is using it.");
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
        Station Station1 = new Station("Station1" , true);
        Station Station2 = new Station("Station2" , false);
        Station Station3 = new Station("Station3" , true);

        stations.add(new SuspendController(Station1));
        stations.add(new SuspendController(Station2));
        stations.add(new SuspendController(Station3));


        //create a scanner class for
        Scanner scanner = new Scanner(System.in);
        // Attendant I/O: Display the list of stations for selection
        System.out.println("Select a station to suspend/unsuspend:");
        for (int i = 0; i < stations.size(); i++) {
            System.out.println((i + 1) + ". Station " + (i + 1));
        }

        // Attendant I/O: Get the selected station
        int selectedStation = 1; // Example: Attendant selects station 1
        System.out.println("Attendant selected Station " + selectedStation);

        //while loop for testing user input, if user enter suspend, can be changed when we apply GUI in the system.
        while (true) {
            System.out.println("Enter 'suspend' to suspend the process or 'unsuspend' to unsuspend the process:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("suspend")) {
                //invoke station that is getting suspended, in this case its station no.1 .
                SuspendController stationToSuspend = stations.get(selectedStation - 1);
                stationToSuspend.suspend();
            } else if (input.equalsIgnoreCase("unsuspend")) {
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
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }


    }
}