package models;

public class OccupationChart {

    private OccupationChartData _unoccupied;
    private OccupationChartData _passHolders;
    private OccupationChartData _adHoc;
    private OccupationChartData _carsWithReservations;

    /**
     * Calls the reset method
     */
    public OccupationChart() {
        reset();
    }

    /**
     * reset the OccupationChart
     */
    public void reset() {
        _unoccupied = new OccupationChartData("Unoccupied", 100);
        _passHolders = new OccupationChartData("Pass holders");
        _adHoc = new OccupationChartData("AdHoc");
        _carsWithReservations = new OccupationChartData("With reservation");
    }

    /**
     * Updates the OccupationChart
     * @param total = Total of parking spots
     * @param unoccupied = Total of unoccupied parking spots
     * @param passHolders = Total of passHolders parked
     * @param adHoc = Total of normal cars parked
     * @param reservations = Total of reservations parked
     */
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

    /**
     * Gets the unoccupied parking spots and returns it
     * @return _unoccupied
     */
    public OccupationChartData getUnoccupied() {
        return _unoccupied;
    }

    /**
     * Gets the total of parked passholders and returns is
     * @return _passHolders
     */
    public OccupationChartData getPassHolders() {
        return _passHolders;
    }

    /**
     * Gets the total of normal cars parked and returns it
     * @return _adHoc
     */
    public OccupationChartData getAdHoc() {
        return _adHoc;
    }

    /**
     * Gets the total of parked cars with a reservation and returns it
     * @return _carsWithReservations
     */
    public OccupationChartData getCarsWithReservation() {
        return _carsWithReservations;
    }
}