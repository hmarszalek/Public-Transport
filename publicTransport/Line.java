package publicTransport;

abstract class Line {
    protected Route lineRoute;
    protected int lineNumber;
    protected int numberOfVehicles;

    public Line(Route route, int lineNumber, int numberOfVehicles) {
        this.lineRoute = route;
        this.lineNumber = lineNumber;
        this.numberOfVehicles = numberOfVehicles;
    }

    public int getTravelTime() {
        return lineRoute.getTotalTime();
    }

    public int getLineLength() {
        return lineRoute.getLength();
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
