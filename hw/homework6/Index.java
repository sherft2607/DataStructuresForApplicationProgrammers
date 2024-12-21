import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 */

public class Index {

    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null); // Default: natural alphabetical order
    }

    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> tree = new BST<>(comparator);
        File file = new File(fileName);

        if (!file.exists()) {
            System.err.println("File does not exist: " + fileName);
            return tree;
        }

        try (Scanner scanner = new Scanner(file, "latin1")) {
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\W+"); // Split by non-word characters

                for (String wordText : words) {
                    if (wordText.matches("^[a-zA-Z]+$")) { // Only consider valid words
                        Word word = comparator instanceof IgnoreCase
                                ? new Word(wordText.toLowerCase()) // Case-insensitive
                                : new Word(wordText);

                        Word existingWord = tree.search(word);
                        if (existingWord != null) {
                            existingWord.setFrequency(existingWord.getFrequency() + 1);
                            existingWord.addToIndex(lineNumber);
                        } else {
                            word.addToIndex(lineNumber);
                            tree.insert(word);
                        }
                    }
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }

        return tree;
    }

    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> tree = new BST<>(comparator);

        for (Word word : list) {
            if (word.getWord().matches("^[a-zA-Z]+$")) {
                if (comparator instanceof IgnoreCase) {
                    word.setWord(word.getWord().toLowerCase());
                }
                if (tree.search(word) == null) {
                    tree.insert(word);
                }
            }
        }

        return tree;
    }

    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> wordList = new ArrayList<>();
        for (Word word : tree) {
            wordList.add(word);
        }
        wordList.sort(new AlphaFreq());
        return wordList;
    }

    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> wordList = new ArrayList<>();
        for (Word word : tree) {
            wordList.add(word);
        }
        wordList.sort(new Frequency());
        return wordList;
    }

    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> sortedByFrequency = sortByFrequency(tree);
        ArrayList<Word> result = new ArrayList<>();

        if (!sortedByFrequency.isEmpty()) {
            int maxFrequency = sortedByFrequency.get(0).getFrequency();

            for (Word word : sortedByFrequency) {
                if (word.getFrequency() == maxFrequency) {
                    result.add(word);
                } else {
                    break;
                }
            }
        }

        return result;
    }
}
