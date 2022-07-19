package kata4;

public class Kata {
    public static char findMissingLetter(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i + 1] != (array[i] + 1)) return (char)(array[i] + 1);
        }
        return '0';
    }
}
