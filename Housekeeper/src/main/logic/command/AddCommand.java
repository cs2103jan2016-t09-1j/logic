package main.logic.command;

import main.logic.Logic;
import main.logic.Task;
import java.util.Date;

/**
 * Command to add a task.
 */
public class AddCommand implements Command{

	public enum TaskType {
		FLOATING, DURATION, DEADLINE
	}

	private String description;
	private Date deadline;
	private Date startDate;
	private Date endDate;
	private TaskType taskType;

	/**
	 * Floating task constructor.
	 * @param  description description of the task
	 * @return             task with the description
	 */
	public AddCommand(String description) {
		this.taskType = TaskType.FLOATING;
		this.description = description;
	}

	/**
	 * Deadline task constructor.
	 * @param  description description of the task
	 * @param  deadline    deadline of the task
	 * @return             task with the description, deadline
	 */
	public AddCommand(String description, Date deadline) {
		this.taskType = TaskType.DEADLINE;
		this.description = description;
		this.deadline = deadline;
	}

	/**
	 * Duration task constructor
	 * @param  description description of the task
	 * @param  startDate   start time of the task
	 * @param  endDate     end time of the task
	 * @return             task with the start time, end time, description
	 */
	public AddCommand(String description, Date startDate, Date endDate) {
		this.taskType = TaskType.DURATION;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String execute() {
		String message = "Add task Successfully<br>";
		switch(this.taskType) {
			case DEADLINE:
				Logic.tasks.add(new Task(description, deadline));
				message += description + "<br>[deadline] " + deadline;
				break;
			case DURATION:
				Logic.tasks.add(new Task(description, startDate, endDate));
				message += description + "<br>[start at] " + startDate + "<br>[end at] " + endDate;
				break;
			case FLOATING:
				Logic.tasks.add(new Task(description));
				message += description;
				break;
			default:
				break;
		}
		return message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
