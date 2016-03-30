package main.logic.command;

import main.logic.Logic;

/**
 * for user to clear all the tasks
 * for test purpose
 *
 */
public class ClearCommand implements Command{

	@Override
	public String execute() {
		Logic.tasks.clear();
		return "All Tasks cleared.";
	}
}
