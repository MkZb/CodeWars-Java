package kata20;

import java.util.ArrayList;

public class JosephusSurvivor {
    public static int josephusSurvivor(final int n, final int k) {
        return new Survivors(n, k).solve();
    }
}

class Survivors {
    private final int step;
    private final ArrayList<Integer> survivors = new ArrayList<>();
    private int lastKilled = 0;

    Survivors(int n, int step) {
        this.step = step;
        for (int i = 1; i <= n; i++) {
            survivors.add(i);
        }
    }

    public void kill() {
        int toKill = lastKilled + step - 1;
        while (toKill > survivors.size() - 1) {
            toKill -= survivors.size();
        }
        survivors.remove(toKill);
        lastKilled = toKill;
    }

    public int solve() {
        while (survivors.size() != 1) {
            kill();
        }
        return survivors.get(0);
    }
}


