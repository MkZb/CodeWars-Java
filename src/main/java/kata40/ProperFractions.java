package kata40;

import java.util.HashMap;
import java.util.Map;

public class ProperFractions {
    public static long properFractions(long n) {
        if (n == 1) return 0;
        Map<Long, Integer> primeFactors = new HashMap<>();

        //Find all prime factors of n in O(n^(1/2) log n)
        int counter = 0;
        while (n % 2 == 0) {
            counter++;
            n /= 2;
        }
        if (counter != 0) {
            primeFactors.put(2L, counter);
        }
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            counter = 0;
            while (n % i == 0) {
                counter++;
                n /= i;
            }
            if (counter!=0){
                primeFactors.put(i, counter);
            }
        }
        if (n > 2)
            primeFactors.put(n, 1);

        //Apply Euler's totient function
        long res = 1;
        for (Long key : primeFactors.keySet()) {
            int k = primeFactors.get(key);
            res *= Math.pow(key, k) - Math.pow(key, k - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(properFractions(5));
    }
}
