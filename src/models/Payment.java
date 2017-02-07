package models;

import java.math.BigDecimal;

public class Payment {

    private double _totalRevenue;
    private double _dayRevenue;
    private double _lastDayRevenue;
    private double _expectedRevenue;
    private double _cost;        //cost in euros per minute

    public Payment() {
        _totalRevenue = 0.0;
        _cost = 0;
        _expectedRevenue = 0;
    }

    public BigDecimal getTotalRevenue() {
        BigDecimal bd = new BigDecimal(_totalRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal getDayRevenue() {
        BigDecimal bd = new BigDecimal(_dayRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public BigDecimal getLastDayRevenue() {
        BigDecimal bd = new BigDecimal(_lastDayRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    public double getDoubleDayRevenue() {
        return _dayRevenue;
    }

    public void resetDayRevenue() {
        _dayRevenue = 0;
    }

    public void setLastDayRevenue(double a) {
        _lastDayRevenue = a;
    }

    public void pay(int stayTime) {
        _totalRevenue += _cost * (double) stayTime;
        _dayRevenue   += _cost * (double) stayTime;
    }

    public void payExtra(double extra) {
        _totalRevenue += extra;
        _dayRevenue   += extra;
    }
  
    public void reset() {
        _totalRevenue = 0;
        _expectedRevenue = 0;
    }

    public void setCost(double cost) {
        _cost = cost;
    }

    public BigDecimal getExpectedRevenue(int normalCarsStillInGarage, int carsWithReservationStillInGarage) {
        _expectedRevenue = normalCarsStillInGarage * _cost;
        _expectedRevenue += (carsWithReservationStillInGarage * _cost) + (carsWithReservationStillInGarage * 5);

        BigDecimal bd = new BigDecimal(_expectedRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
