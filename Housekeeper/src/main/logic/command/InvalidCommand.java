package main.logic.command;

/**
 * Command to represent that the user has entered an invalid input.
 * 
 *
 */
public class InvalidCommand implements Command {

	private String errorString;

	public InvalidCommand(String errorString) {
		this.errorString = errorString;
	}

	@Override
	public String execute() {
		
//		InvalidCommandException ice = new InvalidCommandException();
		String exceptionMessage = this.errorString;
		return exceptionMessage;
		
	}

}
