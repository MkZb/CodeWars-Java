package kata41;

public class Main {
    public static void main(String[] args) {
        ScreenLock screenLock = new ScreenLock();
        System.out.println(screenLock.calculateCombinations('A', 10));
        System.out.println(screenLock.calculateCombinations('A', 0));
        System.out.println(screenLock.calculateCombinations('E', 14));
        System.out.println(screenLock.calculateCombinations('B', 1));
        System.out.println(screenLock.calculateCombinations('C', 2));
        System.out.println(screenLock.calculateCombinations('E', 2));
        System.out.println(screenLock.calculateCombinations('E', 4));
    }
}
