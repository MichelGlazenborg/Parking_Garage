package models;

/**
 * Created by Jelmer on 30-Jan-17.
 */
public class Payment {
    private double totalRevenue;
    private double dayRevenue;
    private double cost;        //cost in euro's per minute

    public Payment() {
        totalRevenue = 0.0;
        cost = 0;
    }

    public double getTotalRevenue() {
        return Math.round(totalRevenue);
    }

    public double getDayRevenue() {return Math.round(dayRevenue);}

    public void resetDayRevenue() {dayRevenue = 0;}

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
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getExpectedRevenue(int normalCarsStillInGarage,int carsWithReservationStillInGarage) {
        double expectedRevenue = normalCarsStillInGarage * cost;
        expectedRevenue += carsWithReservationStillInGarage * cost + (carsWithReservationStillInGarage * 5);
        return Math.round(expectedRevenue);
    }
}
