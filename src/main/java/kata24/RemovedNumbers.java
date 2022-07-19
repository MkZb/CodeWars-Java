package kata24;

import java.util.ArrayList;
import java.util.List;

public class RemovedNumbers {
    public static List<long[]> removNb(long n) {
        List<long[]> results = new ArrayList<>();
        long sequenceSum = 0;

        for (long i = 1; i <= n; i++) {
            sequenceSum += i;
        }

        for (long x = 1; x <= n; x++) {
            if ((sequenceSum - x) % (x + 1) == 0) {
                long y = (sequenceSum - x) / (x + 1);
                if (y <= n) results.add(new long[]{x, y});
            }
        }

        return results;
    }
}
