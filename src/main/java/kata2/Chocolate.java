package kata2;

public class Chocolate {
    public static int breakChocolate(int n, int m) {
        if (m * n == 0) return 0;
        if (n == 1) return m - 1;
        if (m == 1) return n - 1;
        return (m - 1) + m * (n - 1);
    }
}
