package main.logic.command;

import main.logic.Logic;


/**
 * Command to mark a task as done.
 */

public class CheckCommand implements Command{
	
	private boolean check;
	private int index;
	
	public CheckCommand(boolean check, int index){
		this.check = check;
		this.index = index;
	}
	
	@Override
	public String execute(){
		if(check==true){
			if(Logic.tasks.get(index).getCheck()==true) {
				return "This task has already been marked";
			} else {
				Logic.tasks.get(index).setCheck(true);
				return "This task has been marked";
			}			
		} else {
			if(Logic.tasks.get(index).getCheck()==false){
				return "This task has not yet been marked";
			} else {
				Logic.tasks.get(index).setCheck(false);
				return "This task has been unmarked";
			}
		}
	}
}
