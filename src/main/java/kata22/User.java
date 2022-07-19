package kata22;

public class User {
    public int rank;
    public int progress;
    private final static int MAX_RANK = 8;
    private final static int MIN_RANK = -8;
    private final static int LEVEL_THRESHOLD = 100;

    public User() {
        progress = 0;
        rank = -8;
    }

    public void incProgress(int taskRank) {
        if (taskRank == 0 || taskRank > MAX_RANK || taskRank < MIN_RANK)
            throw new IllegalArgumentException("Incorrect task rank: " + taskRank);
        if (rank == MAX_RANK) return;

        int levelDifference;
        if (taskRank * rank < 0) {
            if (taskRank > rank) levelDifference = taskRank - rank - 1;
            else levelDifference = taskRank - rank + 1;
        } else levelDifference = taskRank - rank;

        if (levelDifference <= -2) return;

        switch (levelDifference) {
            case 0 -> addProgress(3);
            case -1 -> addProgress(1);
            default -> addProgress(10 * levelDifference * levelDifference);
        }
    }

    private void addProgress(int amount) {
        progress += amount;
        while (progress >= LEVEL_THRESHOLD) {
            rank = rank == -1 ? 1 : rank + 1;
            if (rank == MAX_RANK) {
                progress = 0;
                break;
            } else {
                progress -= LEVEL_THRESHOLD;
            }
        }
    }
}
