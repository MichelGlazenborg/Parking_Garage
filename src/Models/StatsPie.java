package Models;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class StatsPie {

    private ArrayList<StatsData> _statsData;

    private StatsData _unoccupied;
    private StatsData _passholders;
    private StatsData _adhoc;
    private StatsData _reservations;

    public StatsPie() {
        _statsData = new ArrayList<>();
        reset();
    }

    public void reset() {
        _statsData.add(_unoccupied = new StatsData("Unoccupied", 100));
        _statsData.add(_passholders = new StatsData("Pass holders", 0));
        _statsData.add(_adhoc = new StatsData("Adhoc", 0));
        _statsData.add(_reservations = new StatsData("Reservations", 0));
    }

    public void update(int total, int unoccupied, int passHolders, int adhoc, int reservations) {
        // Calculate percentages here

        _unoccupied.setPercentage(unoccupied);
        _passholders.setPercentage(passHolders);
        _adhoc.setPercentage(adhoc);
        _reservations.setPercentage(reservations);
    }

    public ArrayList getData() {
        return _statsData;
    }
}