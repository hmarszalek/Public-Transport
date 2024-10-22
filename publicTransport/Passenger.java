package publicTransport;

import event.Event;
import main.Losowanie;
import main.Simulation;

public class Passenger {
    private final int socialSecurityNumber;
    private final Station homeStation;
    private Station currentDestination;
    private int startedWaiting;

    public Passenger(int ssn, Station station) {
        socialSecurityNumber = ssn;
        homeStation = station;
    }

    private int drawStartTime() {
        return Losowanie.losuj(360,720);
    }

    public Event WakeUp() {
        return new Event(drawStartTime(), this);
    }

    public void goToHomeStation() {
        if(homeStation.isStationFull()) {
            System.out.println(Simulation.getDay() + ", " + Simulation.timeToString(Simulation.getMinuteOfTheDay()) + ": Passenger " + socialSecurityNumber + " tried to get to station "
                    + homeStation + " but it was full so he gave up and went home");
            System.out.println();
            Simulation.increaseSingleDayGivenUp();
            startedWaiting = 1440;
            return;
        }
        System.out.println(Simulation.getDay() + ", " + Simulation.timeToString(Simulation.getMinuteOfTheDay()) + ": Passenger " + socialSecurityNumber + " went to station " + homeStation.toString());
        homeStation.addPassenger(this);
        gotToTheStation();
    }

    public void setCurrentDestination(Station newStation) {
        currentDestination = newStation;
    }

    public Station getCurrentDestination() {
        return currentDestination;
    }

    public int getSocialSecurityNumber() {
        return  socialSecurityNumber;
    }

    public void gotToTheStation() {
        startedWaiting = Simulation.getMinuteOfTheDay();
        Simulation.increaseWaitCount();
    }

    public void gotInTheTram() {
        int time = getWaitTime();
        Simulation.increaseSingleDayRideCnt();
        Simulation.increaseSingleDayWaitTime(time);
        startedWaiting = 1440;
    }

    public int getWaitTime() {
        return Simulation.getMinuteOfTheDay() - startedWaiting;
    }
}
