import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 17683 Data Structures for Application Programmers.
 *
 * Lab 7 Heaps and Java PriorityQueue class.
 * Find the median of integers using both maxHeap and minHeap.
 *
 * Andrew ID: sherft
 * @author Shandon Jude Herft
 */
public class FindMedian {
    /**
     * max heap data structure using PQ.
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(20, Collections.reverseOrder());
    /**
     * min heap data structure using PQ.
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(20);

    /**
     * Adds int values into two heaps.
     * It should maintain a condition that maxHeap.size() >= minHeap.size().
     * @param value int value to add
     */
    public void addNumber(int rand) {
        // Check if maxHeap and minHeap are of the same size
        if (maxHeap.size() == minHeap.size()) {
            if (minHeap.peek() != null && rand > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(rand);
            } else {
                maxHeap.offer(rand);
            }
        } else {
            if (rand < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(rand);
            } else {
                minHeap.offer(rand);
            }
        }
    }

    /**
     * Returns the median value of the added values.
     * If maxHeap and minHeap are of different sizes, then maxHeap must have one extra element.
     * @return median value
     */
    public double getMedian() {
        // TODO implement this method
        if (maxHeap.isEmpty()) {
            return 0.0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    /**
     * Test program to add int values and find median of those.
     * @param args arguments
     */
    public static void main(String[] args) {
        FindMedian tester = new FindMedian();
        tester.addNumber(6);
        tester.addNumber(4);
        tester.addNumber(3);
        tester.addNumber(10);
        tester.addNumber(12);

        // should print 6.0
        System.out.println(tester.getMedian());

        tester.addNumber(5);

        // should print 5.5
        System.out.println(tester.getMedian());

        tester.addNumber(7);
        tester.addNumber(8);

        // should print 6.5
        System.out.println(tester.getMedian());
    }

}
