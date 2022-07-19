package kata30;

public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Warrior();
        System.out.println(warrior.training("Defeated Chuck Norris", 9000, 1));
        System.out.println(warrior.level());
        System.out.println(warrior.battle(90));
    }
}
