package logic;

import commands.ICommand;
import userInterface.GameBoardController;

public class CommandInvoker {
	
	public static GameHoster gameHoster;
	public static GameBoardController uiController;
	
	public static void runCommand(ICommand command) {
		command.execute();
	}
}
