package main.java.com.fcastelain.cf.model.command;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.CommandException;
import main.java.com.fcastelain.cf.model.LevelException;
import main.java.com.fcastelain.cf.model.Terminal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by fcastelain on 01/12/16.
 */
public class Mkdir implements Command {
    @Override
    public String getRegex() {
        return "^mkdir\\ [a-zA-Z_/]+$";
    } // mkdir [a-zA-Z\\_\/]+$

    @Override
    public String exec(Terminal tty, String str) {
        str = tty.getFullPath() + "/" + str.split(" ")[1];

        try {
            Files.createDirectory(Paths.get(str));
        } catch (IOException e) {
            throw new CommandException("Error with the command", LevelException.WARNING);
        }
        return "";
    }
}
