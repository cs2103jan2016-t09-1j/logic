package main.logic.command;

import java.util.Date;

import main.logic.Logic;
import main.logic.Task;


/**
 * Edits a task given its fields.
 */
public class EditCommand implements Command {

	private int index;
	private String newDescription;
	private Date newDeadline;
	private Date newStartDate;
	private Date newEndDate;
	private boolean clearDeadline;
	private boolean clearDuration;

	public EditCommand(int index, String newDescription, Date newDeadline, Date newStartDate, Date newEndDate, boolean clearDeadline, boolean clearDuration) {
		this.index = index;
		this.newDescription = newDescription;
		this.newDeadline = newDeadline;
		this.newStartDate = newStartDate;
		this.newEndDate = newEndDate;
		this.clearDeadline = clearDeadline;
		this.clearDuration = clearDuration;
	}

	@Override
	public String execute() {
		Task task = Logic.tasks.get(index);
		String message = "";
		if (newDescription != null) {
			message += "Edited task description from: '" + task.getDescription() + "\' to \'" + newDescription + "'\n";
			task.setDescription(newDescription);
		}
		if (newDeadline != null) {
			message += "Edited task deadline from: '" + task.getDeadline() + "\' to \'" + newDeadline + "'";
			task.setDeadline(newDeadline);
			clearDuration = true; //tasks have either a deadline or duration
		}
		if (newStartDate != null) {
			message += "Edited task start date from: '" + task.getStartDate() + "\' to \'" + newStartDate + "'";
			task.setStartDate(newStartDate);
			clearDeadline = true;
		}
		if (newEndDate != null) {
			message += "Edited task end date from: '" + task.getEndDate() + "\' to \'" + newEndDate + "'";
			task.setEndDate(newEndDate);
			clearDeadline = true;
		}
		if (clearDeadline) {
			task.setDeadline(null);
		}
		if (clearDuration) {
			task.setStartDate(null);
			task.setEndDate(null);
		}
		return message;
	}

}
