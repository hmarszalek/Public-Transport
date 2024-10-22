package publicTransport;

import event.Event;
import main.Simulation;

public class TramLine extends Line {
    private Tram[] linesTrams;

    public TramLine(Route route, int lineNumber, int numberOfVehicles) {
        super(route, lineNumber, numberOfVehicles);
    }

    public void createTramsOnTheLine(int sideNumber) {
        linesTrams = new Tram[numberOfVehicles];
        Route routeA = lineRoute.createTramRouteA();
        Route routeB = lineRoute.createTramRouteB();

        int gapTime = numberOfVehicles / getTravelTime();

        int startTime = 360;
        for(int i = 0; i <= (numberOfVehicles-1)/2; i++) {
            linesTrams[i] = new Tram(sideNumber++,this, routeA, startTime);
            startTime += gapTime*2;
        }

        startTime = 360;
        for(int i = (numberOfVehicles-1)/2 + 1; i < numberOfVehicles; i++) {
            linesTrams[i] = new Tram(sideNumber++,this, routeB, startTime);
            startTime += gapTime*2;
        }
    }

    public void setUpMorningSchedule() {
        for(Tram tram : linesTrams) {
            tram.newDay();
            Simulation.addNewEvent(new Event(tram.getStartTime(), tram));
        }
    }

    public void printRoute() {
        lineRoute.printRoute();
    }
}
