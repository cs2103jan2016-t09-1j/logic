package main.logic.command;

import main.logic.Logic;
import main.logic.Task;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Command to sort task according to their alphabetical order
 * 
 */
public class SortCommand implements Command {

	private String sortKey;
	ArrayList<Task> sortedTask = new ArrayList<Task>();

	public SortCommand(String sortKey) {
		this.sortKey = sortKey;
		this.execute();
	}

	@Override
	// Sort by description alphabetically
	public String execute() {
		Logger logger = Logger.getLogger("SortCommand");
		if (sortKey.equals("description")) {
			logger.log(Level.INFO, "Sorting task by description in alphabetical order");
			sortedTask.clear();
			for (int i = 0; i < Logic.tasks.size(); i++) {
				sortedTask.add(Logic.tasks.get(i));
			}
			if (!sortedTask.isEmpty()) {
				Collections.sort(sortedTask);
				Logic.setSortedTask(sortedTask);
				logger.log(Level.INFO, "list has been sorted.");
				return "Tasks sorted";
			} else {
				Logic.setSortedTask(null);
				return "There is no task available for sorting";
			}
		}
		// Sort task according to their deadline
		else if (sortKey.equals("deadline")) {
			logger.log(Level.INFO, "going to add task that have deadline");
			sortedTask.clear();
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getDeadline() != null) {
					sortedTask.add(Logic.tasks.get(i));
				}
			}
			if (!sortedTask.isEmpty()) {
				Collections.sort(sortedTask, Task.getDeadLineComparable());
				Logic.setSortedTask(sortedTask);
				logger.log(Level.INFO, "end of show list");
				return "Tasks sorted";
			} else {
				Logic.setSortedTask(null);
				return "There is no tasks to sort";
			}

		} else {
			return "Please specify which sorting type.";
		}

	}
}
