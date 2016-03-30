package main.logic.command;

/**
 * for display all command
 * for test purpose
 *
 */
public class DisplayAllCommand implements Command{

	@Override
	public String execute() {
		return "Here are your tasks";
	}
}