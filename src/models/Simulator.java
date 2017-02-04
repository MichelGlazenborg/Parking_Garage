package models;

import javafx.scene.canvas.Canvas;

import java.util.Random;

public class Simulator {

	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RES = "3";

	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue entranceResQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;
    private Payment pay;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int week = 0;

    private int weekDayArrivals= 30; // average number of arriving cars per hour
    private int weekendArrivals = 45; // average number of arriving cars per hour
    private int weekDayPassArrivals= 30; // average number of arriving cars per hour
    private int weekendPassArrivals = 40; // average number of arriving cars per hour
    private int weekDayResArrivals = 26;
    private int weekendResArrivals = 40;

    private int enterSpeed = 1; // number of cars that can enter per minute
    private int paymentSpeed = 1; // number of cars that can pay per minute
    private int exitSpeed = 2; // number of cars that can leave per minute

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
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        entranceResQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        pay = new Payment();
        simulatorView = new SimulatorView(canvas, 3, 6, 30);
        pay.setCost(0.067);
        updateViews();
    }


    /**
     * returns the current instance of simulatorView
     * @return the current instance of the class simulatorView
     */
    public SimulatorView getView() {
        return simulatorView;
    }

    /**
     * Ticks the simulation forward by calling other methods like advanceTime(), updateViews() and handles the entering and leaving of cars
     */
    public void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	handleEntrance();
    }

    /**
     * advances the time forward by 1 minute
     */
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
                pay.setLasDayRevenue(pay.getDayRevenue());
                pay.resetDayRevenue();
        }
        while (day > 6) {
            day -= 7;
            week++;
            resetArrivalCounter();
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
        this.week = week;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * gets the the current time
     * @return an array of integers representing the week, day, hour and minute
     */
    public int[] getTime() {
        int[] time = new int[4];
        time[0] = minute;
        time[1] = hour;
        time[2] = day;
        time[3] = week;
        return time;
    }

    /**
     * takes cars from the carQueue and lets them enter the garage
     */
    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue,true, false);
    	carsEntering(entranceCarQueue,false, false);
    	carsEntering(entranceResQueue,false, true);
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
    	simulatorView.tick();
        // Update the car park view.
        simulatorView.updateView();
    }

    /**
     * sets the cost of the parking garage per minute
     * @param price the price per double
     */
    public void setCost(double price) {
        pay.setCost(price);
    }

    /**
     * modifier for the arrival numbers
     * @param modifier the modifier for the arrival numbers
     */
    private void modifyArrivalNumbers(double modifier) {
        weekDayArrivals *= modifier;
        weekendArrivals *= modifier;
        weekDayPassArrivals *= modifier;
        weekendPassArrivals *= modifier;
        weekDayResArrivals *= modifier;
        weekendResArrivals *= modifier;
    }

    private void setArrivalNumbersBack(){
        weekDayArrivals = 50;
        weekendArrivals = 100;
        weekDayPassArrivals = 30;
        weekendPassArrivals = 40;
        weekDayResArrivals = 40;
        weekendResArrivals = 25;
    }

    /**
     * adds new cars to the carQueue's
     */
    private void carsArriving(){
        switch(day) {
            case 0 : {
                if(hour > 23 && hour < 7) {
                    modifyArrivalNumbers(0.5);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
            case 1 : {
                if(hour > 23 && hour < 7) {
                    modifyArrivalNumbers(0.5);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
            case 2 : {
                if(hour > 23 && hour < 7) {
                    modifyArrivalNumbers(0.5);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
            case 3 : {
                if(hour > 23 && hour < 7) {
                    modifyArrivalNumbers(0.5);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
            case 4 : {
                if(hour > 18 && hour < 23) {
                    modifyArrivalNumbers(2);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
            case 5 : {
                if(hour > 18 && hour < 3) {
                    modifyArrivalNumbers(2);
                    carsArrivingIn();
                    setArrivalNumbersBack();
                } else {
                    carsArrivingIn();
                }
                break;
            }
            case 6 : {
                if(hour > 23 && hour < 7) {
                    modifyArrivalNumbers(0.3);
                }
                carsArrivingIn();
                setArrivalNumbersBack();
                break;
            }
        }

    }

        private void carsArrivingIn() {
            int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
            addArrivingCars(numberOfCars, AD_HOC);
            numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
            addArrivingCars(numberOfCars, PASS);
            numberOfCars = getNumberOfCars(weekDayResArrivals, weekendResArrivals);
            addArrivingCars(numberOfCars, RES);
        }


    /**
     * removes a car from the carQueue and assigns it a parking space
     * @param queue the carQueue a car enters from
     */
    private void carsEntering(CarQueue queue, boolean passHolder, boolean hasReservation){
        double i = 0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue() > new Random().nextInt(4) && simulatorView.getNumberOfOpenSpots() > 0 && i < enterSpeed) {
    	    if(!passHolder) {
    	        if(!hasReservation) {
                    AdHocCar car = (AdHocCar) queue.removeCar();
                    Location freeLocation = simulatorView.getFirstFreeLocation();
                    if(freeLocation != null) {
                        simulatorView.setCarAt(freeLocation, car);
                        simulatorView.addOneCarToCount("AdHocCar");
                    }
                }else {
    	            CarWithReservedSpot car = (CarWithReservedSpot) queue.removeCar();
                    Location freeLocation = simulatorView.getFirstReservation(getTime());
                    if(freeLocation != null) {
                        simulatorView.setCarAt(freeLocation, car);
                        simulatorView.addOneCarToCount("CarWithReservedSpot");
                    }

                }
            } else if(passHolder && simulatorView.getNumberOfPassHolders() < simulatorView.getPassHolderSpots()) {
    	        ParkingPassCar car = (ParkingPassCar) queue.removeCar();
    	        Location freeLocation = simulatorView.getFirstPassSpot();
    	        if(freeLocation == null) {
                    freeLocation = simulatorView.getFirstFreeLocation();
                    if(freeLocation != null) {
                        simulatorView.setCarAt(freeLocation, car);
                        simulatorView.addOneCarToCount("ParkingPassCar");
                    }
                } else {
                    simulatorView.setCarAt(freeLocation, car);
                    simulatorView.addOneCarToCount("ParkingPassCar");
                }
            }
			i += 0.33;

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

    public CarQueue getEntranceCarQueue() {
        return entranceCarQueue;
    }

    public CarQueue getEntrancePassQueue() {
        return entrancePassQueue;
    }

    public CarQueue getEntranceResQueue() {
        return entranceResQueue;
    }

    public CarQueue getExitCarQueue() {
        return exitCarQueue;
    }

    public CarQueue getPaymentCarQueue() {
        return paymentCarQueue;
    }

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
        Car car = simulatorView.getFirstLeavingCar();
        while (car != null) {
            simulatorView.removeCarFromCount(car.getClass().getSimpleName());
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car, (car instanceof ParkingPassCar));
        	}
            car = simulatorView.getFirstLeavingCar();
        }
    }

    /**
     * Makes the cars pay
     */
    private void carsPaying(){
        // Let cars pay.
    	int i = 0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            if(car.getHasToPay()) {
                if(car.getHasReservation()) {
                    pay.payExtra(5);
                }
                pay.pay(car.getStayMinutes());
            }
            carLeavesSpot(car, (car instanceof ParkingPassCar));
            i++;
    	}
    }

    public double getRevenue() {
        return pay.getTotalRevenue();
    }

    public double getDayRevenue() {
        return pay.getLastDayRevenue();
    }

    public double getExpectedRevenue() {
        int adHocCars = simulatorView.getNumberOfAdHoc();
        int carsWithReservations = simulatorView.getNumberOfCarsWithReservation();
        double expectedRevenue = pay.getExpectedRevenue(adHocCars, carsWithReservations);
        return expectedRevenue;

    }

    public int[] getStatistics() {
        int[] statistics = new int[6];

        statistics[0] = simulatorView.getNumberOfCarsWithReservation();
        statistics[1] = simulatorView.getNumberOfAdHoc();
        statistics[2] = simulatorView.getNumberOfPassHolders();

        return statistics;
    }

    /**
     * Makes the cars leave the parking garage
     */
    private void carsLeaving(){
        // Let cars leave.
    	int i = 0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
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
        int averageNumberOfCarsPerHour = day < 5 ? weekDay : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);
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
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
          
                /*for (int i = 0; i < numberOfCars; i++) {
                    if(simulatorView.getNumberOfPassHolderSpots() > 0)
                    entrancePassQueue.addCar(new ParkingPassCar());*/
  
                if (simulatorView.getNumberOfPassHolders() < simulatorView.getnumberOfPassHolderSpots()-5){
                    for (int i = 0; i < numberOfCars; i++) {
                        entrancePassQueue.addCar(new ParkingPassCar());
                    }
                }
                break;
            case RES:
                for(int i = 0; i < numberOfCars; i++) {
                    Random ran = new Random();
                    simulatorView.makeReservationsAt(new Location(ran.nextInt(3),ran.nextInt(6),ran.nextInt(30)), minute, hour);
                    entranceResQueue.addCar(new CarWithReservedSpot());
                }
    	}
    }

    /**
     * Makes the car leaves the spot
     * @param car the car that has to leave the spot
     */
    private void carLeavesSpot(Car car, boolean hasParkingPass){
		Location location = car.getLocation();
    	simulatorView.removeCarAt(location);

    	/**
		 * Temporary fix, this should be changed as soon as the manager can decide where he/she wants the reserved
		 * spots to be
		 */
    	if (hasParkingPass)
    	    if(location.getRow() <= simulatorView.getPassHolderSpots()) {
                simulatorView.setPassHolderSpace(location, new PassHolderSpace());
            }

        exitCarQueue.addCar(car);
    }

    public SimulatorView getSimulatorView() {
        return simulatorView;
    }

    public void resetRevenue() {
        pay.reset();
        pay.resetDayRevenue();
        pay.setLasDayRevenue(0);

    }

    public void resetTime() {
        day = 0;
        hour = 0;
        minute = 0;
        week = 0;
    }
    public void resetStats() {
        while(entranceCarQueue.carsInQueue() > 0) {
            entranceCarQueue.removeCar();
        }
        while(entrancePassQueue.carsInQueue() > 0) {
            entrancePassQueue.removeCar();
        }
        while(entranceResQueue.carsInQueue() > 0) {
            entranceResQueue.removeCar();
        }
        while(paymentCarQueue.carsInQueue() > 0) {
            paymentCarQueue.removeCar();
        }
    }

    public void resetArrivalCounter() {
        _arrivalsOnMonday = 0;
        _arrivalsOnTuesday = 0;
        _arrivalsOnWednesday = 0;
        _arrivalsOnThursday = 0;
        _arrivalsOnFriday = 0;
        _arrivalsOnSaturday = 0;
        _arrivalsOnSunday = 0;
    }

    public int getArrivalsOnMonday() {
        return _arrivalsOnMonday;
    }

    public int getArrivalsOnTuesday() {
        return _arrivalsOnTuesday;
    }

    public int getArrivalsOnWednesday() {
        return _arrivalsOnWednesday;
    }

    public int getArrivalsOnThursday() {
        return _arrivalsOnThursday;
    }

    public int getArrivalsOnFriday() {
        return _arrivalsOnFriday;
    }

    public int getArrivalsOnSaturday() {
        return _arrivalsOnSaturday;
    }

    public int getArrivalsOnSunday() {
        return _arrivalsOnSunday;
    }
}
