package kata17;

public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        boolean isScramblable = true;
        for (String symbol : str2.split("")) {
            int oldLength = str1.length();
            str1 = str1.replaceFirst(symbol, "");
            if (oldLength == str1.length()) isScramblable = false;
        }
        return isScramblable;
    }
}
