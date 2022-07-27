package kata39;

public class Hamming {
    public static long hamming(int n) {
        long[] hammingNumbers = new long[n];
        hammingNumbers[0] = 1;
        long num1 = 2, num2 = 3, num3 = 5;
        int i = 0, j = 0, k = 0;

        for (int idx = 1; idx < n; idx++) {
            hammingNumbers[idx] = Math.min(num1, Math.min(num2, num3));
            if (hammingNumbers[idx] == num1) num1 = 2 * hammingNumbers[++i];
            if (hammingNumbers[idx] == num2) num2 = 3 * hammingNumbers[++j];
            if (hammingNumbers[idx] == num3) num3 = 5 * hammingNumbers[++k];
        }

        return hammingNumbers[n - 1];
    }
}
