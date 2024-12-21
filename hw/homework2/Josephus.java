import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 17683 Data Structures for Application Programmers.
 * Homework Assignment 2 Solve Josephus problem using different data structures
 * and different algorithms and compare running times.
 *
 * Andrew ID: sherft
 * @author Shandon Herft
 * I would use the playWithLLAt method to find the survivor.
 * Assuming there are many people in the circle, this method is the most efficient since it
 * avoids moving elements physically and uses indexing to calculate the next index
 * which is faster and is more scalable as the size of the circle increases.
 */
public class Josephus {
    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException("Invalid");
        }
        ArrayDeque<Integer> circle = new ArrayDeque<>();
        for (int i = 1; i <= size; i++) {
            circle.add(i); //person added to end of deque.
        }
        while (circle.size() > 1) {
            for (int i = 1; i < rotation; i++) {
                circle.add(circle.remove()); // removes person from the front and immediately adds them to the back of the line.
            }
            circle.remove(); // Remove the rotation person.
        }
        return circle.peek(); // retrieves the head person, and returns null if empty.
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException("Invalid");
        }

        LinkedList<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            circle.add(i); //person added to the list.
        }
        while (circle.size() > 1) {
        for (int i = 1; i < rotation; i++) {
            circle.add(circle.remove()); //removes person at front of queue then immediately adds them to the back of the line.
        }
        circle.remove(); // Remove the rotation person.
    }
    return circle.peek(); // retrieves the final person, the survivor.
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     *
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException("Invalid parameters");
        }

        LinkedList<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            circle.add(i); //person add to the list.
        }

        int eliminationIndex = 0; //start from first person in list.
        while (circle.size() > 1) {
            eliminationIndex = (eliminationIndex + rotation - 1) % circle.size(); //skip rotation-1, then mod if size exceeded.
            circle.remove(eliminationIndex); // remove the rotation person.
        }

        return circle.get(0); // retrieves the final person, the survivor.
    }
}
