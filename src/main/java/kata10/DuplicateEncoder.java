package kata10;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateEncoder {
    static String encode(String word) {
        Map<String, Long> letterOccurrence = Arrays.stream(word.toLowerCase().split(""))
                .collect(Collectors.groupingBy(el -> el, Collectors.counting()));
        return Arrays.stream(word.toLowerCase().split(""))
                .map(symbol -> letterOccurrence.get(symbol) == 1 ? "(" : ")")
                .collect(Collectors.joining(""));
    }
}
