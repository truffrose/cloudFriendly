package main.java.com.fcastelain.cf.model.command;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.CommandException;
import main.java.com.fcastelain.cf.model.LevelException;
import main.java.com.fcastelain.cf.model.Terminal;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by fcastelain on 30/11/16.
 */
public class Ls implements Command {
    @Override
    public String getRegex() {
        return "^ls(\\ [a-zA-Z_\\./]+)?$";
    }

    @Override
    public String exec(Terminal tty, String str) {
        if (str.equals("ls")) {
            return this.currentDirectory(tty);
        }
        return this.remoteDirectory(tty, str);
    }

    /**
     * Use to list the content of the current directory.
     * @param tty the terminal of the users
     * @param str the path of the directory to show
     * @return the content in String
     */
    private String remoteDirectory(Terminal tty, String str) {
        str = str.split(" ")[1];
        if (str.toCharArray()[0] == '/') {
            return this.showContent(Paths.get(str));
        }
        return this.showContent(Paths.get(tty.getFullPath() + "/" + str));
    }

    /**
     * Use to list the content of the current directory.
     * @param tty the {@link Terminal} with the path of the current directory
     * @return the content in String
     */
    private String currentDirectory(Terminal tty) {
        return this.showContent(tty.getFullPath());
    }

    /**
     * Use to list the content of the directory on the path.
     * @param path the path of the directory
     * @return the content in the String
     */
    private String showContent(Path path) {
        try {
            String retVal = "";
            DirectoryStream<Path> stream = Files.newDirectoryStream(path);
            try {
                Iterator<Path> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    String file = iterator.next() + "";
                    if (Files.isDirectory(Paths.get(file))) {
                        retVal += "d - ";
                    }
                    else {
                        retVal += "f - ";
                    }
                    retVal += this.getLastElement(file, "/") + "\n";
                }
            } finally {
                stream.close();
                return retVal;
            }
        }
        catch (IOException e) {
            throw new CommandException("/!\\ Not able to load the content of the directory.", LevelException.FATAL);
        }
    }

    /**
     * Return the last element of a String cut by the regex.
     * @param input to slip and return the last element
     * @param regex the sit regex
     * @return the last element after the split
     */
    private String getLastElement(String input, String regex) {
        String strs[] = input.split(regex);
        return strs[strs.length - 1];
    }
}
