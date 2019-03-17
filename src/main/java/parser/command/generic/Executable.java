package parser.command.generic;


public interface Executable {

    void addCommand(Executor executor, Command command);

    void exec(Command command, String[] args);

}
