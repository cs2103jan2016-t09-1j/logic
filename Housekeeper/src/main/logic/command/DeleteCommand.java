package main.logic.command;
import main.logic.Logic;

/**
 * Deletes a task given its index.
 */
public class DeleteCommand implements Command {
	private int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	@Override
	public String execute() {
		String message = "Deleting..." + Logic.tasks.get(index).toString();
		Logic.tasks.remove(index);
		return message;
	}

}
