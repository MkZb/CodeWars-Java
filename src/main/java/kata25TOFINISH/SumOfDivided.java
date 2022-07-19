package kata25TOFINISH;

import java.util.ArrayList;
import java.util.List;

public class SumOfDivided {
    public static String sumOfDivided(int[] l) {
        List<List<Integer>> primeFactorsList = new ArrayList<>();
        for (int i = 0; i < l.length - 1; i++) {
            primeFactorsList.add(factorize(l[i]));
        }
        //primeFactorsList.
        return "";
    }

    public static List<Integer> factorize(int number) {
        List<Integer> result = new ArrayList<>();
        while (number != 1 ) {
            for (int i = 2; i <= number; i++) {
                if (number % i == 0) {
                    result.add(i);
                    number /= i;
                    break;
                }
            }
        }
        return result;
    }
}
