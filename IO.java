/** CS 4V95.001, 5V81.001.  Java I/O example
    @author rbk
*/

import java.util.*;
import java.io.*;

public class IO {
    // If command line arg is found, it is assumed to be a file name.  
    // Otherwise read from stdin (console)
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        int s = in.nextInt();
        float t = in.nextFloat();

	System.out.println("s: " + s + " t: " + t);
    }
}
