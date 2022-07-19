package kata23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObservedPin {
    public static List<String> getPINs(String observed) {
        int[][] adjacent = new int[][]{
                {0, 8},
                {1, 2, 4},
                {2, 1, 3, 5},
                {3, 2, 6},
                {4, 1, 5, 7},
                {5, 2, 4, 6, 8},
                {6, 3, 5, 9},
                {7, 4, 8},
                {8, 5, 7, 9, 0},
                {9, 6, 8}
        };

        List<int[]> possibleValues = Arrays.stream(observed.split(""))
                .map(el -> adjacent[Integer.parseInt(el)])
                .toList();

        List<String> allCombinations = new ArrayList<>();
        combinationsGenerator(possibleValues, 0, allCombinations, "");
        return allCombinations;
    }

    private static void combinationsGenerator(List<int[]> toCombine, int depth, List<String> result, String current) {
        if (depth == toCombine.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < toCombine.get(depth).length; i++) {
            combinationsGenerator(toCombine, depth + 1, result, current + toCombine.get(depth)[i]);
        }
    }
}
