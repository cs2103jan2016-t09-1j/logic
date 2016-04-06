package main.logic.command;

//import main.logic.Logic;
//import main.logic.Task;
//import java.util.Date;

public class RecurringCommand implements Command {
	int index;
	
	public RecurringCommand() {
		execute();
	}
	
	public String execute() {
		String message = "Task is set to recur Successfully<br>";
		
		
		
		return message;
		
	}
}
