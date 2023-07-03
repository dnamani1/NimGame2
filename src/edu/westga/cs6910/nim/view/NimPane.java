package edu.westga.cs6910.nim.view;

import edu.westga.cs6910.nim.model.Game;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Defines a GUI for the 1-pile Nim game. This class was started by CS6910
 * 
 * @author Deeksha Namani
 * @version 7/3/2023
 */
public class NimPane extends BorderPane {
	private Game theGame;
	private BorderPane pnContent;
	private HumanPane pnHumanPlayer;
	private ComputerPane pnComputerPlayer;
	private StatusPane pnGameInfo;
	private NewGamePane pnChooseFirstPlayer;
	private boolean shouldShowHelpDialog;
	private NimMenuBar nimMenuBar;

	/**
	 * Creates a pane object to provide the view for the specified Game model
	 * object.
	 * 
	 * @param theGame the domain model object representing the Nim game
	 * 
	 * @requires theGame != null
	 * @ensures the pane is displayed properly
	 */
	public NimPane(Game theGame) {
		if (theGame == null) {
			throw new IllegalArgumentException("Invalid game");
		}
		this.theGame = theGame;

		this.shouldShowHelpDialog = NimHelpDialog.showHelpDialog(this.shouldShowHelpDialog);
		this.pnContent = new BorderPane();

		this.nimMenuBar = new NimMenuBar(this, this.shouldShowHelpDialog);
		this.setTop(this.nimMenuBar);

		this.pnChooseFirstPlayer = new NewGamePane(theGame, this);
		HBox topBox = this.createHBoxHolder(this.pnChooseFirstPlayer, false);
		this.pnContent.setTop(topBox);

		this.pnHumanPlayer = new HumanPane(theGame);
		HBox leftBox = this.createHBoxHolder(this.pnHumanPlayer, true);
		this.pnContent.setLeft(leftBox);

		this.pnComputerPlayer = new ComputerPane(theGame);
		HBox centerBox = this.createHBoxHolder(this.pnComputerPlayer, true);
		this.pnContent.setCenter(centerBox);

		this.pnGameInfo = new StatusPane(theGame);
		HBox bottomBox = this.createHBoxHolder(this.pnGameInfo, false);
		this.pnContent.setBottom(bottomBox);

		this.setCenter(this.pnContent);
	}

	private HBox createHBoxHolder(Pane newPane, boolean disable) {
		newPane.setDisable(disable);
		HBox leftBox = new HBox();
		leftBox.getStyleClass().add("pane-border");
		leftBox.getChildren().add(newPane);
		return leftBox;
	}

	/**
	 * This method gets the game object associated with this pane.
	 * 
	 * @return game
	 */
	public Game getGame() {
		return this.theGame;
	}

	/**
	 * This method gets the NewGamePane object associated with this pane.
	 * 
	 * @return game
	 */
	public NewGamePane getNewGamePane() {
		return this.pnChooseFirstPlayer;
	}

	/**
	 * This method keep track if the help dialog should be shown.
	 * 
	 * @return shouldShowHelpDialog
	 */
	public boolean isShouldShowHelpDialog() {
		return this.shouldShowHelpDialog;
	}

	/**
	 * This method sets whether the help dialog should be shown.
	 * 
	 * @param shouldShowHelpDialog true if the help dialog should be shown
	 */
	public void setShouldShowHelpDialog(boolean shouldShowHelpDialog) {
		this.shouldShowHelpDialog = shouldShowHelpDialog;
	}

	/**
	 * This method gets the NewGamePane object when called.
	 * 
	 * @return pnChooseFirstPlayer
	 */
	public NewGamePane getPnChooseFirstPlayer() {
		return this.pnChooseFirstPlayer;
	}

	/**
	 * This method gets the HumanPane object when called.
	 * 
	 * @return pnHumanPlayer
	 */
	public HumanPane getPnHumanPlayer() {
		return this.pnHumanPlayer;
	}

	/**
	 * This method gets the ComputerPane object when called.
	 * 
	 * @return pnComputerPlayer
	 */
	public ComputerPane getPnComputerPlayer() {
		return this.pnComputerPlayer;
	}

	/**
	 * This method gets the StatusPane object when called.
	 * 
	 * @return pnGameInfo
	 */
	public StatusPane getPnGameInfo() {
		return this.pnGameInfo;
	}
}
