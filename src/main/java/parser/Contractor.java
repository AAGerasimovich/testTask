package parser;

import parser.command.DemandExecutor;
import parser.command.NewProductExecutor;
import parser.command.PurchaseExecutor;
import parser.command.SalesReportExecutor;
import parser.command.generic.Command;
import parser.command.generic.Executable;
import parser.command.generic.Executor;

import java.util.HashMap;
import java.util.Map;

public class Contractor implements Executable {

    private Map<Command, Executor> executors = new HashMap<>();

    public Contractor() {
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
