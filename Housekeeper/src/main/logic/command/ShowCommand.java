package main.logic.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.logic.Logic;
import main.logic.Task;
import main.parser.GUIdisplayParser;

/**
 * Command to show tasks according to user's specifications
 * 
 */

public class ShowCommand implements Command {

	String description;
	ArrayList<Task> showTasks = new ArrayList<Task>();

	public ShowCommand(String description) {
		this.description = description;
	}

	@Override
	public String execute() {
		Logger logger = Logger.getLogger("ShowCommand");

		showTasks.clear();

		if (description.equals("floating")) {
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getDeadline() == null && Logic.tasks.get(i).getStartDate() == null
						&& Logic.tasks.get(i).getEndDate() == null) {
					logger.log(Level.INFO, "going to add floating task");
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("deadline")) {
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getDeadline() != null) {
					logger.log(Level.INFO, "going to add deadline task");
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("duration")) {
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getStartDate() != null && Logic.tasks.get(i).getEndDate() != null) {
					logger.log(Level.INFO, "going to add duration task");
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("completed")) {
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getCheck() == true) {
					logger.log(Level.INFO, "going to add completed task");
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("uncompleted")) {
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getCheck() == false) {
					logger.log(Level.INFO, "going to add uncompleted task");
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("today")) {
			Date today = new Date();
			for (int i = 0; i < Logic.tasks.size(); i++) {

				if (GUIdisplayParser.getDate(Logic.tasks.get(i)) != null
						&& GUIdisplayParser.formateDate(GUIdisplayParser.getDate(Logic.tasks.get(i)))
								.equals(GUIdisplayParser.formateDate(today))) {
					showTasks.add(Logic.tasks.get(i));
				}
			}
		} else if (description.equals("tomorrow")) {
			Date today = new Date();
			Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (GUIdisplayParser.getDate(Logic.tasks.get(i)) != null
						&& GUIdisplayParser.formateDate(GUIdisplayParser.getDate(Logic.tasks.get(i)))
								.equals(GUIdisplayParser.formateDate(tomorrow))) {
					showTasks.add(Logic.tasks.get(i));
				}
			}
		}

		logger.log(Level.INFO, "end of show list");

		if (showTasks.isEmpty()) {
			Logic.setShowTasks(null);
			return "No tasks to show";
		} else {
			Logic.setShowTasks(showTasks);
			assert showTasks.size() != 0 : "Show List is empty";
			return "Tasks shown";
		}
	}

}
