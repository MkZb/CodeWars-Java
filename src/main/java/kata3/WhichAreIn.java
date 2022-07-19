package kata3;

import java.util.ArrayList;
import java.util.Arrays;

public class WhichAreIn {
    public static String[] inArray(String[] array1, String[] array2) {
        ArrayList<String> result = new ArrayList<>();
        Arrays.stream(array1).sorted().forEach(word -> Arrays.stream(array2)
                .map(el -> el.contains(word))
                .forEach(res -> {
                    if (res && !result.contains(word)) result.add(word);
                }));
        return result.toArray(new String[0]);
    }
}
