import javax.swing.*;

/**
 * Created by Jelmer on 13-Jan-17.
 */
public class Menu {
    public Menu(Simulator sim, SimulatorView view)
    {
            JMenuBar menubar = new JMenuBar();
            view.setJMenuBar(menubar);

            //create the execute menu
            JMenu executeMenu = new JMenu("Execute");
            menubar.add(executeMenu);

            JMenuItem run50 = new JMenu("Run for 50 ticks");
                run50.addActionListener( e -> {for(int i=0; i<50; i++){sim.run();}} );
            executeMenu.add(run50);
    }
}
