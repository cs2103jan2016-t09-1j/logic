package main.logic.command;

import main.logic.Logic;

/**
 * Gives the GUI a string to notify it should exit.
 */
public class ExitCommand implements Command {

	String exit;
	public ExitCommand() {
		exit = "exit";
	}

	@Override
	public String execute() {
		Logic.saveData(Logic.getDirectory(), Logic.getFileName());
		return exit;
	}

}
