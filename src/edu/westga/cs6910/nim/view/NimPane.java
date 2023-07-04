package edu.westga.cs6910.nim.view;

import edu.westga.cs6910.nim.model.Game;
import javafx.scene.layout.BorderPane;

/**
 * Defines a GUI for the 1-pile Nim game. This class was started by CS6910
 * 
 * @author Deeksha Namani
 * @version 7/3/2023
 */
public class NimPane extends BorderPane {
	private Game theGame;
	private FullNimPane fullNimPane;
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

		this.fullNimPane = new FullNimPane(theGame, this);

		this.nimMenuBar = new NimMenuBar(this.fullNimPane, this.shouldShowHelpDialog);
		this.setTop(this.nimMenuBar);

		this.setCenter(this.fullNimPane);
	}

	/**
	 * This method gets the game object when called.
	 * 
	 * @return the game object
	 */
	public Game getGame() {
		return this.theGame;
	}

	/**
	 * This method keeps track if the help dialog should be shown.
	 * 
	 * @return if true the help dialog is shown
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
}
