package models;

import java.math.BigDecimal;

public class Payment {

    private double _totalRevenue;   //The total revenue
    private double _dayRevenue;     //The revenue of the day
    private double _lastDayRevenue; //The revenue of the last day
    private double _expectedRevenue;//The expected revenue
    private double _cost;           //cost in euros per minute

    /**
     * The constructor for payment
     * Al revenues and the cost are set to 0
     */
    public Payment() {
        _totalRevenue = 0.0;
        _cost = 0;
        _expectedRevenue = 0;
    }

    /**
     * Returns the total revenue round down to 2 decimals
     * @return The total revenue round down to 2 decimals
     */
    public BigDecimal getTotalRevenue() {
        BigDecimal bd = new BigDecimal(_totalRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Returns the day revenue round down to 2 decimals
     * @return The day revenue round down to 2 decimals
     */
    public BigDecimal getDayRevenue() {
        BigDecimal bd = new BigDecimal(_dayRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Returns the day revenue round down to 2 decimals
     * @return The day revenue round down to 2 decimals
     */
    public BigDecimal getLastDayRevenue() {
        BigDecimal bd = new BigDecimal(_lastDayRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * Returns the day revenue in double type
     * @return The day revenue in double type
     */
    public double getDoubleDayRevenue() {
        return _dayRevenue;
    }

    /**
     * Sets the last day revenue
     * @param lastDayRevenue The variable _lastDayRevenue will be set to this value
     */
    public void setLastDayRevenue(double lastDayRevenue) {
        _lastDayRevenue = lastDayRevenue;
    }

    /**
     * Handles the payment when cars leave
     * @param stayTime The amount of time the car was in the garage
     */
    public void pay(int stayTime) {
        _totalRevenue += _cost * (double) stayTime;
        _dayRevenue   += _cost * (double) stayTime;
    }

    /**
     * Handels extra payment for cars that have to pay extra (Cars with reservations for example)
     * @param extra The amount that has to be payed extra
     */
    public void payExtra(double extra) {
        _totalRevenue += extra;
        _dayRevenue   += extra;
    }

    public void resetDayRevenue() {
        _dayRevenue = 0;
    }

    /**
     * Sets total revenue and expected revenue to 0
     */
    public void reset() {
        _totalRevenue = 0;
        _expectedRevenue = 0;
    }

    /**
     * Sets to cost to a specific value
     * @param cost The value the cost per minute will be set to
     */
    public void setCost(double cost) {
        _cost = cost;
    }

    /**
     * Gets the expected revenue of al the cars still in the garage
     * @param normalCarsStillInGarage       The amount of AdHoc Cars still in the garage
     * @param carsWithReservationStillInGarage  The amount of Cars that had a reservation
     * @return The expected revenue
     */
    public BigDecimal getExpectedRevenue(int normalCarsStillInGarage, int carsWithReservationStillInGarage) {
        _expectedRevenue = normalCarsStillInGarage * _cost;
        _expectedRevenue += (carsWithReservationStillInGarage * _cost) + (carsWithReservationStillInGarage * 5);

        BigDecimal bd = new BigDecimal(_expectedRevenue);
        return bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
}
