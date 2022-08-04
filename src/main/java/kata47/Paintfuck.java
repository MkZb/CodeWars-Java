    package kata47;

    import java.util.Arrays;
    import java.util.Stack;
    import java.util.stream.Collectors;

    public class Paintfuck {
        public static String interpreter(String code, int iterations, int width, int height) {
            Stack<Integer> stack = new Stack<>();
            int[][] data = new int[height][width];
            int dataX = 0;
            int dataY = 0;
            int commandPointer = 0;
            int commandsCounter = 0;
            char[] commands = code.replaceAll("[^nesw*\\[\\]]", "").toCharArray();
            while (commandPointer != commands.length && commandsCounter != iterations) {
                switch (commands[commandPointer]) {
                    case 'n' -> dataY = dataY == 0 ? height - 1 : dataY - 1;
                    case 's' -> dataY = dataY == height - 1 ? 0 : dataY + 1;
                    case 'e' -> dataX = dataX == width - 1 ? 0 : dataX + 1;
                    case 'w' -> dataX = dataX == 0 ? width - 1 : dataX - 1;
                    case '*' -> data[dataY][dataX] = (data[dataY][dataX] == 0 ? 1 : 0);
                    case '[' -> {
                        stack.push(commandPointer);
                        if (data[dataY][dataX] == 0) {
                            int stackSize = stack.size() - 1;
                            while (!(stackSize == stack.size())) {
                                commandPointer++;
                                if (commands[commandPointer] == '[') stack.push(commandPointer);
                                if (commands[commandPointer] == ']') stack.pop();
                            }
                        }
                    }
                    case ']' -> {
                        if (data[dataY][dataX] == 0) stack.pop();
                        else commandPointer = stack.peek();
                    }
                }
                commandPointer++;
                commandsCounter++;
            }
            return Arrays.stream(data)
                    .map(el -> Arrays.stream(el)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining("")))
                    .collect(Collectors.joining("\r\n"));
        }
    }
