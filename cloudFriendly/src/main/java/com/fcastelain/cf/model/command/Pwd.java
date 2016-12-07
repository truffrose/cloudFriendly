package main.java.com.fcastelain.cf.model.command;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.Terminal;

/**
 * Created by fcastelain on 30/11/16.
 */
public class Pwd implements Command {
    @Override
    public String getRegex() {
        return "pwd";
    }

    @Override
    public String exec(Terminal tty, String str) {
        return tty.getPath().toString() + "\n";
    }
}