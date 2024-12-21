import java.util.Comparator;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 */

public class AlphaFreq implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        // Compare alphabetically
        int alphabeticalComparison = w1.getWord().compareTo(w2.getWord());

        // If words are the same alphabetically, compare frequencies in ascending
        if (alphabeticalComparison == 0) {
            return Integer.compare(w1.getFrequency(), w2.getFrequency());
        }

        return alphabeticalComparison;
    }
}
