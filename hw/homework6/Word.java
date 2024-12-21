import java.util.HashSet;
import java.util.Set;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 * Represents a word with its frequency and the list of line numbers where it appears.
 */

public class Word implements Comparable<Word> {

    /** The word. */
    private String word;

    /** The set of line numbers. */
    private Set<Integer> index;

    /** The frequency of the word. */
    private int frequency;

    /**
     * Constructor with String parameter.
     * @param newWord initialize the Word object
     */
    public Word(String newWord) {
        this.word = newWord;
        this.index = new HashSet<>(); // Initialize as a HashSet
        this.frequency = 1; // Initialize frequency to 1
    }

    /**
     * Sets the word.
     * @param newWord the word to set
     */
    public void setWord(String newWord) {
        this.word = newWord;
    }

    /**
     * Gets the word.
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the frequency.
     * @param freq the frequency to set
     */
    public void setFrequency(int freq) {
        this.frequency = freq;
    }

    /**
     * Gets the frequency.
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Adds a line number to the index word.
     * @param line the line number to add
     */
    public void addToIndex(Integer line) {
        this.index.add(line);
    }

    /**
     * Gets the set of line numbers where it appears.
     * @return the set of line numbers
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * Returns a string representation.
     * @return a string in the format"
     */
    @Override
    public String toString() {
        return String.format(
            "%s %d %s",
            word, frequency, index.toString()
        );
    }

    /**
     * Compares two Word objects based on their alphabetical order.
     * @param other the other Word object
     * @return a negative integer, zero, or a positive integer as this word is less than, equal to, or greater than the specified word
     */
    @Override
    public int compareTo(Word other) {
        return this.word.compareTo(other.word);
    }
}
