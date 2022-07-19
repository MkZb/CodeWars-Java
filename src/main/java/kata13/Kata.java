package kata13;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Kata {
    public static String encryptThis(String text) {
        return Objects.equals(text, "") ? "" : Arrays.stream(text.split(" "))
                .map(Kata::encryptWord)
                .collect(Collectors.joining(" "));
    }

    public static String encryptWord(String word) {
        String[] symbols = word.split("");
        symbols[0] = String.valueOf((int) symbols[0].charAt(0));
        if (symbols.length > 2) {
            String temp = symbols[1];
            symbols[1] = symbols[symbols.length - 1];
            symbols[symbols.length - 1] = temp;
        }
        return String.join("", symbols);
    }
}
