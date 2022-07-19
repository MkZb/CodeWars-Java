package kata26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kata {
    public static long nextSmaller(long n) {
        ArrayList<Integer> chars = Arrays.stream(String.valueOf(n).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        if (chars.size() == 1) return -1;

        List<Integer> container = new ArrayList<>();

        for (int i = chars.size() - 1; i > 0; i--) {
            container.add(chars.remove(i));
            container.sort(Comparator.comparingInt(o -> o));

            int difference = 10;
            Integer closestNumber = -1;

            for (Integer num : container) {
                if (chars.get(i - 1) > num && chars.get(i - 1) - num < difference) {
                    difference = chars.get(i - 1) - num;
                    closestNumber = num;
                }
            }

            if (closestNumber != -1) {
                container.remove(closestNumber);
                container.add(chars.remove(i - 1));
                chars.add(closestNumber);
                container.sort((o1, o2) -> o2 - o1);
                chars.addAll(container);
                break;
            } else if (i == 1) return -1;
        }

        String result = chars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));

        return result.startsWith("0") ? -1 : Long.parseLong(result);
    }
}
