package publicTransport;

public class Station {
    private final String stationName;
    private static int stationCapacity;
    private int passengersCount;
    private PassengersQueue passengers;


    public Station(String stationName) {
        this.stationName = stationName;
    }

    public static void setStationCapacity(int stationCapacity) {
        Station.stationCapacity = stationCapacity;
    }

    public static int getStationCapacity() {
        return stationCapacity;
    }

    public boolean isStationFull() {
        return passengersCount == stationCapacity;
    }

    public boolean isStationEmpty() {
        return passengersCount == 0;
    }

    public String toString() {
        return stationName;
    }

    // we assume station isn't full
    public void addPassenger(Passenger newPassenger) {
        passengers.insertPassenger(newPassenger);
    }

    //we assume station isn't empty
    public Passenger getNextPassenger() {
        return passengers.getnextPassenger();
    }

    public void newDay() {
        passengersCount = 0;
        passengers = new PassengersQueue(stationCapacity);
    }

    private class PassengersQueue {
        private final Passenger[] passengersArray;

        public PassengersQueue(int capacity) {
            passengersArray = new Passenger[capacity];
        }

        public void insertPassenger(Passenger passenger) {
            passengersArray[passengersCount] = passenger;
            passengersCount++;
        }

        private void shiftArrayBack() {
            for (int i = 0; i < passengersCount - 1; i++) {
                passengersArray[i] = passengersArray[i + 1];
            }
        }

        public Passenger getnextPassenger() {
            Passenger nextPassenger = passengersArray[0];
            shiftArrayBack();
            passengersCount--;
            return nextPassenger;
        }
    }
}
