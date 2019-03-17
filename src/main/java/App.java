import commandExec.ExecutableImpl;
import commandExec.Command;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExecutableImpl executableImpl = new ExecutableImpl();
        while (!sc.hasNext("EXIT")) {
            String s = sc.nextLine();
            if (s.equals("")) {
                continue;
            }
            String[] arg = s.split("\\s+");
            Command command = Command.getCommand(arg);
            if (command != null) {
                executableImpl.exec(command, arg);
            } else {
                System.out.println("ERROR");
            }
        }
    }

}



