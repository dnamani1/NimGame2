package edu.westga.cs6910.nim.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This class displays the help dialog for Nim Game.
 * 
 * @author Deeksha Namani
 * @version 7/3/2023
 *
 */
public class NimHelpDialog {

	/**
	 * This method display the help dialog when ever called.
	 * 
	 * @param shouldShowHelp should
	 * @return help dialog if true
	 */
	public static boolean showHelpDialog(boolean shouldShowHelp) {
		Alert message = new Alert(AlertType.CONFIRMATION);
		message.setTitle("CS6910: Better Nim");

		String helpMessage = "Nim rules: \nPlay against the computer.\n"
				+ "Alternate taking turns, removing 1 to 3 sticks per turn.\n"
				+ "The player who takes the last stick loses.\n"
				+ "You may set the number of sticks on the pile at the start "
				+ "of each game,\n  and switch what strategy the computer uses " + "at any time.";
		message.setHeaderText(helpMessage);
		message.setContentText("Would you like to see this dialog at the start of the next game?");

		ButtonType btnYes = new ButtonType("Yes");
		ButtonType btnNo = new ButtonType("No");
		message.getButtonTypes().setAll(btnYes, btnNo);

		Optional<ButtonType> result = message.showAndWait();

		return result.get() == btnYes;
	}
}
