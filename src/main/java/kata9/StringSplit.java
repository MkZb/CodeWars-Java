package kata9;

public class StringSplit {
    public static String[] solution(String s) {
        if (s.length() == 0) return new String[]{""};
        int substringCount = s.length() / 2 + s.length() % 2;
        String[] res = new String[substringCount];
        for (int i = 0; i < substringCount; i++) {
            res[i] = s.substring(2 * i, i == substringCount - 1 ? 2 * i + 2 - s.length() % 2 : 2 * i + 2);
        }
        if (res[res.length - 1].length() == 1) res[res.length - 1] += "_";
        return res;
    }
}
