package com.autovend.software.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuspendController {
    public Station station;
    public boolean suspended; // Indicates if the station is currently suspended

    //Create a Station class for suspendController test.
    public class Station {
        private String name;
        private boolean inUse = false;

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
        if (station == null) {
            this.station = new Station("", false);
        } else {
            this.station = station;
            this.station.inUse = station.inUse;
        }
        this.suspended = false; // Initialize the station as not suspended before anything happens.
    }

    public void stationSetUp() {

        this.suspended = true; // Initialize the station as suspended
    }

    // Method to suspend the station
    public boolean suspend() {
        if (!suspended && !station.isInUse()) {
            // Check if the station is not already suspended and not in use
            System.out.println("The Station has been suspended.");
            suspended = true;
            return suspended;
        } else if (suspended) {
            System.out.println("The Station is already suspended.");
            return suspended;
        } else {
            //The system won't be suspended when user is in use.
            System.out.println("Cannot suspend the station when customer is using it.");
            return suspended;
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
        SuspendController suspendController1 = new SuspendController(null);
        Station Station1 = suspendController1.new Station("Station1" , true);
        SuspendController suspendController2 = new SuspendController(null);
        Station Station2 = suspendController2.new Station("Station2" , false);
        SuspendController suspendController3 = new SuspendController(null);
        Station Station3 = suspendController3.new Station("Station3" , true);
        stations.add(new SuspendController(Station1));
        stations.add(new SuspendController(Station2));
        stations.add(new SuspendController(Station3));




        //create a scanner class for
        int selectedStation = 0;
        // Attendant I/O: Get the selected station
        bigLoop:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // Attendant I/O: Display the list of stations for selection
            System.out.println("Select a station to suspend/unsuspend:");
            for (int i = 0; i < stations.size(); i++) {
                System.out.println((i + 1) + ". Station " + (i + 1));
            }
            if (scanner.hasNextInt()) {
                selectedStation = scanner.nextInt();
                System.out.println("Attendant selected Station " + selectedStation);
            } else {
                scanner.nextLine(); // consume invalid input
                System.out.println("Invalid input. Please enter an integer.");
                continue bigLoop;
            }





            //while loop for testing user input, if user enter suspend, can be changed when we apply GUI in the system.
            while (true) {
                System.out.println("Enter 'suspend' to suspend the process or 'unsuspend' to unsuspend the process:");
                System.out.println("Enter 'exit' to exit the process or 'change' to pick another station:");
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
                else if(input.equalsIgnoreCase("exit")) {
                    System.out.println("Exit successfully.");
                    break bigLoop;
                }
                else if(input.equalsIgnoreCase("change")) {
                    System.out.println("Please pick another station.");
                    continue bigLoop;
                }
                else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }
}

