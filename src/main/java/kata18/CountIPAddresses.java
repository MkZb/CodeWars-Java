package kata18;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CountIPAddresses {
    public static long ipsBetween(String start, String end) {
        long startAdress = Long.parseLong(Arrays.stream(start.split("\\."))
                .map(Integer::parseInt)
                .map(Integer::toBinaryString)
                .map(Integer::parseInt)
                .map(s -> String.format("%08d", s))
                .collect(Collectors.joining("")), 2);

        long endAdress = Long.parseLong(Arrays.stream(end.split("\\."))
                .map(Integer::parseInt)
                .map(Integer::toBinaryString)
                .map(Integer::parseInt)
                .map(s -> String.format("%08d", s))
                .collect(Collectors.joining("")), 2);

        return endAdress - startAdress;
    }
}
