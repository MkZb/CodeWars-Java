package kata19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Max {
    public static int sequence(int[] arr) {
        if (Arrays.stream(arr).max().orElse(0) < 0) return 0;
        if (Arrays.stream(arr).min().orElse(0) >= 0) return Arrays.stream(arr).sum();

        ArrayList<int[]> subList = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < arr.length - i; j++) {
                int[] subArr = new int[i + 1];
                System.arraycopy(arr, j, subArr, 0, i + 1);
                subList.add(subArr);
            }
        }

        return Arrays.stream(subList.stream()
                .max(Comparator.comparingInt(o -> Arrays.stream(o).sum()))
                .orElseGet(() -> new int[0])).sum();
    }
}
