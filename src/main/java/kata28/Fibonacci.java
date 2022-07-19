package kata28;

import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger fib(BigInteger n) {
        BigInteger res = fibIter(BigInteger.ONE, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE, n.compareTo(BigInteger.ZERO) < 0 ? n.multiply(BigInteger.valueOf(-1)) : n);
        return n.compareTo(BigInteger.ZERO) < 1 && n.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0 ? res.multiply(BigInteger.valueOf(-1)) : res;
    }

    public static BigInteger fibIter(BigInteger a, BigInteger b, BigInteger p, BigInteger q, BigInteger count) {
        if (count.compareTo(BigInteger.ZERO) == 0) return b;
        if (count.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
            return fibIter(a, b, p.multiply(p).add(q.multiply(q)), q.multiply(q.add(p.multiply(BigInteger.TWO))), count.divide(BigInteger.TWO));
        } else {
            return fibIter(b.multiply(q).add(a.multiply(q)).add(a.multiply(p)), b.multiply(p).add(a.multiply(q)), p, q, count.subtract(BigInteger.valueOf(1)));
        }
    }
}
