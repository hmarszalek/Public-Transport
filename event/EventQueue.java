package event;

public interface EventQueue {
    void insertEvent(Event newEvent);
    Event getNextEvent();
    boolean isQueueEmpty();
}
