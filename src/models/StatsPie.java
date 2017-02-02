package models;

import java.util.ArrayList;

public class StatsPie {

    private StatsData _unoccupied;
    private StatsData _passholders;
    private StatsData _adhoc;
    private StatsData _carsWithReservations;

    public StatsPie() {
        reset();
    }

    public void reset() {
        _unoccupied = new StatsData("Unoccupied", 100);
        _passholders = new StatsData("Pass holders");
        _adhoc = new StatsData("AdHoc");
        _carsWithReservations = new StatsData("With reservation");
    }

    public void update(int total, int unoccupied, int passHolders, int adHoc, int reservations) {
        double percentageUnoccupied = (double) unoccupied / total * 100;
        double percentagePassHolders = (double) passHolders / total * 100;
        double percentageAdHoc = (double) adHoc / total * 100;
        double percentageWithReservation = (double) reservations / total * 100;
        double percentageTotal = percentageUnoccupied + percentageAdHoc + percentagePassHolders + percentageWithReservation;
        
        //if (percentageTotal < 100)
            //percentageUnoccupied += (100 - percentageTotal);

        _unoccupied.setPercentage(percentageUnoccupied);
        _passholders.setPercentage(percentagePassHolders);
        _adhoc.setPercentage(percentageAdHoc);
        _carsWithReservations.setPercentage(percentageWithReservation);
    }

    public StatsData getUnoccupied() {
        return _unoccupied;
    }

    public StatsData getPassHolders() {
        return _passholders;
    }

    public StatsData getAdHoc() {
        return _adhoc;
    }

    public StatsData getCarsWithReservation() {
        return _carsWithReservations;
    }
}