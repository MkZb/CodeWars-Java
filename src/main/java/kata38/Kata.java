package kata38;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Kata {
    public static String longToIP(long ip) {
        StringBuilder binaryIp = new StringBuilder(Long.toBinaryString(ip));
        for (int i = binaryIp.length(); i < 32; i++) {
            binaryIp.insert(0, 0);
        }
        return Arrays.stream(binaryIp.toString().split("(?<=\\G.{8})"))
                .map(el -> Integer.valueOf(el, 2))
                .map(String::valueOf)
                .collect(Collectors.joining("."));
    }
}
