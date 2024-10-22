package main;

import java.util.Scanner;
import publicTransport.*;

public class Main {
    public static void main(String[] args) {
        // Call the example() method to demonstrate the functionality of the Public Transport system.
        // This method creates predefined instances of vehicles, lines, and passengers,
        // allowing users to see how the system operates without needing to input any data.
        example();


        // Uncomment the following line to enable user input functionality.
        // The readInput() method allows users to interactively create their own instances
        // of vehicles, lines, and passengers through standard input.
        // readInput();
    }

    private static void example() {
        int numberOfDays = 3;
        int stationCapacity = 10;
        int numberOfStations = 3;
        Station.setStationCapacity(stationCapacity);
        Station[] stations = new Station[numberOfStations];
        stations[0] = new Station("Banacha");
        stations[1] = new Station("Krakowskie");
        stations[2] = new Station("Centrum");
        int numberOfPassengers = 15;
        int tramCapacity = 5;
        int numberOfTramLines = 1;
        Tram.setTramCapacity(tramCapacity);

        TramLine[] tramLines = new TramLine[numberOfTramLines];
        int numberOfTrams = 2;
        int routeLength = 3;
        Station[] routeStations = new Station[routeLength];
        int[] travelTimes = new int[routeLength];
        routeStations[0] = getStationByName("Banacha", stations);
        travelTimes[0] = 3;
        routeStations[1] = getStationByName("Centrum", stations);
        travelTimes[1] = 2;
        routeStations[2] = getStationByName("Krakowskie", stations);
        travelTimes[2] = 10;
        Route tramRoute = new Route(routeStations, travelTimes);
        tramLines[0] = new TramLine(tramRoute, 0, numberOfTrams);

        // Simulation
        Simulation simulation = new Simulation(numberOfDays, stations, tramLines, numberOfPassengers);
        simulation.simulatePublicTransportation();
    }

    private static void readInput() {
        Scanner scanner = new Scanner(System.in);

        // Prompt for the number of days for the simulation
        System.out.print("Enter the number of days for the simulation: ");
        int numberOfDays = scanner.nextInt();

        // Stations
        System.out.print("Enter the capacity for each station: ");
        int stationCapacity = scanner.nextInt();
        
        System.out.print("Enter the number of stations: ");
        int numberOfStations = scanner.nextInt();
        Station.setStationCapacity(stationCapacity);
        
        Station[] stations = new Station[numberOfStations];
        for (int i = 0; i < numberOfStations; i++) {
            System.out.print("Enter the name of station " + (i + 1) + ": ");
            String stationName = scanner.next();
            stations[i] = new Station(stationName);
        }

        System.out.print("Enter the number of passengers: ");
        int numberOfPassengers = scanner.nextInt();

        // Trams and TramLines
        System.out.print("Enter the capacity for each tram: ");
        int tramCapacity = scanner.nextInt();
        
        System.out.print("Enter the number of tram lines: ");
        int numberOfTramLines = scanner.nextInt();
        Tram.setTramCapacity(tramCapacity);
        
        TramLine[] tramLines = new TramLine[numberOfTramLines];
        for (int i = 0; i < numberOfTramLines; i++) {
            System.out.print("Enter the number of trams for tram line " + (i + 1) + ": ");
            int numberOfTrams = scanner.nextInt();
            
            System.out.print("Enter the route length for tram line " + (i + 1) + ": ");
            int routeLength = scanner.nextInt();
            
            Station[] routeStations = new Station[routeLength];
            int[] travelTimes = new int[routeLength];
            
            for (int j = 0; j < routeLength; j++) {
                System.out.print("Enter the name of station " + (j + 1) + " for tram line " + (i + 1) + ": ");
                String stationName = scanner.next();
                
                System.out.print("Enter the travel time to the next station (in minutes): ");
                travelTimes[j] = scanner.nextInt();
                
                routeStations[j] = getStationByName(stationName, stations);
            }
            
            Route tramRoute = new Route(routeStations, travelTimes);
            tramLines[i] = new TramLine(tramRoute, i, numberOfTrams);
        }

        // Simulation
        Simulation simulation = new Simulation(numberOfDays, stations, tramLines, numberOfPassengers);
        simulation.simulatePublicTransportation();
    }

    private static Station getStationByName(String stationName, Station[] stations) {
        for (Station station : stations) {
            if (station.toString().equals(stationName)) {
                return station;
            }
        }
        return null; // Station not found
    }
}