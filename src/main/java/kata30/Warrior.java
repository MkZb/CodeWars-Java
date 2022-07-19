package kata30;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
    private enum Rank {
        Pushover,
        Novice,
        Fighter,
        Warrior,
        Veteran,
        Sage,
        Elite,
        Conqueror,
        Champion,
        Master,
        Greatest
    }

    private final int MAX_LEVEL = 100;
    private final List<String> achievements;

    private int level;
    private int exp;
    private Rank rank;


    Warrior() {
        level = 1;
        exp = 100;
        rank = calcRank(level);
        achievements = new ArrayList<>();
    }

    public String battle(int enemyLevel) {
        if (enemyLevel < 1 || enemyLevel > MAX_LEVEL) {
            return "Invalid level";
        }

        int diff = level - enemyLevel;
        if (diff <= -5 && calcRank(enemyLevel).ordinal() - calcRank(level).ordinal() >= 1) {
            return "You've been defeated";
        }

        if (diff >= 2) {
            return "Easy fight";
        }

        switch (diff) {
            case 1 -> {
                calcExp(5);
                return "A good fight";
            }
            case 0 -> {
                calcExp(10);
                return "A good fight";
            }
            default -> {
                calcExp(20 * diff * diff);
                return "An intense fight";
            }
        }
    }

    public String training(String description, int expReward, int levelRequirement) {
        if (level < levelRequirement) {
            return "Not strong enough";
        }
        calcExp(expReward);
        achievements.add(description);
        return description;
    }

    public int level() {
        return level;
    }

    public int experience() {
        return exp;
    }

    public String rank() {
        return String.valueOf(rank);
    }

    public List<String> achievements() {
        return achievements;
    }

    private Rank calcRank(int currentLevel) {
        return Rank.values()[currentLevel / 10];
    }

    private void calcExp(int expGained) {
        if (level == MAX_LEVEL) return;
        exp = Math.min(exp + expGained, 10000);
        level = exp / 100;
        rank = calcRank(level);
    }
}
