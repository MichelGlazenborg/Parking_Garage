import javax.swing.*;

/**
 * Created by Jelmer on 13-Jan-17.
 */
public class Menu {

    private JMenuBar _menu;
    private SimulatorView _simulatorView;
    //private Simulator _simulator;

    public Menu(SimulatorView simulatorView)
    {
        _menu = new JMenuBar();
        _simulatorView = simulatorView;
    }

    public JMenuBar getMenuBar() {
        //create the execute menu
        JMenu executeMenu = new JMenu("Execute");
        _menu.add(executeMenu);

        JMenuItem run50 = new JMenu("Run for 50 ticks");
        run50.addActionListener(e -> _simulatorView.run50());
        executeMenu.add(run50);

        return _menu;
    }
}
