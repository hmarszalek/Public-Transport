package event;

public class EventQueueArray implements EventQueue {
    private Event[] eventsArray;
    private int arraySize;
    private int numberOfEvents;

    public EventQueueArray(int size) {
        eventsArray = new Event[size];
        arraySize = size;
        numberOfEvents = 0;
    }

    private boolean isALaterThanB(Event a, Event b) {
        return a.getTimeOfOccurrence() > b.getTimeOfOccurrence();
    }

    private int findNewEventIndex(Event event) {
        int index = 0;
        while (index < numberOfEvents && isALaterThanB(event, eventsArray[index])) {
            index++;
        }
        return index;
    }

    private void shiftArrayFromIndex(int index) {
        for (int i = numberOfEvents; i > index; i--) {
            eventsArray[i] = eventsArray[i - 1];
        }
    }

    private void shiftArrayBack() {
        for (int i = 0; i < numberOfEvents - 1; i++) {
            eventsArray[i] = eventsArray[i + 1];
        }
    }

    private void resizeArray() {
        Event[] newArray = new Event[arraySize * 2];
        if (arraySize >= 0) System.arraycopy(eventsArray, 0, newArray, 0, arraySize);
        eventsArray = newArray;
        arraySize *= 2;
    }

    @Override
    public void insertEvent(Event event) {
        if (numberOfEvents == arraySize) resizeArray();
        int newIndex = findNewEventIndex(event);
        shiftArrayFromIndex(newIndex);
        eventsArray[newIndex] = event;
        numberOfEvents++;
    }

    @Override
    public Event getNextEvent() {
        Event nextEvent = eventsArray[0];
        shiftArrayBack();
        numberOfEvents--;
        return nextEvent;
    }

    @Override
    public boolean isQueueEmpty() {
        return numberOfEvents == 0;
    }
}