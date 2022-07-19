package kata21;

import java.math.BigInteger;

public class SumFct {
    public static BigInteger perimeter(BigInteger n) {
        BigInteger perimeter = new BigInteger("0");
        BigInteger valX = new BigInteger("0");
        BigInteger valY = new BigInteger("1");

        if (n.compareTo(BigInteger.ZERO) == 0) return valY.multiply(new BigInteger("4"));

        for (BigInteger counter = BigInteger.valueOf(1);
             counter.compareTo(n.add(BigInteger.ONE)) < 0;
             counter = counter.add(BigInteger.ONE)) {
            BigInteger newVal = valX.add(valY);
            valX = valY;
            valY = newVal;
            perimeter = perimeter.add(valY);
        }

        return perimeter.multiply(new BigInteger("4")).add(BigInteger.TWO).add(BigInteger.TWO);
    }
}
