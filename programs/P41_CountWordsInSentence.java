package programs;

/**
 * ============================================================
 * PROGRAM 41: Count Words in a Sentence
 * ============================================================
 * Problem: WAP to count total words in a sentence without using `split()`.
 * Handles multiple consecutive spaces, leading/trailing whitespace.
 * ============================================================
 */
public class P41_CountWordsInSentence {

    public static int countWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) return 0;

        int wordCount = 0;
        boolean inWord = false;

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            if (!Character.isWhitespace(ch)) {
                if (!inWord) {
                    wordCount++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }

        return wordCount;
    }

    public static void main(String[] args) {
        String[] samples = {
            "Java is awesome",
            "   Welcome    to   the    course   ",
            "OneWord",
            "",
            "   "
        };

        for (String s : samples) {
            System.out.printf("  \"%s\" -> Word Count: %d%n", s, countWords(s));
        }
    }
}
