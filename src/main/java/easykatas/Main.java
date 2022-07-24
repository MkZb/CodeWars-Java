package easykatas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static boolean isPlural(float f) {
        return f == 1;
    }

    public static String maskify(String str) {
        return str.length() > 4 ? str.substring(0, str.length() - 4).replaceAll(".", "#") + str.substring(str.length() - 4) : str;
    }

    public static int cockroachSpeed(double x) {
        return (int) Math.floor(x * 250 / 9);
    }

    public static String balancedNum(long number) {
        if (number < 100) return "Balanced";

        String stringNum = String.valueOf(number);
        int leftSum;
        int rightSum = Arrays.stream(stringNum.substring(stringNum.length() / 2 + 1).split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);

        if (stringNum.length() % 2 == 1) {
            leftSum = Arrays.stream(stringNum.substring(0, stringNum.length() / 2).split(""))
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        } else {
            leftSum = Arrays.stream(stringNum.substring(0, stringNum.length() / 2 - 1).split(""))
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        }

        return leftSum == rightSum ? "Balanced" : "Not Balanced";
    }

    public static double sum(double[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public static Integer basicMath(String op, int v1, int v2) {
        switch (op) {
            case "+" -> {
                return v1 + v2;
            }
            case "-" -> {
                return v1 - v2;
            }
            case "*" -> {
                return v1 * v2;
            }
            case "/" -> {
                return v1 / v2;
            }
            default -> {
                return null;
            }
        }
    }

    public static String oddOrEven(int[] array) {
        return Arrays.stream(array).sum() % 2 == 0 ? "even" : "odd";
    }

    public static int solution(int number) {
        return IntStream.range(1, number)
                .filter(el -> el % 3 == 0 || el % 5 == 0)
                .sum();
    }

    public static List<String> number(List<String> lines) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            res.add(i + 1 + ": " + lines.get(i));
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
