package easykatas;

public class Main {

    public static void main(String[] args) {
        System.out.println(maskify("dfg"));
    }

    public static boolean isPlural(float f) {
        return f == 1;
    }

    public static String maskify(String str) {
        return str.length() > 4 ? str.substring(0, str.length() - 4).replaceAll(".", "#") + str.substring(str.length() - 4) : str;
    }


}
