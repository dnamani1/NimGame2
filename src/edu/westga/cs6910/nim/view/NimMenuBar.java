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
	private NimPane nimPane;
	private boolean shouldShowHelpDialog;

	/**
	 * This constructor create NimMenuBar object.
	 * 
	 * @param nimPane              main view
	 * @param shouldShowHelpDialog to be shown or not
	 */
	public NimMenuBar(NimPane nimPane, boolean shouldShowHelpDialog) {
		this.nimPane = nimPane;
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

		RadioMenuItem mnuCautious = new RadioMenuItem("_Cautious");
		mnuCautious.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
		mnuCautious
				.setOnAction(event -> this.nimPane.getGame().getComputerPlayer().setStrategy(new CautiousStrategy()));
		mnuCautious.setMnemonicParsing(true);
		mnuCautious.setToggleGroup(tglStrategy);

		RadioMenuItem mnuGreedy = new RadioMenuItem("Gr_eedy");
		mnuGreedy.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN));
		mnuGreedy.setOnAction(event -> this.nimPane.getGame().getComputerPlayer().setStrategy(new GreedyStrategy()));
		mnuGreedy.setMnemonicParsing(true);
		mnuGreedy.setToggleGroup(tglStrategy);

		RadioMenuItem mnuRandom = new RadioMenuItem("_Random");
		mnuRandom.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN));
		mnuRandom.setOnAction(event -> this.nimPane.getGame().getComputerPlayer().setStrategy(new RandomStrategy()));
		mnuRandom.setMnemonicParsing(true);
		mnuRandom.setToggleGroup(tglStrategy);

		NumberOfSticksStrategy currentStrategy = this.nimPane.getGame().getComputerPlayer().getStrategy();
		if (currentStrategy.getClass() == CautiousStrategy.class) {
			mnuCautious.setSelected(true);
		} else if (currentStrategy.getClass() == RandomStrategy.class) {
			mnuRandom.setSelected(true);
		} else {
			mnuGreedy.setSelected(true);
		}

		mnuSettings.getItems().addAll(mnuCautious, mnuGreedy, mnuRandom);
		return mnuSettings;
	}

	private Menu createGameMenu() {
		Menu mnuFile = new Menu("_Game");
		mnuFile.setMnemonicParsing(true);

		MenuItem mnuNew = new MenuItem("_New");
		mnuNew.setMnemonicParsing(true);
		mnuNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
		mnuNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent theEventObject) {
				NimMenuBar.this.nimPane.getPnChooseFirstPlayer().reset();
				NimMenuBar.this.nimPane.getPnChooseFirstPlayer().setDisable(false);
				NimMenuBar.this.nimPane.getPnHumanPlayer().setDisable(true);
				NimMenuBar.this.nimPane.getPnComputerPlayer().setDisable(true);
				NimMenuBar.this.nimPane.getPnComputerPlayer().resetNumberTaken();
				NimMenuBar.this.nimPane.getPnHumanPlayer().resetNumberToTakeComboBox();
				if (NimMenuBar.this.nimPane.isShouldShowHelpDialog()) {
					NimHelpDialog.showHelpDialog(true);
					NimMenuBar.this.nimPane.setShouldShowHelpDialog(false);
				}
			}
		});

		MenuItem mnuExit = new MenuItem("E_xit");
		mnuExit.setMnemonicParsing(true);
		mnuExit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
		mnuExit.setOnAction(event -> System.exit(0));

		mnuFile.getItems().addAll(mnuNew, mnuExit);
		return mnuFile;
	}
}
