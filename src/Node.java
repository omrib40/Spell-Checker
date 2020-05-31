/**
 * this class represents a node of word.
 * @author Gal Cohen, Omri Berkovitch
 */
public class Node {
    private String text;
    private int value;
    private Node next;

    /**
     * Constructs a List's node. time complexity: O(length(d))
     * @param d the string of the node.
     */
    public Node(String d) {
        text = d;
        value = 0;
        for (int i = 0; i < text.length(); i++) {
            value += (int) (text.charAt(i));
        }
        next = null;

    }

    /**
     * returns the next node on the list. time complexity: O(1)
     * @return the next node on the list.
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * this method returns the text of the node. time complexity: O(1)
     * @return the text of the node.
     */
    public String getText() {
        return this.text;
    }

    /**
     * this method returns the value of the letters in the node. time complexity: O(1)
     * @return the value of the letters in the node.
     */
    public int getValue() {
        return value;
    }

    /**
     * set the next node to the input node. time complexity: O(1).
     * @param n the node to set his next value.
     */
    public void setNext(Node n) {
        this.next = n;
    }
}
