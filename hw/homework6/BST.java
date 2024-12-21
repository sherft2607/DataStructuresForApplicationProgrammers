import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

/**
 * Andrew ID: sherft.
 * @author Shandon Jude Herft.
 * Represents a binary search tree.
 * @param <T> the type of elements stored
 */

public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {

    /** The root node. */
    private Node<T> root;

    /** The comparator used for ordering. */
    private Comparator<T> comparator;

    /**
     * Default constructor that creates an empty BST.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor that creates a BST with a given comparator.
     * @param comp the comparator used to order
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Returns the comparator used to order.
     * @return the comparator
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Gets the root element.
     * @return the root element, or null
     */
    public T getRoot() {
        return root != null ? root.data : null;
    }

    /**
     * Gets the height.
     * @return the height
     */
    public int getHeight() {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        return getHeight(root);
    }

    /**
     * Calculates the height of the subtree.
     * @param node the node to calculate
     * @return the height
     */
    private int getHeight(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Gets the total number of nodes.
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }

    /**
     * Calculates the total number of nodes in the subtree.
     * @param node the node to calculate
     * @return the number of nodes
     */
    private int getNumberOfNodes(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + getNumberOfNodes(node.left) + getNumberOfNodes(node.right);
    }

    /**
     * Searches for an element.
     * @param toSearch the element to search
     * @return the element if found, or null if not
     */
    @Override
    public T search(T toSearch) {
        return search(root, toSearch);
    }

    /**
     * Searches for an element in the subtree.
     * @param node the node to search the subtree
     * @param toSearch the element to search
     * @return the element if found, or null if not found
     */
    private T search(Node<T> node, T toSearch) {
        if (node == null) {
            return null;
        }
        int cmp = compare(toSearch, node.data);
        if (cmp == 0) {
            return node.data;
        } else if (cmp < 0) {
            return search(node.left, toSearch);
        } else {
            return search(node.right, toSearch);
        }
    }

    /**
     * Inserts an element.
     * @param toInsert the element to insert
     */
    @Override
    public void insert(T toInsert) {
        root = insert(root, toInsert);
    }

    /**
     * Inserts an element into the subtree.
     * @param node the node to insert the element
     * @param toInsert the element to insert
     * @return the new root of the subtree
     */
    private Node<T> insert(Node<T> node, T toInsert) {
        if (node == null) {
            return new Node<>(toInsert);
        }
        int cmp = compare(toInsert, node.data);
        if (cmp < 0) {
            node.left = insert(node.left, toInsert);
        } else if (cmp > 0) {
            node.right = insert(node.right, toInsert);
        }
        return node;
    }

    /**
     * Returns an iterator that iterates over the elements in-order.
     * @return the in-order iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator(root);
    }

    /**
     * Compares two elements using the comparator.
     * @param obj1 the first element
     * @param obj2 the second element
     * @return the result
     */
    private int compare(T obj1, T obj2) {
        if (comparator != null) {
            return comparator.compare(obj1, obj2);
        }
        return obj1.compareTo(obj2);
    }

    /**
     * Represents a node.
     * @param <T> the type of the element
     */
    private static class Node<T> {
        /**
         * data in the node.
         */
        private T data;
        /**
         * left child of the node.
         */
        private Node<T> left;
        /**
         * right child of the node.
         */
        private Node<T> right;
        /**
         * Creates a new node with the specified data and no children.
         * @param d the data to store in this node
         */
        Node(T d) {
            this(d, null, null);
        }

        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }
    }

    /**
     * An iterator that iterates in-order.
     */
    private class InOrderIterator implements Iterator<T> {
        /**
         * The stack used for simulating recursion during in-order traversal.
         */
        private Stack<Node<T>> stack = new Stack<>();
        /**
         * Creates an in-order iterator.
         * @param startNode the starting node for traversal
         */
        InOrderIterator(Node<T> startNode) {
            pushLeft(startNode);
        }


        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            Node<T> current = stack.pop();
            if (current.right != null) {
                pushLeft(current.right);
            }
            return current.data;
        }
    }
}
