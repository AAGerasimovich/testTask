package commandExec.command.generic;


import commandExec.Command;

public interface Executable {

    void addCommand(Executor executor, Command command);
    void exec(Command command, String[] args);

}
