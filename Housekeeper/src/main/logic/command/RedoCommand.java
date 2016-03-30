package main.logic.command;

import main.logic.Logic;

/**
 * Command to redo the undo command prior to this one.
 */
public class RedoCommand implements Command {

	@Override
	public String execute() {		
		
		if(Logic.redo.size()==0){
			return "Nothing to redo!";
		} else {
			Logic.tasks = Logic.redo.pop();
			return "Redid your undo!";
		}
	}
}