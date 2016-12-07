package main.java.com.fcastelain.cf.model;

/**
 * Created by fcastelain on 30/11/16.
 */
public class CommandException extends RuntimeException {

    private LevelException lvlException;

    public CommandException(String s, LevelException lvl) {
        super(s);
        this.lvlException = lvl;
    }

}
