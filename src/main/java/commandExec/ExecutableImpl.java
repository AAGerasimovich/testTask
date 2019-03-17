package commandExec;

import commandExec.command.DemandExecutor;
import commandExec.command.NewProductExecutor;
import commandExec.command.PurchaseExecutor;
import commandExec.command.SalesReportExecutor;
import commandExec.command.generic.Executable;
import commandExec.command.generic.Executor;

import java.util.HashMap;
import java.util.Map;

public class ExecutableImpl implements Executable {

    private Map<Command, Executor> executors = new HashMap<>();

    public ExecutableImpl() {
        init();
    }

    private void init() {
        executors.put(Command.DEMAND, new DemandExecutor());
        executors.put(Command.NEWPRODUCT, new NewProductExecutor());
        executors.put(Command.PURCHASE, new PurchaseExecutor());
        executors.put(Command.SALESREPORT, new SalesReportExecutor());
    }

    @Override
    public void exec(Command command, String[] args) {
        executors.get(command).execute(args);
    }

    @Override
    public void addCommand(Executor executor, Command command) {
        executors.put(command, executor);

    }
}
