/**
 * this class represents a RedBlack tree of words nodes.
 * @author Gal Cohen, Omri Berkovitch
 */
public class RBTree {
    private RBNode root;
    private RBNode nil;

    /**
     * constructs new RedBlack Tree. time complexity: O(1).
     */
    public RBTree() {
        nil = new RBNode("");
        root = nil;
    }

    /*
     implementation to Rotate Left as described in book page no' 234. time complexity: O(1).
     */
    private void rotateLeft(RBNode x) {
        RBNode y = x.getRight();
        x.setRight(y.getLeft());
        if (y != nil)
            y.getLeft().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == nil)
            root = y;
        else {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    /*
     implementation to Rotate Right, same as left described in book page no' 234. time complexity: O(1).
     */
    private void rotateRight(RBNode x) {
        RBNode y = x.getLeft();
        x.setLeft(y.getRight());
        if(y != nil)
            y.getRight().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == nil)
            root = y;
        else {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(y);
            else {
                x.getParent().setRight(y);
            }
        }
        y.setRight(x);
        x.setParent(y);
    }

    /*
     implementation to insert fixup described in book page no' 236. time complexity: O(log(n)).
     */
    private void insertFixUp(RBNode z) {
        while (z.getParent().isRed()) {
            if (z.getParent() == z.getParent().getParent().getLeft()) { //CASE1
                RBNode y = z.getParent().getParent().getLeft();
                if (y.isRed()) { //CASE1.1
                    z.getParent().setBlack();
                    y.setBlack();
                    z.getParent().getParent().setRed();
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) { //CASE1.2
                        z = z.getParent();
                        rotateLeft(z);
                    }
                    z.getParent().setBlack(); //CASE1.3
                    z.getParent().getParent().setRed();
                    rotateRight(z.getParent().getParent());
                }
            } else {//CASE2
                RBNode y = z.getParent().getParent().getLeft();
                if (y.isRed()) {//CASE2.1
                    z.getParent().setBlack();
                    y.setBlack();
                    z.getParent().getParent().setRed();
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {//CASE2.2
                        z = z.getParent();
                        rotateRight(z);
                    }
                    z.getParent().setBlack();//CASE2.3
                    z.getParent().getParent().setRed();
                    rotateLeft(z.getParent().getParent());
                }
            }
        }
        root.setBlack();
    }

    /**
     * insert new node to the RedBlack tree. time complexity: O(log(n)).
     * @param z the node to insert to the tree.
     */
    //implementation to RedBlack insert as described in book page no' 236.
    public void insert(RBNode z) {
        if (this.search(z.getText()) != null) {
            return;
        }
        RBNode y = nil;
        RBNode x = this.root;
        while (x != nil) {
            y = x;
            if (z.getValue() < x.getValue()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setParent(y);
        if (y == nil) {
            root = z;
        } else {
            if (z.getValue() < y.getValue()) {
                y.setLeft(z);
            } else {
                y.setRight(z);
            }
        }
        z.setLeft(nil);
        z.setRight(nil);
        z.setRed();
        this.insertFixUp(z);
    }

    /*
     implementation to tree Min as described in book page no' 217. time complexity: O(log(n)), because RB Tree is balanced.
     */
    private RBNode treeMin(RBNode z) {
        while (z.getLeft() != nil) {
            z = z.getLeft();
        }
        return z;
    }
    /*
     implementation to successor as described in book page no' 218. time complexity: O(log(n)), because RB Tree is balanced.
     */
    private RBNode successor(RBNode z) {
        if (z.getRight() != nil)
            return treeMin(z.getRight());
        RBNode y = z.getParent();
        while (y != nil && z == y.getRight()) {
            z = y;
            y = y.getParent();
        }
        return y;
    }

    /**
     * delete the given node from the tree. time complexity: O(log(n)).
     * @param z the node to delete.
     */
    //implementation to RB-DELETE as described in book page no' 236.
    public void delete(RBNode z) {
        RBNode y = nil;
        RBNode x = nil;
        if (z.getLeft() == nil || z.getRight() == nil)
            y = z;
        else y = successor(z);
        if (y.getLeft() != nil)
            x = y.getLeft();
        else
            x = y.getRight();
        x.setParent(y.getParent());
        if (y.getParent() == nil)
            root = x;
        else if (y == y.getParent().getLeft())
            y.getParent().setLeft(x);
        else
            y.getParent().setRight(x);
        if (y != z)
            z.copyData(y);
        if (y.isBlack())
            deleteFixup(x);
    }

    /*
     * implementation of delete fixup as described in book page no' 236. time complexity: O(log(n)).
     */
    private void deleteFixup(RBNode x) {
        RBNode w = nil;
        while (x != nil && x != root && x.isBlack()) {
            if (x == x.getParent().getLeft()) {//CASE1
                w = x.getParent().getRight();
                if (w.isRed()) { //CASE1.1
                    w.setBlack();
                    x.getParent().setRed();
                    rotateLeft(x.getParent());
                }
                if (w!= nil && w.getLeft().isBlack() && w.getRight().isBlack()) { //CASE1.2
                    w.setRed();
                    x = x.getParent();
                } else {
                    if (w != nil && w.getRight().isBlack()) {//CASE1.3
                        w.getLeft().setBlack();
                        w.setRed();
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor()); //CASE1.4
                    x.getParent().setBlack();
                    if (w != nil)
                        w.getRight().setBlack();
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else { //CASE2
                w = x.getParent().getLeft();
                if (w.isRed()) { //CASE2.1
                    w.setBlack();
                    x.getParent().setRed();
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w != nil && w.getRight().isBlack() && w.getLeft().isBlack()) { //CASE2.2
                    w.setRed();
                    x = x.getParent();
                } else {
                    if (w!= nil && w.getLeft().isBlack()) { //CASE2.3
                        w.getRight().setBlack();
                        w.setRed();
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor()); //CASE2.4
                    x.getParent().setBlack();
                    if(w!= nil)
                        w.getLeft().setBlack();
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setBlack();
    }
    /*
    implementation of search as described in book page no' 216. time complexity: O(log(n)), because RB tree is balanced.
     */
    private RBNode search(RBNode t, String text, int value) {
        if (t == nil)
            return nil;
        if (t.getText().equals(text))
            return t;
        else if (t.getValue() > value)
            return search(t.getLeft(), text, value);
        else
            return search(t.getRight(), text, value);

    }

    /**
     * search for text in the tree. time complexity: O(log(n)).
     * @param text the text to search for in the tree.
     * @return the node in the tree if text found, null otherwise.
     */
    public RBNode search(String text) {
        if (text.isEmpty())
            return null;
        int value = 0;
        for (int i = 0; i < text.length(); i++) {
            value += (int) (text.charAt(i));
        }
        RBNode s = search(root, text, value);
        if (s != nil)
            return s;
        return null;
    }

    /**
     * in-order scan of the tree. time complexity: O(n).
     * @return paragraph of the node's words in in-order scan.
     */
    public String inOrder() {
        if (root == nil) {
            return ("there is no nodes in this tree");
        }
        return inOrder(root, "");
    }
/*
implementation of in-order as described in book page no' 214. time complexity: O(log(n)), because RB tree is balanced.
 */
    private String inOrder(RBNode t, String str) {
        if (t == nil) {
            return str;
        }
        str = inOrder(t.getLeft(), str);
        str = str + t.getText() + " ";
        str = inOrder((t.getRight()), str);
        return str;
    }

    /**
     * deletes the words from the tree that in the given text - paragraph and found in the tree. time complexity: O(length(text) * log(n)).
     * @param text the words to look for in the tree to delete if found.
     */
    public void deleteWords(String text) {
        String word = "";
        text = text + " ";
        RBNode n = null;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!(word.isEmpty())) {
                    n = this.search(word);
                    if (n != null)
                        this.delete(n);
                    word = "";
                }
            } else {
                word = word + text.charAt(i);
            }
        }
    }

    /**
     * adds new words to the tree as nodes. time complexity: O(length(text) * log(n)).
     * @param text words as paragraph to add to the tree.
     */
    public void addData(String text) {
        String word = "";
        text = text + " ";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!(word.isEmpty())) {
                    RBNode n = new RBNode(word);
                    this.insert(n);
                    word = "";
                }
            } else {
                word = word + text.charAt(i);
            }
        }
    }
}
