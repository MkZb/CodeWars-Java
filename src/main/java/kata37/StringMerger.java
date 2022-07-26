package kata37;

public class StringMerger {
    public static boolean isMerge(String s, String part1, String part2) {
        if (s.equals("")) return part1.equals("") && part2.equals("");
        if (part1.equals("") && part2.equals("")) return false;
        if (!part1.equals("") && !part2.equals("") && part1.substring(0, 1).equals(part2.substring(0, 1))) {
            boolean canMerge = isMerge(s.substring(1), part1.substring(1), part2);
            if (!canMerge) return isMerge(s.substring(1), part1, part2.substring(1));
        }
        if (!part1.equals("") && part1.startsWith(s.substring(0, 1)))
            return isMerge(s.substring(1), part1.substring(1), part2);
        if (!part2.equals("") && part2.startsWith(s.substring(0, 1)))
            return isMerge(s.substring(1), part1, part2.substring(1));
        return false;
    }
}
