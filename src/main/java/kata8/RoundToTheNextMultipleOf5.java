package kata8;

public class RoundToTheNextMultipleOf5 {
    public static int roundToNext5(int number) {
        if (number % 5 == 0) return number;
        else if (number > 0) return number + (5 - number % 5);
        else return number - number % 5;
    }
}
