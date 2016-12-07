package main.java.com.fcastelain.cf.cli;

import main.java.com.fcastelain.cf.model.Command;

import java.nio.file.Path;

/**
 * Created by fcastelain on 25/11/16.
 */
public interface ControllerCLI {

    /**
     * Launch a new cli.
     */
    void start();

    /**
     * Add the command to the list of know command.
     * @param cmd to add
     */
    void addCommand(Command cmd);

    /**
     * Return the welcome message.
     * @return
     */
    default String getStartShow() {
        String str = "";
        str += "Welcom to Cloud Friendly\n\n\n";
        return str;
    }

    /**
     * Return the command waiting command line.
     * @param userName name to the connect
     * @param path
     * @return
     */
    default String waitCommand(String userName, Path path) {
        return userName + "@" + path + " > ";
    }

}
