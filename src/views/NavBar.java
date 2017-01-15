package views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import views.GUI;
import views.SimulatorView;

/**
 * Created by Jelmer on 13-Jan-17.
 */
public class NavBar extends GUI {

    private GUI _gui;
    private MenuBar _menuBar;

    public NavBar(GUI gui) {
        _gui = gui;
        _menuBar = new MenuBar();
    }

    public MenuBar generate() {
        // [Execute]
        Menu executeMenu = new Menu("Execute");
        MenuItem executeFiftyTicks = new MenuItem("Run for 50 ticks");

        executeFiftyTicks.setOnAction(e -> System.out.println("Test."));

        executeMenu.getItems().addAll(executeFiftyTicks);

        _menuBar.getMenus().addAll(executeMenu);
        return _menuBar;
    }
}
