package main.logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Comparator;

import main.exception.NoDescriptionException;

/**
 * Task represents an item that the user needs to complete.
 * 
 * @@author A0124719A
 * 
 */
public class Task implements Serializable, Comparable<Task> {

	private static final long serialVersionUID = 1L;
	private String description = null;
	private Date deadline = null;
	private Date startDate = null;
	private Date endDate = null;
	private boolean check = false;

	/**
	 * 	
	 * 
	 * @param description
	 *            floating description
	 * @return task containing the description
	 * @throws NoDescriptionException
	 *             exception
	 */
	public Task(String description) throws NoDescriptionException {
		if (description.trim().equals("")) {
			throw new NoDescriptionException();
		}
		this.description = description;
	}

	/**
	 * Constructor for task containing a deadline.
	 * 
	 * @param description
	 *            task's description
	 * @param deadline
	 *            task's deadline
	 * @return task
	 */
	public Task(String description, Date deadline) {
		this.description = description;
		this.deadline = deadline;
	}

	/**
	 * Constructor for duration task.
	 * 
	 * @param description
	 *            task's description
	 * @param startDate
	 *            task's start date
	 * @param endDate
	 *            task's end date
	 * @return task
	 */
	public Task(String description, Date startDate, Date endDate) {
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Copy constructor.
	 * 
	 * @param task
	 *            task to be cloned
	 * @return cloned task
	 */
	public Task(Task task) {
		description = task.getDescription();
		startDate = task.getStartDate();
		endDate = task.getEndDate();
		deadline = task.getDeadline();
		check = task.getCheck();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 *
	 */
	public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * return date in certain format
	 * 
	 * @param date
	 * @return formated date in certain way
	 */
	public String formateDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MMM/yyyy K:mma");
		return df.format(date);
	}

	/**
	 * return deadline in certain format String type
	 * 
	 * @return formated date
	 */
	public String getStrDeadline() {
		if (deadline != null) {
			return formateDate(deadline);
		}
		return "";
	}

	/**
	 * return StartDate in certain format String type
	 * 
	 * @return formated date
	 */
	public String getStrStartDate() {
		if (startDate != null) {
			return formateDate(startDate);
		}
		return "";
	}

	/**
	 * return EndDate in certain format String type
	 * 
	 * @return formated date
	 */
	public String getStrEndDate() {
		if (endDate != null) {
			return formateDate(endDate);
		}
		return "";
	}

	/**
	 * Serializes the task object.
	 *
	 * @param stream
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeObject(description);
		stream.writeObject(deadline);
		stream.writeObject(startDate);
		stream.writeObject(endDate);
		stream.writeObject(check ? Boolean.TRUE : Boolean.FALSE);
	}

	/**
	 * Deserializes the task object.
	 *
	 * @param stream
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		description = (String) stream.readObject();
		deadline = (Date) stream.readObject();
		startDate = (Date) stream.readObject();
		endDate = (Date) stream.readObject();
		check = ((Boolean) stream.readObject()) ? true : false;
	}

	public String toString() {
		return "Task: " + getDescription();
	}

	/**
	 * Compares two tasks.
	 * 
	 * @param task
	 * @return
	 */
	public boolean equals(Object o) {
		if (o instanceof Task) {
			Task task = (Task) o;
			boolean descriptionBool = description.equals(task.getDescription());
			boolean deadlineBool;
			boolean startDateBool;
			boolean endDateBool;
			boolean checkBool;
			if (deadline == null) {
				deadlineBool = deadline == task.getDeadline();
			} else {
				deadlineBool = deadline.equals(task.getDeadline());
			}

			if (startDate == null) {
				startDateBool = startDate == task.getStartDate();
			} else {
				startDateBool = startDate.equals(task.getStartDate());
			}

			if (endDate == null) {
				endDateBool = endDate == task.getEndDate();
			} else {
				endDateBool = endDate.equals(task.getEndDate());
			}

			checkBool = check == task.getCheck();

			return descriptionBool && deadlineBool && startDateBool && endDateBool && checkBool;
		} else {
			return false;
		}
	}

	/**
	 * Compares two tasks alphabetically base on description
	 * 
	 * @param task
	 * @return int
	 */
	@Override
	public int compareTo(Task t) {
		return this.description.compareTo(t.getDescription());
	}

	/**
	 * Compares the two task base on deadline
	 * 
	 * @param
	 * @return Comparator
	 */
	public static Comparator<Task> getDeadLineComparable() {
		return new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return t1.getDeadline().compareTo(t2.getDeadline());
			}
		};
	}

}