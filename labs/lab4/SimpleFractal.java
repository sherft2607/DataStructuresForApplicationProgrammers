/**
 * 17683 Data Structures for Application Programmers.
 * Lab 4 Number of Collisions Comparison and
 * Drawing a simple fractal of n squares with recursion.
 *
 * Andrew ID: sherft
 * @author Shandon Jude Herft
 */
public class SimpleFractal {

    /**
     * Test program to draw n squares.
     * @param args arguments
     */
    public static void main(String[] args) {
        /*
         * canvas width and height are both 1.0.
         * initial call to create 10 squares.
         * Do not change the following initial call
         */
        fractal(10, 0, 0, 0.5);
    }

    /**
     * recursive method to draw a fractal of n number of squares.
     * @param n number of squares
     * @param x the x-coordinate of the left-bottom corner of the square
     * @param y the y-coordinate of the left-bottom corner of the square
     * @param length side length of a square
     */
    public static void fractal(int n, double x, double y, double length) {
        // Base case: stop when n reaches 0
        if (n == 0) {
            return;
        }

        // Draw the current square
        StdDraw.filledSquare(x, y, length);

        // Recursive calls to draw smaller squares in four corners
        double newLength = length / 2;
        fractal(n - 1, x + length, y + length, newLength);
        fractal(n - 1, x - length, y + length, newLength);
        fractal(n - 1, x + length, y - length, newLength);
        fractal(n - 1, x - length, y - length, newLength);
    }

}
