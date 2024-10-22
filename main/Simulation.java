package main;

import event.*;
import publicTransport.*;

public class Simulation {
    private final int numberOfDays;
    private final int numberOfPassengers;
    private final Station[] stations;
    private final TramLine[] tramLines;
    private Passenger[] passengers;
    private static int day;
    private static int minuteOfTheDay;
    private static int singleDayRideCnt;
    private static int singleDayWaitingTime;
    private static int singleDayGivenUp;
    private static int waitCount;
    private static EventQueueArray eventQueue;

    public Simulation(int numberOfDays, Station[] stations, TramLine[] tramLines, int numberOfPassengers) {
        this.numberOfDays = numberOfDays;
        this.stations = stations;
        this.tramLines = tramLines;
        this.numberOfPassengers = numberOfPassengers;
    }

    private void printInputData() {
        System.out.println("Number of days of simulation: " + numberOfDays);
        System.out.println("Station capacity: " + Station.getStationCapacity());
        System.out.println("Number of stations: " + stations.length);
        for(Station station : stations)
            System.out.print(station.toString() + " ");
        System.out.println("\n" + "Number of passengers: " + numberOfPassengers);
        System.out.println("Tram capacity: " + Tram.getTramCapacity());
        System.out.println("Number of tram lines: " + tramLines.length);
        for(TramLine line : tramLines) {
            System.out.println("Number of trams on the line: " + line.getNumberOfVehicles());
            System.out.println("Line's length: " + line.getLineLength());
            line.printRoute();
        }
    }

    // Method to simulate a single day of public transportation
    private void simulateSingleDay() {
        singleDayRideCnt = 0;
        singleDayWaitingTime = 0;
        singleDayGivenUp = 0;
        eventQueue = new EventQueueArray(2048);

        // Clear all stations for the new day
        for(Station station : stations) {
            station.newDay();
        }
        // Wake up passengers
        for(Passenger passenger : passengers) {
            eventQueue.insertEvent(passenger.WakeUp());
        }
        // Set up morning schedule for tram lines
        for(TramLine line : tramLines) {
            line.setUpMorningSchedule();
        }

        // Process events in the event queue until it's empty
        while(!eventQueue.isQueueEmpty()) {
            eventQueue.getNextEvent().executeEvent();
        }
        // Reset time to midnight and add time each passenger spent waiting on trams till midnight
        minuteOfTheDay = 1440;
        for(Passenger passenger : passengers) {
            singleDayWaitingTime += passenger.getWaitTime();
        }
    }

    // Method to simulate the entire public transportation system
    public void simulatePublicTransportation() {
        printInputData();
        createPassengers();
        createTrams();
        int[] rides = new int[numberOfDays];
        int[] wait = new int[numberOfDays];
        int[] givenUp = new int[numberOfDays];

        day = 1;
        int entireRideCnt = 0;
        int entireWaitingTime = 0;
        int entireGivenUp = 0;
        waitCount = 0;
        while (day <= numberOfDays) {
            simulateSingleDay();
            rides[day-1] = singleDayRideCnt;
            wait[day-1] = singleDayWaitingTime;
            givenUp[day-1] = singleDayGivenUp;
            entireRideCnt += singleDayRideCnt;
            entireWaitingTime += singleDayWaitingTime;
            entireGivenUp += singleDayGivenUp;
            day++;
        }

        System.out.println("\n################### STATISTICS ###########################");

        for(int i = 0; i < numberOfDays; i++) {
            System.out.println("Day " + (i+1) + ":");
            System.out.println("All rides taken: " + rides[i]);
            System.out.println("Number of passengers who gave up and went home: " + givenUp[i]);
            System.out.println("Sum of waiting time on the stations: " + wait[i] + " minutes\n");
        }

        if(waitCount != 0) {
            entireWaitingTime /= waitCount;
        }

        System.out.println("For the entire lenght of " + numberOfDays + " days of the simulation:");
        System.out.println("All rides during the simulation: " + entireRideCnt);
        System.out.println("Number of passengers who gave up and went home: " + entireGivenUp);
        System.out.println("Mean of waiting time on the station: " + entireWaitingTime + " minutes");
        System.out.println("##########################################################");
    }

    private void createTrams() {
        int firstSideNumber = 0;
        for(TramLine line : tramLines) {
            line.createTramsOnTheLine(firstSideNumber);
            firstSideNumber += line.getNumberOfVehicles();
        }
    }

    private void createPassengers() {
        passengers = new Passenger[numberOfPassengers];
        for(int i = 0; i < numberOfPassengers; i++) {
            passengers[i] = new Passenger(i, drawHomeStation());
        }
    }

    private Station drawHomeStation() {
        return stations[Losowanie.losuj(0, stations.length - 1)];
    }

    public static void setMinuteOfTheDay(int time) {
        minuteOfTheDay = time;
    }

    public static int getMinuteOfTheDay() {
        return minuteOfTheDay;
    }

    public static void addNewEvent(Event event) {
        eventQueue.insertEvent(event);
    }

    public static int getDay() {
        return day;
    }

    public static void increaseSingleDayRideCnt() {
        singleDayRideCnt++;
    }

    public static void increaseSingleDayWaitTime(int time) {
        singleDayWaitingTime += time;
    }

    public static void increaseSingleDayGivenUp() { singleDayGivenUp++; }

    public static void increaseWaitCount() {
        waitCount++;
    }

    public static String timeToString(int time) {
        String hour = String.valueOf(time/60);
        String minute = String.valueOf(time % 60);
        if(Integer.parseInt(hour) < 10) hour = "0" + hour;
        if(Integer.parseInt(minute) < 10) minute = "0" + minute;
        return hour + ":" + minute;
    }
}

