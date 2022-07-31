package kata44;

import java.util.LinkedList;

public class BraceChecker {
    public boolean isValid(String braces) {
        LinkedList<Character> queue = new LinkedList<>();
        for (int i = 0; i < braces.length(); i++) {
            switch (braces.charAt(i)) {
                case '(', '[', '{' -> queue.addLast(braces.charAt(i));
                default -> {
                    Character lastInQueue = queue.pollLast();
                    if (lastInQueue == null || lastInQueue != (braces.charAt(i) == ')' ? '(' : braces.charAt(i) == ']' ? '[' : '{')) {
                        return false;
                    }
                }
            }
        }
        return queue.size() == 0;
    }
}
