import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 * Class Similarity:
 * - Data Structure: HashMap<String, Integer>.
 *   Purpose: To store the frequency of words in a text or document.
 *   Reason: HashMap allows quick storage and retrieval of word counts
 *           with average O(1) time complexity.
 */
public class Similarity {
    /**
     * Stores the frequency of each word in the text or document.
     */
    private final Map<String, Integer> wordFrequencyMap;
    /**
     * Stores the number of lines in the document.
     */
    private int numOfLines;
    /**
     * Constructor that processes a single string.
     *
     * @param text Input text string.
     */
    public Similarity(String text) {
        wordFrequencyMap = new HashMap<>();
        if (text != null && !text.isEmpty()) {
            String[] words = text.split("\\W+");
            for (String word : words) {
                if (word.matches("^[a-zA-Z]+$")) {
                    word = word.toLowerCase();
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }
            this.numOfLines = 1;
        }
    }
    /**
     * Constructor that processes a file.
     *
     * @param file Input file.
     */
    public Similarity(File file) {
        wordFrequencyMap = new HashMap<>();
        this.numOfLines = 0;
        if (file != null && file.exists()) {
            try (Scanner scanner = new Scanner(file, "latin1")) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("\\W+");
                    for (String word : words) {
                        if (word.matches("^[a-zA-Z]+$")) {
                            word = word.toLowerCase();
                            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                        }
                    }
                    this.numOfLines++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + file.getName());
            }
        }
    }
    /**
     * Returns the total number of words in the text or document.
     *
     * @return Total number of words as BigInteger.
     */
    public BigInteger numOfWords() {
        BigInteger totalWords = BigInteger.ZERO;
        for (int frequency : wordFrequencyMap.values()) {
            totalWords = totalWords.add(BigInteger.valueOf(frequency));
        }
        return totalWords;
    }
    /**
     * Returns the number of words (without duplicates).
     *
     * @return Number of words as an integer.
     */
    public int numOfWordsNoDups() {
        return wordFrequencyMap.size();
    }
    /**
     * Calculates the Euclidean norm (magnitude) of the frequency vector.
     *
     * @return Euclidean norm.
     */
    public double euclideanNorm() {
        double sum = 0.0;
        for (int frequency : wordFrequencyMap.values()) {
            sum += Math.pow(frequency, 2);
        }
        return Math.sqrt(sum);
    }
    /**
     * Computes the dot product of two frequency vectors.
     *
     * @param otherMap The frequency map of the other text.
     * @return The dot product of the two frequency vectors.
     */
    public double dotProduct(Map<String, Integer> otherMap) {
        if (otherMap == null || otherMap.isEmpty()) {
            return 0.0;
        }
        double result = 0.0;
        Map<String, Integer> smaller = (wordFrequencyMap.size() < otherMap.size()) ? wordFrequencyMap : otherMap;
        Map<String, Integer> larger = (smaller == wordFrequencyMap) ? otherMap : wordFrequencyMap;
        for (Map.Entry<String, Integer> entry : smaller.entrySet()) {
            String word = entry.getKey();
            if (larger.containsKey(word)) {
                result += entry.getValue() * larger.get(word);
            }
        }
        return result;
    }
    /**
     * Computes the cosine similarity (or distance) between two texts/documents.
     *
     * @param otherMap The frequency map of the other text.
     * @return The cosine distance.
     */
    public double distance(Map<String, Integer> otherMap) {
        if (otherMap == null || otherMap.isEmpty() || this.wordFrequencyMap.isEmpty()) {
            return Math.PI / 2;
        }
        double dotProduct = dotProduct(otherMap);
        if (dotProduct == 0.0) {
            return Math.PI / 2;
        }
        double magnitudeThis = this.euclideanNorm();
        double magnitudeOther = 0.0;
        for (int frequency : otherMap.values()) {
            magnitudeOther += Math.pow(frequency, 2);
        }
        magnitudeOther = Math.sqrt(magnitudeOther);
        if (magnitudeThis == 0 || magnitudeOther == 0) {
            return Math.PI / 2;
        }
        double cosineSimilarity = dotProduct / (magnitudeThis * magnitudeOther);
        cosineSimilarity = Math.max(-1.0, Math.min(1.0, cosineSimilarity));
        return Math.acos(cosineSimilarity);
    }
    /**
     * Returns the word frequency map.
     *
     * @return Word frequency map.
     */
    public Map<String, Integer> getMap() {
        return new HashMap<>(wordFrequencyMap); // Return a copy ensuring immutability
    }
    /**
     * Returns the number of lines in the text or document.
     *
     * @return Number of lines.
     */
    public int numOfLines() {
        return this.numOfLines;
    }
}
