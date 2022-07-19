package kata14;

import java.util.ArrayList;

public class MexicanWave {
    public static String[] wave(String str) {
        if (str.equals("")) return new String[0];
        ArrayList<String> wave = new ArrayList<>();
        String[] symbols = str.split("");
        for (int i = 0; i < symbols.length; i++) {
            if (symbols[i].equals(" ")) continue;
            symbols[i] = symbols[i].toUpperCase();
            wave.add(String.join("", symbols));
            symbols[i] = symbols[i].toLowerCase();
        }
        return wave.toArray(new String[0]);
    }
}
