package kata33;

public class Main {

    public static void main(String[] args) {
        MathEvaluator me = new MathEvaluator();
        System.out.println(me.calculate("(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11) "));
    }
}
