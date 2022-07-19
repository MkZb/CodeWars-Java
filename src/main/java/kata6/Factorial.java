package kata6;

public class Factorial {
    public int factorial(int n) {
        if (n < 0 || n > 12) throw new IllegalArgumentException();
        int res = 1;
        if (n > 1) {
            res *= n;
            res *= factorial(n - 1);
        }
        return res;
    }
}
