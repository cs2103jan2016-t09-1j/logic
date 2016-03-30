package main.logic.command;

import main.logic.Logic;

/**
 * Command to undo the command prior to this one.
 * 
 */
public class UndoCommand implements Command {

	@Override
	public String execute() {

		if (Logic.history.size() == 0) {
			assert Logic.history.size() != 0 : "Cannot undo!";
			return "Nothing to undo!";
		} else {
			Logic.tasks = Logic.history.pop();
			return "Undid your command!";
		}

	}

}
