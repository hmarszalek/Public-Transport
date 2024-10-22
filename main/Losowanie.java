package main;

public class Losowanie {
    public static int losuj(int lowerBound, int upperBound) {
        return (int) ((Math.random() * (upperBound - lowerBound+1)) + lowerBound);
    }
}
