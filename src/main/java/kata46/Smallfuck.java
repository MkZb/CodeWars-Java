package kata46;

public class Smallfuck {
    public static String interpreter(String code, String tape) {
        int dataPointer = 0;
        int commandPointer = 0;
        char[] commands = code.replaceAll("[^><*\\[\\]]", "").toCharArray();
        char[] data = tape.toCharArray();
        while (commandPointer < commands.length && dataPointer >= 0 && dataPointer < data.length) {
            switch (commands[commandPointer]) {
                case '>' -> dataPointer++;
                case '<' -> dataPointer--;
                case '*' -> data[dataPointer] = data[dataPointer] == '0' ? '1' : '0';
                case '[' -> {
                    if (data[dataPointer] == '0') {
                        boolean isFound = false;
                        int innerCycle = 0;
                        int testPtr = commandPointer;
                        while (!isFound) {
                            if (commands[++testPtr] == ']') {
                                if (innerCycle == 0) isFound = true;
                                else innerCycle--;
                            } else if (commands[testPtr] == '[') {
                                innerCycle++;
                            }
                        }
                        commandPointer = testPtr;
                    }
                }
                case ']' -> {
                    if (data[dataPointer] != '0') {
                        boolean isFound = false;
                        int innerCycle = 0;
                        int testPtr = commandPointer;
                        while (!isFound) {
                            if (commands[--testPtr] == '[') {
                                if (innerCycle == 0) isFound = true;
                                else innerCycle--;
                            } else if (commands[testPtr] == ']') {
                                innerCycle++;
                            }
                        }
                        commandPointer = testPtr;
                    }
                }
            }
            commandPointer++;
        }
        return new String(data);
    }
}
