package kata35;

import java.util.HashMap;
import java.util.Map;

public class BattleField {

    static final int FIELD_SIZE = 10;

    public static boolean fieldValidator(int[][] field) {
        Map<Integer, Integer> ships = new HashMap<>();
        ships.put(4, 1);
        ships.put(3, 2);
        ships.put(2, 3);
        ships.put(1, 4);

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (isOccupied(i, j, field)) {
                    if (!testAround(i, j, 1, field, ships)) return false;
                }
            }
        }
        return ships.values().stream().allMatch(el -> el == 0);
    }

    public static boolean isOccupied(int x, int y, int[][] field) {
        return field[x][y] == 1;
    }

    public static boolean testAround(int x, int y, int size, int[][] field, Map<Integer, Integer> ships) {
        if (size > 4) return false;
        int count = 0, savedX = 0, savedY = 0;
        field[x][y] = 0;
        for (int i = x == 0 ? x : x - 1; x == FIELD_SIZE - 1 ? i <= x : i <= x + 1; i++) {
            for (int j = y == 0 ? y : y - 1; y == FIELD_SIZE - 1 ? j <= y : j <= y + 1; j++) {
                if (isOccupied(i, j, field)) {
                    if (Math.abs(i - x) + Math.abs(j - y) > 1) return false;
                    else {
                        count++;
                        savedX = i;
                        savedY = j;
                    }
                }
            }
        }

        if (count == 0) {
            int shipsLeft = ships.get(size);
            if (shipsLeft == 0) return false;
            ships.replace(size, shipsLeft - 1);
            return true;
        } else if (count == 1) {
            return testAround(savedX, savedY, size + 1, field, ships);
        } else return false;
    }

}
