package main.java.com.fcastelain.cf.cli;

import main.java.com.fcastelain.cf.model.command.*;

public class Main {

	public static void main(String[] args) {
		ControllerCLIImpl instance = new ControllerCLIImpl();
		instance.addCommand(new Exit());
		instance.addCommand(new Cd());
		instance.addCommand(new Ls());
		instance.addCommand(new Pwd());
		instance.addCommand(new Mkdir());
		instance.start();
	}

}
