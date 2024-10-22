package publicTransport;

import main.Losowanie;
import main.Simulation;
import event.Event;

public class Tram extends Vehicle {
    private static int tramCapacity;
    private final int startTime;
    private final Route tramRoute;
    private int currentStationIndex;
    private Station currentStation;
    private Passenger[] passengers;

    public Tram(int sideNumber, TramLine line, Route route, int startTime) {
        super(sideNumber, line);
        tramRoute = route;
        this.startTime = startTime;
    }

    public static void setTramCapacity(int capacity) {
        tramCapacity = capacity;
    }

    public static int getTramCapacity() {
        return tramCapacity;
    }

    public int getStartTime() {
        return startTime;
    }

    public void newDay() {
        currentStationIndex = 0;
        passengers = new Passenger[tramCapacity];
        passengersCount = 0;
    }

    @Override
    public boolean isVehicleFull() {
        return passengersCount == tramCapacity;
    }

    @Override
    public boolean isVehicleEmpty() {
        return passengersCount == 0;
    }

    @Override
    protected void addPassenger(Passenger passenger) {
        givePassengerDestination(passenger);
        System.out.println(Simulation.getDay() + ", " + Simulation.timeToString(Simulation.getMinuteOfTheDay()) + ": Passenger " + passenger.getSocialSecurityNumber() + " got in the tram of line " +
                line.getLineNumber() + " (side number " + sideNumber + ") with intentions to get to station " + passenger.getCurrentDestination());
        passenger.gotInTheTram();
        passengers[passengersCount++] = passenger;
    }

    @Override
    protected Passenger removePassenger(int index) {
        Passenger passenger = passengers[index];
      System.out.println(Simulation.getDay() + ", " + Simulation.timeToString(Simulation.getMinuteOfTheDay()) + ": Passenger " + passenger.getSocialSecurityNumber() + " got out of the tram of line "
                + line.getLineNumber() + " (side number " + sideNumber + ") to the station " + currentStation);
        passenger.gotToTheStation();
        passengersCount--;
        for (int i = index; i < passengersCount; i++) {
            passengers[i] = passengers[i + 1];
        }
        return passenger;
    }

    private void givePassengerDestination(Passenger passenger) {
        int numberOfStations = tramRoute.getLength() / 2;
        int newStationIndex;
        if (currentStationIndex < numberOfStations) {
            newStationIndex = Losowanie.losuj(currentStationIndex + 1, numberOfStations - 1);
        } else {
            newStationIndex = Losowanie.losuj(currentStationIndex + 1, numberOfStations * 2 - 1);
        }
        passenger.setCurrentDestination(tramRoute.getStationFromIndex(newStationIndex));
    }

    private void letOutPassengers() {
        int index = 0;
        while (!isVehicleEmpty() && !currentStation.isStationFull()) {
            if(index >= passengersCount) return;
            if (passengers[index].getCurrentDestination() == currentStation) {
                currentStation.addPassenger(removePassenger(index));
                continue;
            }
            index++;
        }
    }

    private void letInPassengers() {
        while (!isVehicleFull() && !currentStation.isStationEmpty()) {
            addPassenger(currentStation.getNextPassenger());
        }
    }

    public void arrivedToTheStation() {
        currentStation = tramRoute.getStationFromIndex(currentStationIndex);
        System.out.println(Simulation.getDay() + "; " + Simulation.timeToString(Simulation.getMinuteOfTheDay()) + ": Tram of line " + line.getLineNumber() +
                    " (side number " + sideNumber + ") arrived to the station " + currentStation.toString());
        letOutPassengers();
        if (!tramRoute.isLastStation(currentStationIndex)) {
            letInPassengers();
        }
        goToNextStation();
    }

    private void goToNextStation() {
        if (currentStationIndex == 0 && Simulation.getMinuteOfTheDay() > 1380) return;
        int time = Simulation.getMinuteOfTheDay() + tramRoute.getNextStationTravelTime(currentStationIndex);
        currentStationIndex = (currentStationIndex + 1) % tramRoute.getLength();
        Simulation.addNewEvent(new Event(time, this));
    }
}
