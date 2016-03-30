package main.logic.command;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.logic.Logic;

/**
 * Opens the tasks at a given location.
 * 
 *
 */
public class OpenCommand implements Command {
	private String directory = "";

	public OpenCommand(String directory) {
		this.directory = directory;
	}

	@Override
	public String execute() {

		if (directory.equals("")) {
			File f = new File(Logic.getFileName());
			if (f.exists()) {
				Logic.openData(directory, Logic.getFileName());
				return "File opened at: " + directory;
			}
		}

		Path path = Paths.get(directory);
		if (Files.exists(path)) {
			Logic.setDirectory(directory);
			File f = new File(directory, Logic.getFileName());
			if (f.exists() && !f.isDirectory()) {
				Logic.openData(directory, Logic.getFileName());
				return "File opened at: " + directory;
			}
			return "Open failed! No such file at file path.";
		} else {
			return "Open failed! No such file path.";
		}
	}

}
