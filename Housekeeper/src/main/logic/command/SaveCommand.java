package main.logic.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.logic.Logic;

/**
 * Saves the tasks to a specified directory.
 *
 */
public class SaveCommand implements Command {
	private String directory = "";

	public SaveCommand(String directory) {
		this.directory = directory;
	}

	@Override
	public String execute() {
		Path path = Paths.get(directory);
		if (Files.exists(path)) {
			Logic.setDirectory(directory);
			Logic.setFileName("tasks.ser");
			Logic.saveData(directory, Logic.getFileName());
			return "File saved to: " + directory;
		} else {
			return "Saved failed! No such file path.";
		}
	}
}
