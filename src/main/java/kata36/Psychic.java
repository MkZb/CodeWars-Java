//src: https://franklinta.com/2014/08/31/predicting-the-next-math-random-in-java/
package kata36;

import java.util.ArrayList;
import java.util.Random;

public class Psychic {
    public static double guess() {
        Cheat cheat = new Cheat();
        cheat.replicateState(Math.random());
        return cheat.nextDouble();
    }
}

class Cheat extends Random {
    public void replicateState(double nextDouble) {
        long numerator = (long) (nextDouble * (1L << 53));
        int first26 = (int) (numerator >>> 27);
        int last27 = (int) (numerator & ((1L << 27) - 1));
        replicateState(first26, 26, last27, 27);
    }

    public void replicateState(int nextN, int n, int nextM, int m) {
        final long multiplier = 0x5DEECE66DL;
        final long addend = 0xBL;
        final long mask = (1L << 48) - 1;

        long upperMOf48Mask = ((1L << m) - 1) << (48 - m);

        long oldSeedUpperN = ((long) nextN << (48 - n)) & mask;
        long newSeedUpperM = ((long) nextM << (48 - m)) & mask;

        ArrayList<Long> possibleSeeds = new ArrayList<>();
        for (long oldSeed = oldSeedUpperN; oldSeed <= (oldSeedUpperN | ((1L << (48 - n)) - 1)); oldSeed++) {
            long newSeed = (oldSeed * multiplier + addend) & mask;
            if ((newSeed & upperMOf48Mask) == newSeedUpperM) {
                possibleSeeds.add(newSeed);
            }
        }

        if (possibleSeeds.size() == 1) {
            setSeed(possibleSeeds.get(0) ^ multiplier);
        }
    }
}
