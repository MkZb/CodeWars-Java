package kata34;

public class Kata {

    public static String intToNegabinary(int i) {
        StringBuilder result = new StringBuilder(i == 0 ? "0" : "");
        int number = i;
        while (number != 0) {
            int j = number % (-2);
            number /= -2;
            if (j < 0) {
                j += Math.abs(-2);
                number++;
            }
            result.insert(0, j);
        }
        return result.toString();
    }

    public static int negabinaryToInt(String s) {
        String[] num = s.split("");
        int res = 0;
        for (int i = num.length - 1, j = 0; i >= 0; i--, j++) {
            res += Integer.parseInt(num[i]) * Math.pow(-2, j);
        }
        return res;
    }

}
