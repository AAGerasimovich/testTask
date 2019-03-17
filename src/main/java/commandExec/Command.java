package commandExec;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {

    NEWPRODUCT("^(\\bNEWPRODUCT);" +
            "(\\S+)"),
    PURCHASE("^(\\bPURCHASE);" +
            "(\\S+);" +
            "(\\d+);" +
            "(\\d+);" +
            "date"),
    DEMAND("^(\\bDEMAND);" +
            "(\\S+);" +
            "(\\d+);" +
            "(\\d+);" +
            "date"),
    SALESREPORT("^(\\bSALESREPORT);" +
            "(\\w+);" +
            "date");


    private static final SimpleDateFormat DATE_FORMATAT = new SimpleDateFormat("dd.MM.yyyy") {{
        setLenient(false);
    }};
    String regexp;

    Command(String regexp) {
        this.regexp = regexp;
    }

    public static Command getCommand(String[] args) {

        Pattern pattern;
        Matcher matcher;
        for (Command c : Command.values()) {
            Boolean valid = true;
            String[] regexps = c.regexp.split(";");
            if (regexps.length != args.length) {
                continue;
            }
            for (int i = 0; i < regexps.length; i++) {
                if (!regexps[i].equals("date")) {
                    pattern = Pattern.compile(regexps[i]);
                    matcher = pattern.matcher(args[i]);
                    if (!matcher.find()) {
                        valid = false;
                    }
                } else {
                    if (!validDate(args[i])) {
                        valid = false;
                    }
                }
                if (valid) {
                    return c;
                }
            }
        }
        return null;
    }

    private static boolean validDate(String date) {
        try {
            DATE_FORMATAT.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}