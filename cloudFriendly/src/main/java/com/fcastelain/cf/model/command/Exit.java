package main.java.com.fcastelain.cf.model.command;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.Terminal;

/**
 * Created by fcastelain on 28/11/16.
 */
public class Exit implements Command {
    @Override
    public String getRegex() {
        return "exit";
    }

    @Override
    public String exec(Terminal tty, String str) { return null; }
}
