package kata27;

public class Magnets {
    public static double doubles(int maxk, int maxn) {
        double s = 0;
        for (int k = 1; k <= maxk; k++) {
            for (int n = 1; n <= maxn; n++) {
                s += 1 / (k * Math.pow(n + 1, 2 * k));
            }
        }
        return s;
    }
}
