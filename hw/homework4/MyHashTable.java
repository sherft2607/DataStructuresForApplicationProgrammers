/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 4: HashTable Implementation with linear probing.
 *
 * Andrew ID:sherft
 * @author Shandon Herft
 */
public class MyHashTable implements MyHTInterface {
    
    /**
     * The DataItem array of the table.
     */
    private DataItem[] hashArray;

    private static final int INITIAL_CAPACITY = 10; // default capacity.
    private static final float LOAD_FACTOR = 0.5f; // default load factor.
    private static final DataItem DELETED = new DataItem("#DEL#");
    private static final int ALPHABET_SIZE = 27; // The number of "letters" (1-26 for a-z + 1 for empty)
    private int size;
    private int collision;

    /**
     * Constructor with no initial capacity. Initializes with default capacity.
     */
    public MyHashTable() {
        hashArray = new DataItem[INITIAL_CAPACITY];
        size = 0;
        collision = 0;
    }

    /**
     * Constructor with initial capacity.
     * @param capacity initial capacity of the hash table
     */
    public MyHashTable(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("Positive Capacity Only.");
        }
        hashArray = new DataItem[capacity];
        size = 0;
        collision = 0;
    }

    /**
     * Hash function with hashing using 27 (based on 26 letters) to hash the string.
     * The input string is hashed and then compressed.
     * @param input input string for hash value
     * @return hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = 0;
        for (int i = 0; i < input.length(); i++) {
            int character = input.charAt(i) - 'a' + 1; // 1-26 for a-z
            hashVal = (hashVal * ALPHABET_SIZE + character) % hashArray.length;
        }

        // hashVal is non-negative
        return (hashVal + hashArray.length) % hashArray.length;
    }

    /**
     * Helper method to check if the word is valid.
     * @param key input string to check for validity
     * @return boolean indicating if the word is valid
     */
    private boolean wordValid(String key) {
        if (key == null || key.isEmpty()) {
            return false; // Reject null or empty strings
        }

        // Check that all characters are alphabetic letters (a-z)
        for (int i = 0; i < key.length(); i++) {
            if (!Character.isLetter(key.charAt(i))) {
                return false; // Return false immediately if a non-letter character is found
            }
        }

        return true; // Return true, if all characters are valid letters.
    }

    /**
     * Rehash the table when the load factor exceeds.
     * Doubles the table size and rehashes existing items.
     */
    private void rehash() {
        // Log the rehashing process.
        System.out.printf("Rehashing %d items, new length is %d%n", size, nextPrime(hashArray.length * 2));
        // Reset size and collisions.
        size = 0;
        collision = 0;
        // Store the current hash table as a temp.
        DataItem[] oldHashArray = hashArray;
        // Allocate a new larger hash table.
        hashArray = new DataItem[nextPrime(oldHashArray.length * 2)];
        for (DataItem item : oldHashArray) {
            // Skip over null and deleted.
            if (item == null || item == DELETED) {
                continue;
            }
            // Compute the hash value.
            int index = hashFunc(item.value);
            // Start linear probing.
            while (hashArray[index] != null) {
                // If the item already exists, increment its frequency.
                if (hashArray[index].value.equals(item.value)) {
                    hashArray[index].frequency++;
                    break;
                }
                // Otherwise, increment the index for linear probing.
                index = (index + 1) % hashArray.length;
                collision++;  // Increment the collision counter each time.
            }
            // Place the item at the computed index.
            hashArray[index] = new DataItem(item.value);
            hashArray[index].frequency = item.frequency;
            // Update the size of the table as insertion occurs.
            size++;
        }
    }

    /**
     * Get the next greater prime number.
     * @param current current number
     * @return next prime number
     */
    private int nextPrime(int current) {
        current++; // Start checking from the next number
        while (!isPrime(current)) {
            current++;
        }
        return current;
    }

    /**
     * Check if a number is prime.
     * @param n number to check for prime
     * @return boolean indicating if the number is prime
     */
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Insert a value into the hash table, handling collisions.
     * If the word is already present, add its frequency.
     * If the word is not valid, ignore.
     * @param value the string value to insert
     */
    @Override
    public void insert(String value) {
        if (!wordValid(value)) {
            return;
        }
        int hashVal = hashFunc(value);

        // Handle collision using linear probing.
        int originalHashVal = hashVal;
        boolean tmpCollision = false;

        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
            if (hashArray[hashVal].value.equals(value)) {
                hashArray[hashVal].frequency++;  // Increment frequency if found.
                return;  // Exit after updating the frequency.
            } else {
                if (hashFunc(hashArray[hashVal].value) == hashFunc(value)) {
                    tmpCollision = true;  // Mark collision.
                }
                hashVal = (hashVal + 1) % hashArray.length;
            }
        }

        if (tmpCollision) {
            collision++;  // Count collisions
        }
        hashArray[hashVal] = new DataItem(value);
        size++;

        // Rehash if threshold exceeded.
        if ((1.0 * size / hashArray.length) > LOAD_FACTOR) {
            rehash();
        }
    }

    /**
     * Get the number of collisions during insertions.
     * @return number of collisions
     */
    @Override
    public int numOfCollisions() {
        return collision;
    }

    /**
     * Get the hash value of a string.
     * @param value the string value to hash
     * @return hash value of the string
     */
    @Override
    public int hashValue(String value) {
        return hashFunc(value);
    }

    /**
     * Display the table contents, showing the value and frequency.
     */
    @Override
    public void display() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] == DELETED) {
                line.append(hashArray[i].value).append(" ");
            } else if (hashArray[i] != null) {
                line.append("[" + hashArray[i].value + ", " + hashArray[i].frequency + "] ").append(" ");
            } else {
                line.append("**").append(" ");
            }
        }
        System.out.println(line.toString());
    }

    /**
     * Check if the table contains a specific value.
     * @param key the string key to check for existence
     * @return boolean indicating if the key exists in the table
     */
    @Override
    public boolean contains(String key) {
        if (!wordValid(key)) {
            return false;
        }
        int hashVal = hashFunc(key);

        // Linear probing to find the key
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].value.equals(key)) {
                return true;
            }
            hashVal = (hashVal + 1) % hashArray.length;  // Linear probing.
        }
        return false;
    }

    /**
     * Show the frequency of a specific key.
     * @param key the string key whose frequency is to be shown
     * @return the frequency of the key, or 0 if not found
     */
    @Override
    public int showFrequency(String key) {
        if (!wordValid(key)) {
            return 0;
        }
        if (!contains(key)) {
            return 0;
        }

        int hashVal = hashFunc(key);
        while (!hashArray[hashVal].value.equals(key)) {
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return hashArray[hashVal].frequency;
    }

    /**
     * Remove a key from the table.
     * @param key the string key to remove
     * @return the removed key, or null if not found
     */
    @Override
    public String remove(String key) {
        if (!wordValid(key)) {
            return null;
        }

        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].value.equals(key)) {
                String removedValue = hashArray[hashVal].value;
                hashArray[hashVal] = DELETED;  // Mark as deleted.
                size--;
                return removedValue;
            }
            hashVal = (hashVal + 1) % hashArray.length;
        }
        return null; // If not found.
    }
}
