package Models;

/**
 * Created by Jelmer on 30-Jan-17.
 */
public class Payment {
    private double totalRevenue;
    private double cost;        //cost in euro's per minute

    public Payment() {
        totalRevenue = 0.0;
        cost = 0;
    }

    public double getTotalRevenue() {
        return Math.round(totalRevenue);
    }

    public void pay(int stayTime) {
        totalRevenue += cost * (double) stayTime;
    }

    public void payExtra(double extra) {
        totalRevenue += extra;
    }
  
    public void reset() {
        totalRevenue = 0;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
