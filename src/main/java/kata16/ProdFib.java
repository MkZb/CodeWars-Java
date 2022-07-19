package kata16;

import java.util.ArrayList;

public class ProdFib {
    public static long[] productFib(long prod) {
        long prodResult = 0;
        int n = 0;
        Fibonacci fibonacci = new Fibonacci();
        while (prodResult < prod) {
            while (!fibonacci.hasN(n + 1)) fibonacci.genValue();
            prodResult = fibonacci.getValue(n) * fibonacci.getValue(n + 1);
            n++;
        }
        return new long[]{fibonacci.getValue(n - 1), fibonacci.getValue(n), prodResult == prod ? 1 : 0};
    }

    static class Fibonacci {
        private final ArrayList<Long> fibValues = new ArrayList<>();

        Fibonacci() {
            fibValues.add(0L);
            fibValues.add(1L);
        }

        public void genValue() {
            fibValues.add(fibValues.get(fibValues.size() - 1) + fibValues.get(fibValues.size() - 2));
        }

        public boolean hasN(int n) {
            return fibValues.size() >= n + 1;
        }

        public long getValue(int n) {
            return fibValues.get(n);
        }
    }
}
