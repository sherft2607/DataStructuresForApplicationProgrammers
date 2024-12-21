import java.util.Comparator;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 */

public class Frequency implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        // Compare frequencies in descending
        int frequencyComparison = Integer.compare(w2.getFrequency(), w1.getFrequency());

        // If frequencies are equal, compare alphabetically in natural order
        if (frequencyComparison == 0) {
            return w1.getWord().compareTo(w2.getWord());
        }

        return frequencyComparison;
    }
}
