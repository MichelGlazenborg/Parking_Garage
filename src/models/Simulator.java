package models;

import javafx.scene.canvas.Canvas;

import java.math.BigDecimal;
import java.util.Random;

public class Simulator {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RES = "3";

	private CarQueue _entranceCarQueue;
    private CarQueue _entrancePassQueue;
    private CarQueue _entranceResQueue;
    private CarQueue _paymentCarQueue;
    private CarQueue _exitCarQueue;
    private Garage _garage;
    private Payment _pay;

    private int _day = 0;
    private int _hour = 8;
    private int _minute = 0;
    private int _week = 1;

    private int _weekDayArrivals = 30; // average number of arriving cars per hour
    private int _weekendArrivals = 45; // average number of arriving cars per hour
    private int _weekDayPassArrivals = 30; // average number of arriving cars per hour
    private int _weekendPassArrivals = 40; // average number of arriving cars per hour
    private int _weekDayResArrivals = 26;
    private int _weekendResArrivals = 40;

    private int _enterSpeed = 1;   // number of cars that can enter per minute (Global to make easily editable)
    private int _paymentSpeed = 3; // number of cars that can pay per minute   (Global to make easily editable)
    private int _exitSpeed = 2;    // number of cars that can leave per minute (Global to make easily editable)

    private int _arrivalsOnMonday = 0;
    private int _arrivalsOnTuesday = 0;
    private int _arrivalsOnWednesday = 0;
    private int _arrivalsOnThursday = 0;
    private int _arrivalsOnFriday = 0;
    private int _arrivalsOnSaturday = 0;
    private int _arrivalsOnSunday = 0;

    /**
     * The constructor of the class Simulator, runs the main simulator by handling arriving/leaving cars, keeps count of the time and Payments
     */
    public Simulator(Canvas canvas) {
        _entranceCarQueue = new CarQueue();
        _entrancePassQueue = new CarQueue();
        _entranceResQueue = new CarQueue();
        _paymentCarQueue = new CarQueue();
        _exitCarQueue = new CarQueue();
        _pay = new Payment();
        _garage = new Garage(canvas,3, 6, 30);
        _pay.setCost(0.00667);
        updateViews();
    }

    /**
     * returns the current instance of garage
     * @return the current instance of the class garage
     */
    public Garage getView() {
        return _garage;
    }

    /**
     * Ticks the simulation forward by calling other methods like advanceTime(), updateViews() and handles the entering and leaving of cars
     */
    public void tick() {
    	advanceTime();
    	handleExit();
    	handleEntrance();
        updateViews();
    }

    /**
     * advances the time forward by 1 minute
     */
    private void advanceTime(){
        // Advance the time by one minute.
        _minute++;
        while (_minute > 59) {
            _minute -= 60;
            _hour++;
        }
        while (_hour > 23) {
            _hour -= 24;
            _day++;
                _pay.setLastDayRevenue(_pay.getDoubleDayRevenue());
                _pay.resetDayRevenue();
        }
        while (_day > 6) {
            _day -= 7;
            _week++;
            resetArrivalCounter();
        }
        while (_week > 52) {
            _week = 1;
        }
    }

    /**
     * sets the time
     * @param week the user entered week number
     * @param day the user entered day number
     * @param hour the user entered hour
     * @param minute the user entered minute
     */
    public void setTime(int week, int day, int hour, int minute) {
        _week = week;
        _day = day;
        _hour = hour;
        _minute = minute;
    }

    /**
     * gets the the current time
     * @return an array of integers representing the week, day, hour and minute
     */
    public int[] getTime() {
        int[] time = new int[4];
        time[0] = _minute;
        time[1] = _hour;
        time[2] = _day;
        time[3] = _week;
        return time;
    }

    /**
     * takes cars from the carQueue and lets them enter the garage
     */
    private void handleEntrance(){
    	carsArriving();
    	carsEntering(_entrancePassQueue,true, false);
    	carsEntering(_entranceCarQueue,false, false);
    	carsEntering(_entranceResQueue,false, true);
    }

