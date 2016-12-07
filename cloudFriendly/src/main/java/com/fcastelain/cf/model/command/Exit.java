package com.fcastelain.cf.model.command;

import com.fcastelain.cf.model.Command;
import com.fcastelain.cf.model.Terminal;

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
