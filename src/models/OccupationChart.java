package models;

public class OccupationChart {

    private OccupationChartData _unoccupied;
    private OccupationChartData _passHolders;
    private OccupationChartData _adHoc;
    private OccupationChartData _carsWithReservations;

    public OccupationChart() {
        reset();
    }

    public void reset() {
        _unoccupied = new OccupationChartData("Unoccupied", 100);
        _passHolders = new OccupationChartData("Pass holders");
        _adHoc = new OccupationChartData("AdHoc");
        _carsWithReservations = new OccupationChartData("With reservation");
    }

    public void update(int total, int unoccupied, int passHolders, int adHoc, int reservations) {
        double percentageUnoccupied = (double) unoccupied / total * 100;
        double percentagePassHolders = (double) passHolders / total * 100;
        double percentageAdHoc = (double) adHoc / total * 100;
        double percentageWithReservation = (double) reservations / total * 100;

        _unoccupied.setPercentage(percentageUnoccupied);
        _passHolders.setPercentage(percentagePassHolders);
        _adHoc.setPercentage(percentageAdHoc);
        _carsWithReservations.setPercentage(percentageWithReservation);
    }

    public OccupationChartData getUnoccupied() {
        return _unoccupied;
    }

    public OccupationChartData getPassHolders() {
        return _passHolders;
    }

    public OccupationChartData getAdHoc() {
        return _adHoc;
    }

    public OccupationChartData getCarsWithReservation() {
        return _carsWithReservations;
    }
}