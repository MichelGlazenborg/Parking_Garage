package models;

/**
 * Created by Jelmer on 30-Jan-17.
 */
public class Payment {
    private double totalRevenue;
    private double dayRevenue;
    private double lastDayRevenue;
    private double expectedRevenue;
    private double cost;        //cost in euro's per minute
    private boolean ready;

    public Payment() {
        totalRevenue = 0.0;
        cost = 0;
        expectedRevenue = 0;
        ready = true;
    }

    public double getTotalRevenue() {
        return Math.round(totalRevenue);
    }

    public double getDayRevenue() {return Math.round(dayRevenue);}

    public double getLastDayRevenue() {return Math.round(lastDayRevenue);}

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

    public double getExpectedRevenue(int normalCarsStillInGarage,int carsWithReservationStillInGarage) {
        expectedRevenue = normalCarsStillInGarage * cost;
        expectedRevenue += carsWithReservationStillInGarage * 5;
        return Math.round(expectedRevenue);
    }

}
