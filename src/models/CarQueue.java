package models;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is used for storing car queues.
 * @author  MATA
 * @since   1.0
 */
public class CarQueue {

    /**
     * Cars are added to this queue.
     */
    private final Queue<Car> _queue = new LinkedList<>();

    /**
     * Adds a car to the queue.
     * @param   car     The car object that should be added.
     * @return  boolean Whether or not the program was successful in adding the car to the queue
     */
    public boolean addCar(Car car) {
        return _queue.add(car);
    }

    /**
     * Removes a car from the que
     * @return  car     The car that was removed
     */
    public Car removeCar() {
        return _queue.poll();
    }

    /**
     * Returns the size of the que
     * @return  int     The queue size
     */
    public int carsInQueue(){
    	return _queue.size();
    }
}
