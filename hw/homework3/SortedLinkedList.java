/**
 * 17683 Data Structures for Application Programmers.
 * Homework 3.
 * Sorted Linked List Implementation.
 *
 * Andrew ID: sherft
 * @author Shandon Jude Herft
 */
public class SortedLinkedList implements MyListInterface {

    // create a chain of nodes.
    private class Node {
        /**
         * data.
         */
        private String data;
        /**
         * string.
         */
        private Node next;

        Node(String info) {
            this.data = info;
            this.next = null;
        }
    }
    /**
     * head.
     */
    private Node head;

    // default.
    public SortedLinkedList() {
        head = null;
    }

    // take in unsorted array.
    public SortedLinkedList(String[] unsorted) {
        head = null; // empty list with head set to null.
        for (String word : unsorted) {
            add(word); //calls add on each element of array.
        }
    }

    // overrides add method to add in sorted order.
    @Override
    public void add(String value) {
        if (value == null || contains(value)) {
            return; // checking for null and duplicates.
        }
        head = addRecursive(head, value); // calls recursive add method.
    }

    // helper for add method.
    private Node addRecursive(Node node, String value) {
        if (node == null) {
            return new Node(value); // base case.
        }
        if (value.compareTo(node.data) < 0) {
            Node newNode = new Node(value);
            newNode.next = node; // if value is smaller.
            return newNode; //insert before current.
        } else {
            node.next = addRecursive(node.next, value); // if value is larger.
            return node; // move on to recursive.
        }
    }

    // method to display list in format required.
    @Override
    public void display() {
        System.out.print("[");
        displayRecursive(head); //calls recursive helper method.
        System.out.println("]");
    }

    // helper recursive method for display list.
    private void displayRecursive(Node node) {
        if (node != null) {
            System.out.print(node.data); // base case.
            if (node.next != null) {
                System.out.print(", ");
            }
            displayRecursive(node.next); //each string seperated by comma.
        }
    }

    // contains method.
    @Override
    public boolean contains(String key) {
        return containsRecursive(head, key); //calls recursive contains method.
    }

    // helper recursive method for contains.
    private boolean containsRecursive(Node node, String key) {
        if (node == null) {
            return false; //base case if empty.
        }
        if (node.data.equals(key)) {
            return true; //true if found.
        }
        return containsRecursive(node.next, key); // continue recursive calls.
    }

    // size method.
    @Override
    public int size() {
        return sizeRecursive(head); //calls recursive size method.
    }

    // helper recursive method for size.
    private int sizeRecursive(Node node) {
        if (node == null) {
            return 0; //base case if null.
        }
        return 1 + sizeRecursive(node.next); //continue with recursive calls.
    }

    // checks if the list is empty.
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // removed and return first string from list.
    @Override
    public String removeFirst() {
        if (head == null) {
            return null; //return null if empty.
        }
        String removed = head.data;
        head = head.next;
        return removed;
    }

    // removes and returns at a specified index.
    @Override
    public String removeAt(int index) {
        if (index < 0 || index >= size()) {
            throw new RuntimeException("Invalid index"); //validity check.
        }
        if (index == 0) {
            return removeFirst(); // calls removeFirst method.
        }
        return removeAtRecursive(head, index); //calls removeAtRecursive method.
    }

    // removeAtRecursive helper method.
    private String removeAtRecursive(Node node, int index) {
        if (index == 1) {
            String removed = node.next.data;
            node.next = node.next.next;
            return removed;
        }
        return removeAtRecursive(node.next, index - 1);
    }
}
