import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> _queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return _queue.add(car);
    }

    public Car removeCar() {
        return _queue.poll();
    }

    public int carsInQueue(){
    	return _queue.size();
    }
}
