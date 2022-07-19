package kata11;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MorseCodeDecoder {
    public static String decode(String morseCode) {
        return Arrays.stream(morseCode.strip().split(" {3}"))
                .map(word -> String.join("", Arrays.stream(word.split(" "))
                        .map(MorseCode::get)
                        .collect(Collectors.joining())))
                .collect(Collectors.joining(" "));
    }
}
