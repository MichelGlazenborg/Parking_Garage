package models;

import java.math.BigDecimal;

/**
 * Created by Jelmer on 30-Jan-17.
 */
public class Payment {
    private double totalRevenue;
    private double dayRevenue;
    private double lastDayRevenue;
    private double expectedRevenue;
    private double cost;        //cost in euro's per minute

    public Payment() {
        totalRevenue = 0.0;
        cost = 0;
        expectedRevenue = 0;
    }

    public BigDecimal getTotalRevenue() {
        BigDecimal bd = new BigDecimal(totalRevenue);
        BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return rounded;
    }

    public BigDecimal getDayRevenue() {
        BigDecimal bd = new BigDecimal(dayRevenue);
        BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return rounded;
    }

    public BigDecimal getLastDayRevenue() {BigDecimal bd = new BigDecimal(lastDayRevenue);
        BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return rounded;
    }

    public double getDoubleDayRevenue() { return dayRevenue;}

    public void resetDayRevenue() {dayRevenue = 0;}

    public void setLasDayRevenue(double a) {lastDayRevenue = a;}

    public void pay(int stayTime) {
        totalRevenue += cost * (double) stayTime;
        dayRevenue   += cost * (double) stayTime;
    }

    public void payExtra(double extra) {
        totalRevenue += extra;
        dayRevenue   += extra;
    }
  
    public void reset() {
        totalRevenue = 0;
        expectedRevenue = 0;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public BigDecimal getExpectedRevenue(int normalCarsStillInGarage,int carsWithReservationStillInGarage) {
        expectedRevenue = normalCarsStillInGarage * cost;
        expectedRevenue += (carsWithReservationStillInGarage * cost) + (carsWithReservationStillInGarage * 5);
        BigDecimal bd = new BigDecimal(expectedRevenue);
        BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        return rounded;

    }

}
