package kata1;

public class SpinWords {
    public String spinWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder newSentence = new StringBuilder();
        for (String word : words) {
            if (word.length() >= 5) {
                StringBuilder toReverse = new StringBuilder(word);
                newSentence.append(toReverse.reverse()).append(" ");
            } else {
                newSentence.append(word).append(" ");
            }
        }
        return newSentence.toString().strip();
    }
}
