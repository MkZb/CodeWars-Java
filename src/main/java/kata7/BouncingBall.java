package kata7;

public class BouncingBall {
    public static int bouncingBall(double h, double bounce, double window) {
        if (h <= 0 || !(bounce > 0 && bounce < 1) || window >= h) return -1;
        boolean isBallSeen = true;
        int timesSeen = 1;
        double newHeight = h;
        while (isBallSeen) {
            if (newHeight * bounce <= window) isBallSeen = false;
            else {
                timesSeen += 2;
                newHeight *= bounce;
            }
        }
        return timesSeen;
    }
}
