package easykatas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static String even_or_odd(int number) {
        return number % 2 == 0 ? "Even" : "Odd";
    }

    public static String stringy(int size) {
        return Stream.iterate(1, i -> i == 1 ? 0 : 1)
                .limit(size)
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }

    public static int quarterOf(int month) {
        return (month - 1) / 3 + 1;
    }

    public static boolean isLove(final int flower1, final int flower2) {
        return ((flower1 + flower2) & 0b1) == 1;
    }

    public String dnaToRna(String dna) {
        return dna.replace("T", "U");
    }

    public static int summation(int n) {
        return IntStream.rangeClosed(1, n).sum();
    }

    public static int Past(int h, int m, int s) {
        return (h * 3600 + m * 60 + s) * 1000;
    }

    public static boolean isTriangle(int a, int b, int c) {
        return a + b > c && a + c > b && b + c > a;
    }

    public static String rps(String p1, String p2) {
        if (p1.equals(p2)) return "Draw";
        if ((p1.equals("paper") && p2.equals("rock")) ||
                (p1.equals("rock") && p2.equals("scissors")) ||
                (p1.equals("scissors") && p2.equals("paper"))) {
            return "Player 1 won";
        } else {
            return "Player 2 won";
        }
    }

    public static String calculate(double distance, String time) {
        Integer[] timeInInt = Arrays.stream(time.split(":"))
                .map(el -> el.replace("^(00)", "0"))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        int paceInSeconds = (int) ((timeInInt[0] * 60 + timeInInt[1]) / distance);
        return String.format("%d:%02d", paceInSeconds / 60, paceInSeconds % 60);
    }

    public static String abbrevName(String name) {
        return Arrays.stream(name.split(" "))
                .map(el -> String.valueOf(el.charAt(0)))
                .map(String::toUpperCase)
                .collect(Collectors.joining("."));
    }

    public static String greet(String language) {
        Map<String, String> database = new HashMap<>();
        database.put("english", "Welcome");
        database.put("czech", "Vitejte");
        database.put("danish", "Velkomst");
        database.put("dutch", "Welkom");
        database.put("estonian", "Tere tulemast");
        database.put("finnish", "Tervetuloa");
        database.put("flemish", "Welgekomen");
        database.put("french", "Bienvenue");
        database.put("german", "Willkommen");
        database.put("irish", "Failte");
        database.put("italian", "Benvenuto");
        database.put("latvian", "Gaidits");
        database.put("lithuanian", "Laukiamas");
        database.put("polish", "Witamy");
        database.put("spanish", "Bienvenido");
        database.put("swedish", "Valkommen");
        database.put("welsh", "Croeso");
        return database.getOrDefault(language, "Welcome");
    }

    public static String twoSort(String[] s) {
        return String.join("***", Arrays.stream(s)
                .sorted()
                .findFirst()
                .orElse("").split(""));
    }

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null) return false;
        int[] aSquared = Arrays.stream(a).map(el -> el * el).sorted().toArray();
        Arrays.sort(b);
        return Arrays.equals(aSquared, b);
    }

    public static boolean zeroFuel(double distanceToPump, double mpg, double fuelLeft) {
        return fuelLeft * mpg >= distanceToPump;
    }

    static String findChildren(final String text) {
        return text.chars()
                .boxed()
                .sorted(((o1, o2) -> {
                    if ((o1 > 96 && o2 > 96) || (o1 < 96 && o2 < 96) || Math.max(o1, o2) - 32 == Math.min(o1, o2)) return o1 - o2;
                    if (o1 > o2) return (o1 - 32) - o2;
                    else return o1 - (o2 - 32);
                }))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {

    }
}
