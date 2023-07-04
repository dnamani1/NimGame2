package edu.westga.cs6910.nim.view;

import edu.westga.cs6910.nim.model.Game;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * This class have all 4 panes code.
 * 
 * @author Deeksha Namani
 * @version 7/3/2023
 */
public class FullNimPane extends BorderPane {
	private Game theGame;
	private BorderPane pnContent;
	private HumanPane pnHumanPlayer;
	private ComputerPane pnComputerPlayer;
	private StatusPane pnGameInfo;
	private NewGamePane pnChooseFirstPlayer;

	/**
	 * Creates a FullNimPane object with the specified game and NimPane.
	 * 
	 * @param theGame the game model object
	 * @param nimPane the NimPane object
	 */
	public FullNimPane(Game theGame, NimPane nimPane) {
		if (theGame == null) {
			throw new IllegalArgumentException("Invalid game");
		}
		this.theGame = theGame;
		this.pnContent = new BorderPane();
		this.pnChooseFirstPlayer = new NewGamePane(this.theGame, this);
		this.pnHumanPlayer = new HumanPane(theGame);
		this.pnComputerPlayer = new ComputerPane(theGame);
		this.pnGameInfo = new StatusPane(theGame);

		this.buildPane();
	}

	private void buildPane() {

		HBox topBox = this.createHBoxHolder(this.pnChooseFirstPlayer, false);
		this.pnContent.setTop(topBox);

		HBox leftBox = this.createHBoxHolder(this.pnHumanPlayer, true);
		this.pnContent.setLeft(leftBox);

		HBox centerBox = this.createHBoxHolder(this.pnComputerPlayer, true);
		this.pnContent.setCenter(centerBox);

		HBox bottomBox = this.createHBoxHolder(this.pnGameInfo, false);
		this.pnContent.setBottom(bottomBox);

		this.setCenter(this.pnContent);
	}

	private HBox createHBoxHolder(Pane newPane, boolean disable) {
		newPane.setDisable(disable);
		HBox hBox = new HBox();
		hBox.getStyleClass().add("pane-border");
		hBox.getChildren().add(newPane);
		hBox.setSpacing(20);
		return hBox;
	}

	/**
	 * This method reset the radio buttons for new selection in the NewGamePane.
	 */
	public void resetNewGamePane() {
		this.pnChooseFirstPlayer.reset();
	}

	/**
	 * This method gets the NewGamePane object when called.
	 * 
	 * @return the NewGamePane object
	 */
	public NewGamePane getPnChooseFirstPlayer() {
		return this.pnChooseFirstPlayer;
	}

	/**
	 * This method gets the HumanPane object when called.
	 * 
	 * @return the HumanPane object
	 */
	public HumanPane getPnHumanPlayer() {
		return this.pnHumanPlayer;
	}

	/**
	 * This method gets the ComputerPane object when called.
	 * 
	 * @return the ComputerPane object
	 */
	public ComputerPane getPnComputerPlayer() {
		return this.pnComputerPlayer;
	}

	/**
	 * This method gets the StatusPane object when called.
	 * 
	 * @return the StatusPane object
	 */
	public StatusPane getPnGameInfo() {
		return this.pnGameInfo;
	}
	
	/**
	 * This method gets the game object when called.
	 * 
	 * @return the game object
	 */
	public Game getGame() {
		return this.theGame;
	}
}
