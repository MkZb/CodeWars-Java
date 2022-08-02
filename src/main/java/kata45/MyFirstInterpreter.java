package kata45;

public class MyFirstInterpreter {
    char cell = 0;
    char[] code;
    StringBuilder result = new StringBuilder();

    public MyFirstInterpreter(String code) {
        this.code = code.replaceAll("[^+.]", "").toCharArray();
    }

    public String interpret() {
        for (char ch : code) {
            switch (ch) {
                case '.' -> result.append(cell);
                case '+' -> {
                    if (cell == 255) cell = 0;
                    else cell++;
                }
            }
        }
        return result.toString();
    }
}
