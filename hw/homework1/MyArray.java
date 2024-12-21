/**
 * @author sherft Shandon Herft
 */
public class MyArray {
    /**
     * array.
     */
    private String[] array;
    /**
     * size.
     */
    private int size;
    /**
     * DEFAULT_CAPACITY.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default constructor initializes the array with a length of 10.
     */
    public MyArray() {
        array = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructor that initializes the array with a given initial capacity.
     *
     * @param initialCapacity The initial capacity of the array.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public MyArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        array = new String[initialCapacity];
        size = 0;
    }

    /**
     * Adds a word to the array if it is non-null, non-empty, and alphabetic.
     * Doubles the array capacity when full.
     *
     * @param text The word to add.
     */
    public void add(String text) {
        if (text == null || text.isEmpty() || !text.matches("[a-zA-Z]+")) {
            return;
        }
        if (size == array.length) {
            int newArraySize;
            if (array.length == 0) {
                newArraySize = 1;
            } else {
                newArraySize = array.length * 2;
            }
            String[] newArray = new String[newArraySize];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size] = text;
        size++;
    }

    /**
     * @param key The word to search for.
     * @return true if found, false otherwise.
     */
    public boolean search(String key) {
        if (key == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return The number of words in the array.
     */
    public int size() {
        return size;
    }

    /**
     * @return The current length of the array.
     */
    public int getCapacity() {
        return array.length;
    }

    /**
     * Displays all words in the array on a single line.
     */
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Removes duplicate words from the array.
     * Adjusts the size to unique words only.
     */
    public void removeDups() {
        int uniqueCount = 0;
        for (int i = 0; i < size; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (array[i].equals(array[j])) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                array[uniqueCount++] = array[i];
            }
        }
        size = uniqueCount;
    }
}
