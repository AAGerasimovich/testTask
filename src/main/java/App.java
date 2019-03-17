import parser.Contractor;
import parser.command.generic.Command;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contractor contractor = new Contractor();
        while (!sc.hasNext("EXIT")) {
            String s = sc.nextLine();
            if (s.equals("")) {
                continue;
            }
            Command command = Command.getCommand(s.split("\\s+"));
            if (command != null) {
                contractor.exec(command, s.split("\\s+"));
            } else {
                System.out.println("ERROR");
            }
        }
    }

}