    /**
     * Makes cars leave and pay the parking garage
     */
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }

    /**
     * updates the simulator view
     */
    private void updateViews(){
    	_garage.tick();
        // Update the car park view.
        _garage.updateView();
    }

    /**
     * sets the cost of the parking garage per minute
     * @param price the price per double
     */
    public void setCost(double price) {
        _pay.setCost(price);
    }

    /**
     * modifier for the arrival numbers
     * @param modifier the modifier for the arrival numbers
     */
    private void modifyArrivalNumbers(double modifier) {
        _weekDayArrivals *= modifier;
        _weekendArrivals *= modifier;
        _weekDayPassArrivals *= modifier;
        _weekendPassArrivals *= modifier;
        _weekDayResArrivals *= modifier;
        _weekendResArrivals *= modifier;
    }

    /**
     * Resets the arrival numbers back to their original value
     */
    private void resetArrivalNumbers(){
        _weekDayArrivals = 30;
        _weekendArrivals = 45;
        _weekDayPassArrivals = 30;
        _weekendPassArrivals = 40;
        _weekDayResArrivals = 26;
        _weekendResArrivals = 40;
    }

    /**
     * adds new cars to the carQueue's
     */
    private void carsArriving(){
        switch(_day) {
            case 0:
            case 1:
            case 2:
            case 3: {
                if (_hour > 23 || _hour < 7)
                    modifyArrivalNumbers(0.5);

                carsArrivingIn();
                resetArrivalNumbers();
                break;
            }
            case 4: {
                if (_hour > 18)
                    modifyArrivalNumbers(2);

                carsArrivingIn();
                resetArrivalNumbers();
                break;
            }
            case 5: {
                if (_hour > 18 || _hour < 3) {
                    modifyArrivalNumbers(2);
                    carsArrivingIn();
                    resetArrivalNumbers();
                } else {
                    carsArrivingIn();
                }
                break;
            }
            case 6: {
                if(_hour > 23 || _hour < 7)
                    modifyArrivalNumbers(0.3);

                carsArrivingIn();
                resetArrivalNumbers();
                break;
            }
        }

    }

    /**
     * Makes the cars arrive to the garage
     */
    private void carsArrivingIn() {
        int numberOfCars = getNumberOfCars(_weekDayArrivals, _weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);

        numberOfCars = getNumberOfCars(_weekDayPassArrivals, _weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);

        numberOfCars = getNumberOfCars(_weekDayResArrivals, _weekendResArrivals);
        addArrivingCars(numberOfCars, RES);
    }

    /**
     * removes a car from the carQueue and assigns it a parking space
     * @param queue the carQueue a car enters from
     */
    private void carsEntering(CarQueue queue, boolean passHolder, boolean hasReservation){
        double i = 0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue() > new Random().nextInt(4) && _garage.getNumberOfOpenSpots() > 0 && i < _enterSpeed) {
    	    if(!passHolder) {
    	        if(!hasReservation) {
                    AdHocCar car = (AdHocCar) queue.removeCar();
                    Location freeLocation = _garage.getFirstFreeLocation();
                    if(freeLocation != null) {
                        _garage.setCarAt(freeLocation, car);
                        _garage.addOneCarToCount("AdHocCar");
                    }
                }
                else {
    	            CarWithReservedSpot car = (CarWithReservedSpot) queue.removeCar();
                    Location freeLocation = _garage.getFirstReservation(getTime());
                    if(freeLocation != null) {
                        _garage.setCarAt(freeLocation, car);
                        _garage.addOneCarToCount("CarWithReservedSpot");
                    }

                }
            }
            else if(_garage.getNumberOfPassHolders() < _garage.getPassHolderSpots()) {
    	        ParkingPassCar car = (ParkingPassCar) queue.removeCar();
    	        Location freeLocation = _garage.getFirstPassSpot();

    	        if (freeLocation == null) {
                    freeLocation = _garage.getFirstFreeLocation();
                    if(freeLocation != null) {
                        _garage.setCarAt(freeLocation, car);
                        _garage.addOneCarToCount("ParkingPassCar");
                    }
                }
                else {
                    _garage.setCarAt(freeLocation, car);
                    _garage.addOneCarToCount("ParkingPassCar");
                }
            }
			i += 1;

    	    switch (getTime()[2]) {
                case 0:
                    _arrivalsOnMonday++;
                    break;
                case 1:
                    _arrivalsOnTuesday++;
                    break;
                case 2:
                    _arrivalsOnWednesday++;
                    break;
                case 3:
                    _arrivalsOnThursday++;
                    break;
                case 4:
                    _arrivalsOnFriday++;
                    break;
                case 5:
                    _arrivalsOnSaturday++;
                    break;
                case 6:
                    _arrivalsOnSunday++;
                    break;
            }
        }
    }

    /**
     * Gets the normal car entrance queue
     * @return  the normal car entrance queue
     */
    private CarQueue getEntranceCarQueue() {
        return _entranceCarQueue;
    }

    /**
     * Gets the passholder car entrance queue
     * @return  the passholder car entrance queue
     */
    private CarQueue getEntrancePassQueue() {
        return _entrancePassQueue;
    }

    /**
     * Gets the reservation car entrance queue
     * @return  the reservation car entrance queue
     */
    private CarQueue getEntranceResQueue() {
        return _entranceResQueue;
    }

    /**
     * Gets the exit car queue
     * @return  the exit car queue
     */
    private CarQueue getExitCarQueue() {
        return _exitCarQueue;
    }

    /**
     * Gets the payment car queue
     * @return  the payment car queue
     */
    private CarQueue getPaymentCarQueue() {
        return _paymentCarQueue;
    }

    /**
     * Gets all the queues
     * @return an array of integers that contains the number of cars per queue
     */
    public int[] getQueues() {
        int[] queues = new int[5];

        CarQueue queue = getEntranceCarQueue();
        queues[0] = queue.carsInQueue();

        queue = getEntrancePassQueue();
        queues[1] = queue.carsInQueue();

        queue = getEntranceResQueue();
        queues[2] = queue.carsInQueue();

        queue = getExitCarQueue();
        queues[3] = queue.carsInQueue();

        queue = getPaymentCarQueue();
        queues[4] = queue.carsInQueue();

        return queues;
    }
    /**
     * adds leaving cars to the payment queue.
     */
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = _garage.getFirstLeavingCar();
        while (car != null) {
            _garage.removeCarFromCount(car.getClass().getSimpleName());
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            _paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car, (car instanceof ParkingPassCar));
        	}
            car = _garage.getFirstLeavingCar();
        }
    }

    /**
     * Makes the cars pay
     */
    private void carsPaying(){
    	int i = 0;
    	while (_paymentCarQueue.carsInQueue()>0 && i < _paymentSpeed){
            Car car = _paymentCarQueue.removeCar();
            if(car.getHasToPay()) {
                if(car.getHasReservation()) {
                    _pay.payExtra(5);
                }
                _pay.pay(car.getStayMinutes());
            }

            carLeavesSpot(car, (car instanceof ParkingPassCar));
            i++;
    	}
    }

    /**
     * Gets the total revenue from the payment class
     * @return The total revenue
     */
    public BigDecimal getRevenue() {
        return _pay.getTotalRevenue();
    }

    /**
     * Gets the last day revenue from the payment class
     * @return The last day revenue
     */
    public BigDecimal getDayRevenue() {
        return _pay.getLastDayRevenue();
    }

    /**
     * Gets the expected revenue from the payment class
     * @return  the expected revenue
     */
    public BigDecimal getExpectedRevenue() {
        int adHocCars = _garage.getNumberOfAdHoc();
        int carsWithReservations = _garage.getNumberOfCarsWithReservation();
        return _pay.getExpectedRevenue(adHocCars, carsWithReservations);
    }

    /**
     * Gets the statistics
     * @return An array of integers containing the number of cars per type
     */
    public int[] getStatistics() {
        int[] statistics = new int[3];

        statistics[0] = _garage.getNumberOfCarsWithReservation();
        statistics[1] = _garage.getNumberOfAdHoc();
        statistics[2] = _garage.getNumberOfPassHolders();

        return statistics;
    }

    /**
     * Makes the cars leave the parking garage
     */
    private void carsLeaving(){
        // Let cars leave.
    	int i = 0;
    	while (_exitCarQueue.carsInQueue()>0 && i < _exitSpeed){
            _exitCarQueue.removeCar();
            i++;
    	}
    }

    /**
     *
     * @param weekDay The number of cars on an weekDay
     * @param weekend The number of cars on a weekend
     * @return int the number of cars per hour on the current day
     */
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = _day < 5 ? weekDay : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int) Math.round(numberOfCarsPerHour / 60);
    }

    /**
     * Add cars to the arriving Queue's
     * @param numberOfCars numbers of cars to be added to the queue of arriving cars
     * @param type the type of the car
     */
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    _entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                if (_garage.getNumberOfPassHolders() < _garage.getNumberOfPassHolderSpots()-5){
                    for (int i = 0; i < numberOfCars; i++) {
                        _entrancePassQueue.addCar(new ParkingPassCar());
                    }
                }
                break;
            case RES:
                for(int i = 0; i < numberOfCars; i++) {
                    Random ran = new Random();
                    _garage.makeReservationsAt(new Location(ran.nextInt(_garage.getNumberOfFloors()), ran.nextInt(_garage.getNumberOfRows()), ran.nextInt(_garage.getNumberOfPlaces())), _minute, _hour);
                    _entranceResQueue.addCar(new CarWithReservedSpot());
                }
    	}
    }

    /**
     * Makes the car leaves the spot
     * @param car the car that has to leave the spot
     */
    private void carLeavesSpot(Car car, boolean hasParkingPass){
		Location location = car.getLocation();
        _garage.removeCarAt(location);

    	if (hasParkingPass && location.getRow() <= _garage.getPassHolderSpots())
            _garage.setPassHolderSpace(location, new PassHolderSpace());

        _exitCarQueue.addCar(car);
    }

    /**
     * Gets the garage that was made
     * @return The garage
     */
    public Garage getGarage() {
        return _garage;
    }

    /**
     * Resets the all revenue
     */
    public void resetRevenue() {
        _pay.reset();
        _pay.setLastDayRevenue(0);
    }

    /**
     * Resets the time to the start
     */
    public void resetTime() {
        _day = 0;
        _hour = 8;
        _minute = 0;
        _week = 1;
    }

    /**
     * Resets the statistics
     */
    public void resetStats() {
        while (_entranceCarQueue.carsInQueue() > 0) {
            _entranceCarQueue.removeCar();
        }
        while (_entrancePassQueue.carsInQueue() > 0) {
            _entrancePassQueue.removeCar();
        }
        while (_entranceResQueue.carsInQueue() > 0) {
            _entranceResQueue.removeCar();
        }
        while (_paymentCarQueue.carsInQueue() > 0) {
            _paymentCarQueue.removeCar();
        }
    }

    /**
     * Resets the arrivalcounters
     */
    public void resetArrivalCounter() {
        _arrivalsOnMonday = 0;
        _arrivalsOnTuesday = 0;
        _arrivalsOnWednesday = 0;
        _arrivalsOnThursday = 0;
        _arrivalsOnFriday = 0;
        _arrivalsOnSaturday = 0;
        _arrivalsOnSunday = 0;
    }

    /**
     * Gets the amount of arrivals on monday
     * @return The amount of arrivals on monday
     */
    public int getArrivalsOnMonday() {
        return _arrivalsOnMonday;
    }

    /**
     * Gets the amount of arrivals on Tuesday
     * @return The amount of arrivals on Tuesday
     */
    public int getArrivalsOnTuesday() {
        return _arrivalsOnTuesday;
    }

    /**
     * Gets the amount of arrivals on Wednesday
     * @return The amount of arrivals on Wednesday
     */
    public int getArrivalsOnWednesday() {
        return _arrivalsOnWednesday;
    }

    /**
     * Gets the amount of arrivals on Thursday
     * @return The amount of arrivals on Thursday
     */
    public int getArrivalsOnThursday() {
        return _arrivalsOnThursday;
    }

    /**
     * Gets the amount of arrivals on Friday
     * @return The amount of arrivals on Friday
     */
    public int getArrivalsOnFriday() {
        return _arrivalsOnFriday;
    }

    /**
     * Gets the amount of arrivals on Saturday
     * @return The amount of arrivals on Saturday
     */
    public int getArrivalsOnSaturday() {
        return _arrivalsOnSaturday;
    }

    /**
     * Gets the amount of arrivals on Sunday
     * @return The amount of arrivals on Sunday
     */
    public int getArrivalsOnSunday() {
        return _arrivalsOnSunday;
    }
}
