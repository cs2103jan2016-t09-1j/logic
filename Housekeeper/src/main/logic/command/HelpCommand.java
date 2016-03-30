package main.logic.command;

/**
 * Show help Command
 *
 */
public class HelpCommand implements Command {

	@Override
	public String execute() {
		return "Help";
	}
}