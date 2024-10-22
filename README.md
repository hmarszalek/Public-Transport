# Public Transport Project

This project aims to model a public transportation system using object-oriented programming principles in Java. The system includes various types of vehicles, lines, and passengers, allowing for a simulation of public transport operations.

## Features

-   Abstract representation of vehicles (e.g., buses, trams)
-   Management of passengers on each vehicle
-   Determination of vehicle capacity (full or empty)
-   Line management for different routes

## Getting Started

To get started with the Public Transport project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/hmarszalek/Public-Transport.git
    ```

2. Navigate to the project directory and compile the Java files:

    ```bash
    javac ./event/*.java
    javac ./main/*.java
    javac ./publicTransport/*.java
    ```

3. Run the main application

    ```bash
    java main.Main
    ```

## Architecture

The project is structured around an abstract class `Vehicle` which defines the basic properties and methods that any vehicle should have. This includes:

-   `sideNumber`: An identifier for the vehicle
-   `line`: The line that the vehicle operates on
-   `passengersCount`: The current number of passengers in the vehicle

The abstract methods defined in the Vehicle class allow for specific implementations in subclasses.

## Classes

### Vehicle

The `Vehicle` class is an abstract class that represents a generic vehicle in the public transport system. It provides a basic structure for different types of vehicles, such as buses, trams, and trains.

**Properties:**

-   `sideNumber`: A unique identifier for the vehicle.
-   `line`: The line that the vehicle operates on.
-   `passengersCount`: The current number of passengers in the vehicle.
-   `capacity`: The maximum number of passengers the vehicle can hold.

**Methods:**

-   `isVehicleFull()`: Checks if the vehicle is full by comparing the passengersCount with the capacity.
-   `isVehicleEmpty()`: Checks if the vehicle is empty by checking if the passengersCount is zero.
-   `addPassenger(Passenger passenger)`: Adds a passenger to the vehicle if there is available capacity.
-   `removePassenger(int index)`: Removes a passenger from the vehicle at the specified index.
-   `getSideNumber()`: Returns the sideNumber of the vehicle.
-   `getLine()`: Returns the line that the vehicle operates on.
-   `getPassengersCount()`: Returns the current number of passengers in the vehicle.
-   `getCapacity()`: Returns the maximum capacity of the vehicle.

**Subclasses:**
The Vehicle class can be extended by subclasses to represent specific types of vehicles, such as:

-   `Bus`: A subclass of Vehicle that represents a bus.
-   `Tram`: A subclass of Vehicle that represents a tram.
-   `Train`: A subclass of Vehicle that represents a train.

Each subclass can add its own properties and methods specific to that type of vehicle.

### Line

The `Line` class represents a public transport line, which can be associated with various vehicles.

**Properties:**

-   `lineNumber`: A unique identifier for the line.
-   `vehicles`: A list of vehicles that operate on the line.
-   `route`: The route that the line follows.

**Methods:**

-   `addVehicle(Vehicle vehicle)`: Adds a vehicle to the line.
-   `removeVehicle(Vehicle vehicle)`: Removes a vehicle from the line.
-   `getLineNumber()`: Returns the lineNumber of the line.
-   `getVehicles()`: Returns the list of vehicles that operate on the line.
-   `getRoute()`: Returns the route that the line follows.

### Passenger

The `Passenger` class represents a passenger using the public transport system.

**Properties:**

-   `passengerId`: A unique identifier for the passenger.
-   `name`: The name of the passenger.
-   `destination`: The destination of the passenger.

**Methods:**

-   `getPassengerId()`: Returns the passengerId of the passenger.
-   `getName()`: Returns the name of the passenger.
-   `getDestination()`: Returns the destination of the passenger.

## Usage

The `Main.java' file serves as the entry point for the Public Transport project. It contains examples of input data to demonstrate the functionality of the system, as well as the capability for users to input their own data through standard input.

## Contributions

Contributions are welcome! Please feel free to open issues or submit pull requests for improvements or bug fixes.

## Author

Hanna Marszałek
