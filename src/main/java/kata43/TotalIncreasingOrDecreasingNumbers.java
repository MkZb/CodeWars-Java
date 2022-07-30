package kata43;

import java.math.BigInteger;

public class TotalIncreasingOrDecreasingNumbers {
    public static BigInteger totalIncDec(int x) {
        if (x == 0) return BigInteger.ONE;
        BigInteger num = new BigInteger(String.valueOf(10));
        num = num.pow(x);
        BigInteger test = BigInteger.ZERO;
        for (int i = 1; i < num.toString().length(); i++) {
            System.out.println(i);
            test = test.add(getIncDecForNDigits(i));
        }
        //System.out.println(test);
        return test;
    }

    private static BigInteger getIncDecForNDigits(int n) {
        if (n == 1) return BigInteger.TEN;
        if (n == 2) return BigInteger.valueOf(90);
        BigInteger inc = getInc(1, n - 1);
        BigInteger dec = getDec(9, n - 1, true);
        return inc.add(dec).subtract(BigInteger.TEN).add(BigInteger.ONE);
    }

    private static BigInteger getInc(int start, int depth) {
        if (depth == 0) return BigInteger.TEN.subtract(BigInteger.valueOf(start));
        BigInteger acc = BigInteger.ZERO;
        for (int i = start; i < 10; i++) {
            acc = acc.add(getInc(i, depth - 1));
        }
        //System.out.println(acc);
        return acc;
    }

    private static BigInteger getDec(int start, int depth, boolean init) {
        if (depth == 0) return BigInteger.valueOf(start).add(BigInteger.ONE);
        BigInteger acc = BigInteger.ZERO;
        for (int i = start; init ? i > 0 : i >= 0; i--) {
            acc = acc.add(getDec(i, depth - 1, false));
        }
        //System.out.println(acc);
        return acc;
    }
}
