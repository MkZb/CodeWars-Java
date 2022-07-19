package kata26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KataNope {
    public static long nextSmaller(long n) {
        List<Integer> numbers = Arrays.stream(String.valueOf(n).split(""))
                .map(Integer::parseInt)
                .toList();

        List<Long> allCombinations = new ArrayList<>();

        for (int i = 2; i <= numbers.size(); i++) {
            parseCombinations(numbers, 0, i, i, allCombinations, "");
            long closest = allCombinations.stream()
                    .filter(el -> el < n)
                    .min((Comparator.comparingLong(o -> n - o)))
                    .orElse(Long.MAX_VALUE);
            //System.out.println("Closest " + closest);
            if (closest != Long.MAX_VALUE) return closest;
        }

        return -1;
    }

    private static void parseCombinations(List<Integer> input, int currentDepth, int digitsLeft, int lastDigitsVariance, List<Long> result, String current) {
        if (currentDepth == lastDigitsVariance) {
            for (int i = input.size() - 1; i >= 0; i--) {
                current = String.valueOf(input.get(i)).concat(current);
            }
            if (!result.contains(Long.parseLong(current)) && !current.startsWith("0")) {
                //System.out.println(current);
                result.add(Long.parseLong(current));
            }
            return;
        }

        for (int i = input.size() - 1; i >= input.size() - digitsLeft; i--) {
            List<Integer> copy = new ArrayList<>(input); //SHMAT GIMNA
            copy.remove(i);
            parseCombinations(copy, currentDepth + 1, digitsLeft - 1, lastDigitsVariance, result, String.valueOf(input.get(i)).concat(current));
        }
    }
}
