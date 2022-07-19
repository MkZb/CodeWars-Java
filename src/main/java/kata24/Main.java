package kata24;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        for (long[] el : RemovedNumbers.removNb(26)) {
            System.out.println(Arrays.toString(el));
        }
    }
}
