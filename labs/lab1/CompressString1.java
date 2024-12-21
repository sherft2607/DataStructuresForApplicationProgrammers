/**
 * 17683 Data Structures for Application Programmers.
 * Lab 1 ArrayList time comparison and String manipulation.
 *
 * Andrew ID: sherft
 * @author Shandon Herft
 */
public class CompressString1 {
    /**
     * You may change the main method while you are working on.
     * But, when you submit your code, make sure to have the original code of the Main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        String str = "aabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcaabbccaabbccaabbccaabbccbbbaaabbbcccaaabbbcccaaabbbcccaaabbbccaaabbbcccaaabbbbccccaaaaabbbbbccccaaabbbcccaaabbbcccaaabbbcccaaabbbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaabbbcccaaannncccaaabbbcccaaabbbcccaaabbbcccaaabbbaaabbbccc";
        Stopwatch timer1 = new Stopwatch();
        System.out.println("output:" + compress(str));
        System.out.print("running time:" + timer1.elapsedTime() + " millisec");
    }

    /**
     * method to compress a string using String.
     * @param str input string that should have at least two characters
     * @return Compressed or original string depending on the size of the compressed
     */
    public static String compress(String str) {
        // initialize compressed string
        String compressed = "";

        // Your code here
        int alphabetCount = 0;

        //loop through each character in str
        for (int i = 0; i < str.length(); i++) {
            alphabetCount++; //count current alphabet

            //check if next character is different
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                //add alphabet and count to compressed
                compressed += str.charAt(i) + String.valueOf(alphabetCount);
                alphabetCount = 0; // reset count
            }
        }


        // check length and return accordingly
        // return final string that is compressed
        if (compressed.length() > str.length()) {
            return str;
        }
        return compressed;
    }
}
