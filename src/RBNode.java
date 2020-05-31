/**
 * this class represents a RedBlack tree node.
 * @author Gal Cohen, Omri Berkovitch
 */
public class RBNode {
    private static final int BLACK = 0, RED = 1;
    private String text;
    private int value;
    private RBNode parent;
    private RBNode right;
    private RBNode left;
    private int color;

    /**
     * constructs a new RedBlack Tree Node. time complexity: O(length(d)).
     * @param d the word as value of the node.
     */
    public RBNode(String d) {
        text = d;
        value = 0;
        for (int i = 0; i < text.length(); i++) {
            value += (int) (text.charAt(i));
        }
        parent = null;
        left = null;
        right = null;
        color = BLACK;

    }

    /**
     * get the right son of the node. time complexity: O(1).
     * @return the right son of the node.
     */
    public RBNode getRight() {
        return this.right;
    }
    /**
     * get the left son of the node. time complexity: O(1).
     * @return the left son of the node.
     */
    public RBNode getLeft() {
        return this.left;
    }
    /**
     * get the parent of the node. time complexity: O(1).
     * @return the parent of the node.
     */
    public RBNode getParent() {
        return this.parent;
    }
    /**
     * get the color of the node. time complexity: O(1).
     * @return the color of the node.
     */
    public int getColor() {
        return this.color;
    }
    /**
     * get the text of the node. time complexity: O(1).
     * @return the text of the node.
     */
    public String getText() {
        return this.text;
    }
    /**
     * get the value of letter in node's text. time complexity: O(1).
     * @return the value of letter in node's text.
     */
    public int getValue() {
        return value;
    }

    /**
     * set node's color black. time complexity: O(1).
     */
    public void setBlack() {
        this.color = BLACK;
    }

    /**
     * set node's color red. time complexity: O(1).
     */
    public void setRed() {
        this.color = RED;
    }

    /**
     * checks if node's color is black. time complexity: O(1).
     * @return true if black, false if red.
     */
    public boolean isBlack() {
        return this.color == BLACK;
    }
    /**
     * checks if node's color is red. time complexity: O(1).
     * @return true if red, false if black.
     */
    public boolean isRed() {
        return !isBlack();
    }

    /**
     * set node's parent to given node. time complexity: O(1).
     * @param n the node to set as parent.
     */
    public void setParent(RBNode n) {
        this.parent = n;
    }
    /**
     * set node's left son to given node. time complexity: O(1).
     * @param n the node to set as left son.
     */
    public void setLeft(RBNode n) {
        this.left = n;
    }
    /**
     * set node's right son to given node. time complexity: O(1).
     * @param n the node to set as right son.
     */
    public void setRight(RBNode n) {
        this.right = n;
    }
    /**
     * set node's color to given color. time complexity: O(1).
     * if given value that not equals to black or red - does nothing.
     * @param c the node's new color.
     */
    public void setColor(int c) {
        if (c == 1)
            this.color = 1;
        if (c == 0)
            this.color = 0;
    }

    /**
     * copy satellite data of given node to this node. time complexity: O(1).
     * @param x the node to copy data from.
     */
    public void copyData(RBNode x) {
        this.text = x.text;
        this.value = x.value;
    }
}


