package main.logic.command;

import main.logic.Logic;

import main.logic.Task;
import main.parser.DateParser;

import java.util.*;

/**
 * Command to search a task.
 * 
 */
public class SearchCommand implements Command {

	private ArrayList<String> description;
	private ArrayList<Date> specifyDate;
	private ArrayList<Task> foundTasks = new ArrayList<Task>();

	public SearchCommand(ArrayList<String> description) {
		this.description = description;
	}

	@Override
	public String execute() {
		foundTasks.clear();
		String listString = "";
		Calendar searchDate = Calendar.getInstance();
		Calendar taskDate = Calendar.getInstance();

		if (description.get(0).equals("date")) {
			for (int i = 1; i < description.size(); i++) {
				listString += description.get(i) + " ";
			}

			listString = listString.trim();

			for (int i = 0; i < Logic.tasks.size(); i++) {
				specifyDate = DateParser.getDateList(listString);
				searchDate.setTime(specifyDate.get(0));

				if (!(Logic.tasks.get(i).getDeadline() == null)) {
					taskDate.setTime(Logic.tasks.get(i).getDeadline());

					if (searchDate.get(Calendar.DAY_OF_MONTH) == taskDate.get(Calendar.DAY_OF_MONTH)) {
						if (searchDate.get(Calendar.MONTH) == taskDate.get(Calendar.MONTH)) {
							foundTasks.add(Logic.tasks.get(i));
						}
					}
				} else if (!(Logic.tasks.get(i).getStartDate() == null)) {
					taskDate.setTime(Logic.tasks.get(i).getStartDate());
					if (searchDate.get(Calendar.DAY_OF_MONTH) == taskDate.get(Calendar.DAY_OF_MONTH)) {
						if (searchDate.get(Calendar.MONTH) == taskDate.get(Calendar.MONTH)) {
							foundTasks.add(Logic.tasks.get(i));
						}
					}
					if (!(Logic.tasks.get(i).getStartDate().equals(Logic.tasks.get(i).getEndDate()))) {
						taskDate.setTime(Logic.tasks.get(i).getEndDate());
						if (searchDate.get(Calendar.DAY_OF_MONTH) == taskDate.get(Calendar.DAY_OF_MONTH)) {
							if (searchDate.get(Calendar.MONTH) == taskDate.get(Calendar.MONTH)) {
								foundTasks.add(Logic.tasks.get(i));
							}
						}
					}
				}
			}

		} else {
			for (String s : description) {
				if (description.size() == 1) {
					listString = s;
				} else {
					listString += s + " ";
				}
			}

			listString = listString.trim();
			for (int i = 0; i < Logic.tasks.size(); i++) {
				if (Logic.tasks.get(i).getDescription().matches("(.*)" + listString + "(.*)")) {
					foundTasks.add(Logic.tasks.get(i));
				}
			}
		}

		if (foundTasks.isEmpty()) {
			Logic.setfoundTasks(null);
			return "No such task";
		} else {
			Logic.setfoundTasks(foundTasks);
			assert foundTasks.size() != 0 : "Search List is empty";
			return "Task found";
		}
	}
}
