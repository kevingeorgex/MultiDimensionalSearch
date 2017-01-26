/** CS 4V95.001, 5V81.001.  Java example
 *  Simple class that stores just a single int
 *   @author rbk
 */

public class Item implements Comparable<Item> {
    private int element;

    Item(int x) { element = x; }

    public int getItem() { return element; }

    public void setItem(int x) { element = x; }

    public int compareTo(Item another) { return this.element - another.element; }

    public String toString() { return Integer.toString(element); }
}
