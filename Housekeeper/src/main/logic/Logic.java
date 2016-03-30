package main.logic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.logic.command.Command;
import main.logic.command.HelpCommand;
import main.logic.command.InvalidCommand;
import main.logic.command.OpenCommand;
import main.logic.command.SaveCommand;
import main.logic.command.UndoCommand;
import main.parser.CommandParser;
import main.storage.Storage;

/**
 * Handles the manipulation of the list of tasks as well as their temporary
 * storage.
 * 
 * @@author A0124719A
 * 
 */
public class Logic {
	private static final Logger logger = Logger.getLogger(Logic.class.getName());
	private static String directory;
	private static String fileName;
	public static ArrayList<Task> sortedTasks;
	public static ArrayList<Task> foundTasks;
	public static ArrayList<Task> showTasks;
	public static Stack<ArrayList<Task>> history;
	public static Stack<ArrayList<Task>> redo;
	public static ArrayList<Task> tasks;
	private static Logic logic;
	private int current_status = 1;

	/**
	 * For testing purposes, may pass in a different file name.
	 * 
	 * @param fileName
	 */
	private Logic(String directory, String fileName) {
		assert fileName != null : "The file name is null";
		assert false;
		Logic.setDirectory(directory);
		Logic.setFileName(fileName);
		File f = new File(directory, fileName);
		if (f.exists() && !f.isDirectory()) {
			tasks = Storage.deserialize(f);
		} else {
			tasks = new ArrayList<Task>();
		}
		history = new Stack<ArrayList<Task>>();
		redo = new Stack<ArrayList<Task>>();
	}

	/**
	 * The default constructor.
	 * 
	 */
	private Logic() {
		this("", "tasks.ser");
	}

	/**
	 * based the userInput to tell which type it is and handle it
	 * 
	 * @param userInput
	 * @return a command project
	 */

	public String executeCommand(String userInput) {
		Command command = CommandParser.parse(userInput);
		assert command != null : "Command is null!";
		current_status = CommandParser.current_status;
		if (!(command instanceof UndoCommand) && !(command instanceof SaveCommand) && !(command instanceof OpenCommand)
				&& !(command instanceof InvalidCommand) && !(command instanceof HelpCommand)) {
			Logic.history.push(clone(Logic.tasks));
		} else if (command instanceof UndoCommand) {
			current_status = 1;
			Logic.redo.push(clone(Logic.tasks));
		}

		logger.log(Level.FINE, "processing command {0}", command);
		String feedback = command.execute();
		return feedback;
	}

	/**
	 *
	 *
	 * @getter get the main tasks list
	 */
	public ArrayList<Task> getTasks() {
		return Logic.tasks;
	}

	/**
	 *
	 *
	 * @setter set the main tasks list
	 */
	public void setTasks(ArrayList<Task> newTasks) {
		Logic.tasks = newTasks;
	}

	/**
	 *
	 *
	 * @setter return current status
	 */
	public int getCurrentStatus() {
		return current_status;
	}

	/**
	 *
	 *
	 * @getter and setters for search and show functions
	 */
	public ArrayList<Task> getfoundTasks() {
		return foundTasks;
	}

	public static void setfoundTasks(ArrayList<Task> foundTasks) {
		Logic.foundTasks = foundTasks;
	}

	public ArrayList<Task> getShowTasks() {
		return Logic.showTasks;
	}

	public static ArrayList<Task> setShowTasks(ArrayList<Task> showTasks) {
		return Logic.showTasks = showTasks;
	}

	/**
	 *
	 *
	 * @getter and setters for sort commands
	 */

	public ArrayList<Task> getSortedTasks() {
		return sortedTasks;
	}

	public static void setSortedTask(ArrayList<Task> sortedTask) {
		Logic.sortedTasks = sortedTask;
	}

	/**
	 * Deep clones a Task object.
	 *
	 * @param original
	 *            the task object to be cloned
	 * @return the cloned task object
	 */
	private static ArrayList<Task> clone(ArrayList<Task> original) {
		ArrayList<Task> clone = new ArrayList<Task>();
		for (Task task : original) {
			clone.add(new Task(task));
		}
		return clone;
	}

	/**
	 * Saves data to the file name.
	 *
	 * @param fileName
	 */
	public static void saveData(String directory, String fileName) {

		File f;
		if (directory.equals("")) {
			f = new File(fileName);
			Storage.serialize(tasks, f);

		} else {
			Path path = Paths.get(directory);
			if (Files.exists(path)) {
				f = new File(directory, fileName);
				Storage.serialize(tasks, f);
			}
		}
	}

	/**
	 * Opens data at the file name.
	 *
	 * @param fileName
	 */
	public static void openData(String directory, String fileName) {
		File f;
		if (directory.equals("")) {
			f = new File(fileName);
		} else {
			f = new File(directory, fileName);
		}
		tasks = Storage.deserialize(f);
		Logic.setDirectory(directory);
		Logic.setFileName(fileName);
		history = new Stack<ArrayList<Task>>();
		redo = new Stack<ArrayList<Task>>();
	}

	/**
	 * apply singleton pattern
	 *
	 * @return the logic instance
	 */
	public static Logic getInstance() {
		if (logic == null) {
			logic = new Logic();
		}
		return logic;
	}

	/**
	 * For testing purposes, so that we don't overwrite our actual serialized
	 * file.
	 * 
	 * @return a test copy of logic
	 */
	public static Logic getTestInstance() {
		return new Logic("src/main/storage/", "test.ser");
	}

	/**
	 * For testing purposes, to hard reset all files.
	 * 
	 */
	public static void reset() {
		Logic.tasks = new ArrayList<Task>();
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		Logic.fileName = fileName;
	}

	public static String getDirectory() {
		return directory;
	}

	public static void setDirectory(String directory) {
		Logic.directory = directory;
	}
}