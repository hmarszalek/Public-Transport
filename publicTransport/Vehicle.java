package publicTransport;

abstract class Vehicle {
    protected int sideNumber;
    protected Line line;
    protected int passengersCount;

    public Vehicle(int sideNumber, Line line) {
        this.sideNumber = sideNumber;
        this.line = line;
    }

    abstract boolean isVehicleFull();
    abstract boolean isVehicleEmpty();
    protected abstract void addPassenger(Passenger passenger);
    protected abstract Passenger removePassenger(int index);
}
