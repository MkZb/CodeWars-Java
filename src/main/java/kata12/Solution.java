package kata12;

public class Solution {
    public static int solveSuperMarketQueue(int[] customers, int n) {
        int served = 0;
        int timeNeeded = 0;

        Till[] tills = new Till[n];
        for (int i = 0; i < n; i++) {
            tills[i] = new Till();
        }

        while (served != customers.length) {
            for (int i = 0; i < n; i++) {
                if (served != customers.length && tills[i].canAcquire()) {
                    tills[i].acquire(customers[served]);
                    served += 1;
                }
                tills[i].tick();
            }
        }

        for (int i = 0; i < n; i++) {
            if (tills[i].getTotalTime() > timeNeeded) timeNeeded = tills[i].getTotalTime();
        }

        return timeNeeded;
    }
}

class Till {
    private int timeLeft = 0;
    private int totalTime = 0;

    public void acquire(int time) {
        timeLeft = time;
        totalTime += time;
    }

    public void tick() {
        timeLeft -= 1;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public boolean canAcquire() {
        return timeLeft == 0;
    }
}