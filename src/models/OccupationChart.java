package models;

public class OccupationChart {

    private OccupationChartData _unoccupied;
    private OccupationChartData _passholders;
    private OccupationChartData _adhoc;
    private OccupationChartData _carsWithReservations;

    public OccupationChart() {
        reset();
    }

    public void reset() {
        _unoccupied = new OccupationChartData("Unoccupied", 100);
        _passholders = new OccupationChartData("Pass holders");
        _adhoc = new OccupationChartData("AdHoc");
        _carsWithReservations = new OccupationChartData("With reservation");
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

    public OccupationChartData getUnoccupied() {
        return _unoccupied;
    }

    public OccupationChartData getPassHolders() {
        return _passholders;
    }

    public OccupationChartData getAdHoc() {
        return _adhoc;
    }

    public OccupationChartData getCarsWithReservation() {
        return _carsWithReservations;
    }
}