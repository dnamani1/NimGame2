package edu.westga.cs6910.nim.view;

import java.time.LocalDate;

import edu.westga.cs6910.nim.model.strategy.CautiousStrategy;
import edu.westga.cs6910.nim.model.strategy.GreedyStrategy;
import edu.westga.cs6910.nim.model.strategy.NumberOfSticksStrategy;
import edu.westga.cs6910.nim.model.strategy.RandomStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;

/**
 * This class have all the menu bar menu items methods.
 * 
 * @author Deeksha Namani
 * @version 7/3/2023
 *
 */
public class NimMenuBar extends VBox {
    private FullNimPane fullNimPane;
    private boolean shouldShowHelpDialog;

    /**
     * This constructor creates NimMenuBar object.
     * 
     * @param fullNimPane          the main view
     * @param shouldShowHelpDialog to be shown or not
     */
    public NimMenuBar(FullNimPane fullNimPane, boolean shouldShowHelpDialog) {
        this.fullNimPane = fullNimPane;
        this.shouldShowHelpDialog = shouldShowHelpDialog;
        this.createMenu();
    }

    private void createMenu() {
        MenuBar mnuMain = new MenuBar();

        Menu mnuGame = this.createGameMenu();

        Menu mnuSettings = this.createStrategyMenu();

        Menu mnuHelp = this.createHelpMenu();

        mnuMain.getMenus().addAll(mnuGame, mnuSettings, mnuHelp);
        this.getChildren().add(mnuMain);
    }

    private Menu createHelpMenu() {
        Menu mnuHelp = new Menu("_Help");
        mnuHelp.setMnemonicParsing(true);

        MenuItem mnuShowHelp = new MenuItem("H_elp");
        mnuShowHelp.setOnAction(event -> NimHelpDialog.showHelpDialog(this.shouldShowHelpDialog));

        LocalDate currentDate = LocalDate.now();
        String author = "Deeksha Namani";
        String aboutText = "Date: " + currentDate + "\nAuthor: " + author;
        MenuItem mnuAbout = new MenuItem("_About");
        mnuAbout.setOnAction(event -> {
            Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
            aboutDialog.setTitle("About");
            aboutDialog.setHeaderText("About the Application");
            aboutDialog.setContentText(aboutText);
            aboutDialog.showAndWait();
        });

        mnuHelp.getItems().addAll(mnuShowHelp, mnuAbout);
        return mnuHelp;
    }

    private Menu createStrategyMenu() {
        Menu mnuSettings = new Menu("_Strategy");
        mnuSettings.setMnemonicParsing(true);

        ToggleGroup tglStrategy = new ToggleGroup();

        this.addStrategyItem(mnuSettings, tglStrategy, "_Cautious", KeyCode.C, new CautiousStrategy());
        this.addStrategyItem(mnuSettings, tglStrategy, "Gr_eedy", KeyCode.E, new GreedyStrategy());
        this.addStrategyItem(mnuSettings, tglStrategy, "_Random", KeyCode.R, new RandomStrategy());

        NumberOfSticksStrategy currentStrategy = this.fullNimPane.getGame().getComputerPlayer().getStrategy();
        for (MenuItem menuItem : mnuSettings.getItems()) {
            RadioMenuItem radioMenuItem = (RadioMenuItem) menuItem;
            radioMenuItem.setToggleGroup(tglStrategy);
            if (currentStrategy.getClass() == radioMenuItem.getUserData().getClass()) {
                radioMenuItem.setSelected(true);
            }
        }

        return mnuSettings;
    }

    private void addStrategyItem(Menu menu, ToggleGroup toggleGroup, String text, KeyCode accelerator,
            NumberOfSticksStrategy strategy) {
        RadioMenuItem menuItem = new RadioMenuItem(text);
        menuItem.setAccelerator(new KeyCodeCombination(accelerator, KeyCombination.SHORTCUT_DOWN));
        menuItem.setOnAction(event -> this.fullNimPane.getGame().getComputerPlayer().setStrategy(strategy));
        menuItem.setMnemonicParsing(true);
        menuItem.setToggleGroup(toggleGroup);
        menuItem.setUserData(strategy);
        menu.getItems().add(menuItem);
    }

    private Menu createGameMenu() {
        Menu mnuFile = new Menu("_Game");
        mnuFile.setMnemonicParsing(true);

        MenuItem mnuNew = new MenuItem("_New");
        mnuNew.setMnemonicParsing(true);
        mnuNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        mnuNew.setOnAction(new NewGameMenuItemHandler());

        MenuItem mnuExit = new MenuItem("E_xit");
        mnuExit.setMnemonicParsing(true);
        mnuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        mnuExit.setOnAction(event -> System.exit(0));

        mnuFile.getItems().addAll(mnuNew, mnuExit);
        return mnuFile;
    }

    private class NewGameMenuItemHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent theEventObject) {
            NimMenuBar.this.fullNimPane.resetNewGamePane();
            NimMenuBar.this.fullNimPane.getPnChooseFirstPlayer().setDisable(false);
            NimMenuBar.this.fullNimPane.getPnHumanPlayer().setDisable(true);
            NimMenuBar.this.fullNimPane.getPnComputerPlayer().setDisable(true);
            NimMenuBar.this.fullNimPane.getPnComputerPlayer().resetNumberTaken();
            NimMenuBar.this.fullNimPane.getPnHumanPlayer().resetNumberToTakeComboBox();
            if (NimMenuBar.this.shouldShowHelpDialog) {
                NimHelpDialog.showHelpDialog(true);
                NimMenuBar.this.shouldShowHelpDialog = false;
            }
        }
    }
}
