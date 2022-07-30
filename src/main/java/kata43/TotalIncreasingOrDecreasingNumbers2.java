package kata43;

import java.math.BigInteger;

public class TotalIncreasingOrDecreasingNumbers2 {
    public static BigInteger totalIncDec(int x) {
        BigInteger num = BigInteger.valueOf(10);
        num = num.pow(x);
        return getIncDecForNDigits(num.toString().split("").length - 1);
    }

    private static BigInteger getIncDecForNDigits(int n) {
        if (n == 0) return BigInteger.ONE;
        if (n == 1) return BigInteger.TEN;
        BigInteger[][] increasingOrder = new BigInteger[n - 1][9];
        BigInteger[][] decreasingOrder = new BigInteger[n - 1][10];

        for (int i = 0; i < 9; i++) {
            increasingOrder[0][i] = BigInteger.valueOf(9 - i);
            decreasingOrder[0][i] = BigInteger.valueOf(10 - i);
        }
        decreasingOrder[0][9] = BigInteger.valueOf(1);

        for (int i = 1; i < n - 1; i++) {
            BigInteger sum1 = BigInteger.ZERO;
            BigInteger sum2 = BigInteger.ZERO;

            for (int j = 0; j < 9; j++) {
                sum1 = sum1.add(increasingOrder[i - 1][j]);
                sum2 = sum2.add(decreasingOrder[i - 1][j]);
            }
            sum2 = sum2.add(decreasingOrder[i - 1][9]);


            for (int j = 0; j < 9; j++) {
                increasingOrder[i][j] = sum1;
                sum1 = sum1.subtract(increasingOrder[i - 1][j]);
                decreasingOrder[i][j] = sum2;
                sum2 = sum2.subtract(decreasingOrder[i - 1][j]);
            }
            decreasingOrder[i][9] = sum2;
        }

        BigInteger res = BigInteger.ONE;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 9; j++) {
                res = res.add(increasingOrder[i][j]);
                res = res.add(decreasingOrder[i][j]);
            }
            if (i != 0) res = res.subtract(BigInteger.valueOf(9));
        }

        return res;
    }
}
