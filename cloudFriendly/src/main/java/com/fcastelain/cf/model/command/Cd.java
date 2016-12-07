package main.java.com.fcastelain.cf.model.command;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.Terminal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cd implements Command {
	@Override
	public String getRegex() {
		return "^cd\\ [a-zA-Z\\./]+$";
	}

	@Override
	public String exec(Terminal tty, String str) {
		Path pTemp = Paths.get(tty.getFullPath().toString() + "/" + str.substring(3, str.length()));
		pTemp = pTemp.normalize();
		if (Files.exists(pTemp)) {
			tty.addToPath(str.split(" ")[1]);
			return "";
		}
		else {
			return "/!\\ cd: No directory find \n";
		}
	}
}
