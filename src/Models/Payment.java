package Models;

/**
 * Created by Jelmer on 30-Jan-17.
 */
public class Payment {
    private double totalRevenue;

    public Payment() {
        totalRevenue = 0;
    }

    public double getTotalRevenue() {
        return Math.round(totalRevenue);
    }

    public void pay(double cost, int stayTime) {
        totalRevenue += cost * (double) stayTime;
    }

}
