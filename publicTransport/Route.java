package publicTransport;

public class Route {
    public Station[] routeStations;
    public int[] routeTravelTime;

    public Route(Station[] stations, int[] time) {
        routeStations = stations;
        routeTravelTime = time;
    }

    public int getTotalTime() {
        int totalTime = 0;
        for (int j : routeTravelTime) totalTime += 2 * j;
        return totalTime;
    }

    public Route createTramRouteA() {
        int n = routeStations.length;
        Station[] stationsA = new Station[2*n];
        int[] travelTimeA = new int[2*n];
        for(int i = 0; i < n; i++) {
            stationsA[i] = routeStations[i];
            stationsA[2*n-1-i] = routeStations[i];
            travelTimeA[i] = routeTravelTime[i];
            if(i==0) travelTimeA[2*n-1] = routeTravelTime[n-1];
            else travelTimeA[2*n-1-i] = routeTravelTime[i-1];
        }
        return new Route(stationsA, travelTimeA);
    }

    public Route createTramRouteB() {
        int n = routeStations.length;
        Station[] stationsB = new Station[2*n];
        int[] travelTimeB = new int[2*n];
        for(int i = 0; i < n; i++) {
            stationsB[n-1-i] = routeStations[i];
            stationsB[n+i] = routeStations[i];
            if(i==0) travelTimeB[n-1-i] = routeTravelTime[n-1];
            else travelTimeB[n-1-i] = routeTravelTime[i-1];
            travelTimeB[n+i] = routeTravelTime[i];
        }
        return new Route(stationsB, travelTimeB);
    }

    public int getLength() {
        return routeStations.length;
    }

    public int getNextStationTravelTime(int index) {
        return routeTravelTime[index];
    }

    public Station getStationFromIndex(int index) {
        return routeStations[index];
    }

    public boolean isLastStation(int index) {
        return index == (getLength()-1)/2 || index == getLength()-1;
    }

    public void printRoute() {
        for(int i = 0; i < getLength(); i++) {
            System.out.println(routeStations[i].toString() + " " + routeTravelTime[i]);
        }
    }
}
