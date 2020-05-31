/**
 * this class represents a list of words.
 * @author Gal Cohen, Omri Berkovitch
 */
public class List {
    private Node head;

    /**
     * adds new node to the list. time complexity: O(1).
     * @param n the node to add to the list.
     */
    public void add(Node n) {
        if (search(n.getText()) == null) {
            n.setNext(head);
            head = n;
        }
    }

    /**
     * search for node in the list with text as input text. time complexity: O(length(list)).
     * @param text the word to look for.
     * @return the node with given text if found, null otherwise.
     */
    public Node search(String text) {
        Node t = head;
        while (t != null) {
            if (t.getText().equals(text))
                return t;
            t = t.getNext();
        }
        return null;
    }
}
