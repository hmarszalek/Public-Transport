package event;
import main.Simulation;
import publicTransport.*;

public class Event {
    private final int timeOfOccurrence;
    private final Object eventClass;

    public Event(int timeOfOccurrence, Object eventClass) {
        this.timeOfOccurrence = timeOfOccurrence;
        this.eventClass = eventClass;
    }

    public void executeEvent() {
        Simulation.setMinuteOfTheDay(timeOfOccurrence);
        String className = eventClass.getClass().getSimpleName();
        if(className.equals("Tram"))  {
            Tram eventTram = (Tram) eventClass;
            eventTram.arrivedToTheStation();
        }
        if(className.equals("Passenger")) {
            Passenger eventPassenger = (Passenger) eventClass;
            eventPassenger.goToHomeStation();
        }
    }

    public int getTimeOfOccurrence() {
        return timeOfOccurrence;
    }
}