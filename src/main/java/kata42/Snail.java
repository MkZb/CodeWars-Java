package kata42;

public class Snail {
    public static int[] snail(int[][] array) {
        if (array[0].length == 0) return new int[0];
        int[] res = new int[array.length * array[0].length];
        int idx = 0;
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = array.length - 1;
        int colEnd = array[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                res[idx++] = array[rowStart][i];
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                res[idx++] = array[i][colEnd];
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                for (int i = colEnd; i > colStart - 1; i--) {
                    res[idx++] = array[rowEnd][i];
                }
            }
            rowEnd--;

            if (colStart <= colEnd) {
                for (int i = rowEnd; i > rowStart - 1; i--) {
                    res[idx++] = array[i][colStart];
                }
            }
            colStart++;
        }
        return res;
    }
}
