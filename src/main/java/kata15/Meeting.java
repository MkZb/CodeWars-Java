package kata15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Meeting {
    public static String meeting(String s) {
        return Arrays.stream(s.split(";"))
                .map(String::toUpperCase)
                .sorted((o1, o2) -> {
                    String[] firstInfo = o1.split(":");
                    String[] secondInfo = o2.split(":");
                    if (!firstInfo[1].equals(secondInfo[1]))
                        {
                            return firstInfo[1].compareTo(secondInfo[1]);
                        }
                    else {
                        return firstInfo[0].compareTo(secondInfo[0]);
                    }
                })
                .map(entry -> {
                    List<String> info = Arrays.asList(entry.split(":"));
                    Collections.reverse(info);
                    return String.join(", ", info.toArray(new String[0]));
                })
                .collect(Collectors.joining(")(", "(", ")"));
    }
}
