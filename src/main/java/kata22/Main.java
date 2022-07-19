package kata22;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.rank);
        System.out.println(user.progress);
        user.incProgress(-7);
        System.out.println(user.progress);
        user.incProgress(-5);
        System.out.println(user.progress);
        System.out.println(user.rank);
        user.incProgress(1);
        System.out.println(user.progress);
        System.out.println(user.rank);
    }
}

