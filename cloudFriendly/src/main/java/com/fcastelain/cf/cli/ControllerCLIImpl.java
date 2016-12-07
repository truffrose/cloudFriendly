package main.java.com.fcastelain.cf.cli;

import main.java.com.fcastelain.cf.model.Command;
import main.java.com.fcastelain.cf.model.Terminal;
import main.java.com.fcastelain.cf.model.User;
import main.java.com.fcastelain.cf.service.UserService;

// import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by fcastelain on 25/11/16.
 */
public class ControllerCLIImpl implements ControllerCLI {

    private ArrayList<Command> cmds;
    private UserService userService = UserService.INSTANCE;
    private Scanner keyboard = new Scanner(System.in);
    // Use to hide the password input but work only with console not with the IDE
//    private Console console = System.console();

    @Override
    public void start() {
        Terminal t = new Terminal();
        System.out.println(this.getStartShow());
        connect(t);
        t.setPath("/");
        while (t.getCurrentUser() != null) {
            run(t);
        }
    }

    /**
     * Use to wait the next input in the CLI and to check it.
     * @param tty the {@link Terminal} where we check the input.
     */
    private void run(Terminal tty) {
        System.out.print(this.waitCommand(tty.getCurrentUser().getName(), tty.getPath()));
        String input = this.keyboard.nextLine().trim();
        for (Command cmd : this.cmds) {
            if (cmd.check(input)) {
                String retVal = cmd.exec(tty, input);
                if (retVal == null) {
                    tty.setCurrentUser(null);
                }
                else {
                    System.out.print(retVal);
                }
                return;
            }
        }
        System.out.println("/!\\ Wrong input : input");
    }

    /**
     * Ask the name and password of the user and check the validity.
     * @param tty the {@link Terminal} where the user try to connect
     */
    private void connect(Terminal tty) {
        System.out.print("name : ");
        String name = this.keyboard.nextLine();
//        String password = String.valueOf(this.console.readPassword("password : "));
        System.out.print("password : ");
        String password = this.keyboard.nextLine();
        User retVal = userService.isExist(name);
        while (retVal == null || (retVal != null && !retVal.check(password))) {
            System.out.println("/!\\ wrong couple name and password \n\n");
            System.out.print("name : ");
            name = this.keyboard.nextLine();
//            password = String.valueOf(this.console.readPassword("password : "));
            System.out.print("password : ");
            password = this.keyboard.nextLine();
            retVal = userService.isExist(name);
        }
        tty.setCurrentUser(retVal);
    }

    @Override
    public void addCommand(Command cmd) {
        if (this.cmds == null) {
            this.cmds = new ArrayList<>();
        }
        this.cmds.add(cmd);
    }

}