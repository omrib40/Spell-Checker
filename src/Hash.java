/**
 * this class represents a hash table of words.
 * @author Gal Cohen, Omri Berkovitch
 */
public class Hash {
    private static final double CONSTANT = (Math.sqrt(5) - 1) / 2;
    private List[] table;
    private int size;

    /**
     * constructs new hash table. time complexity: O(m)
     * @param m the size of the hash table to create.
     */
    public Hash(int m) {
        if (m < 0) {
            table = new List[0];
            size = 0;
        } else {
            table = new List[m];
            size = m;
        }
        for (int i = 0; i < table.length; i++) {
            table[i] = new List();
        }
    }
    /*
    this method calculates the h(k) of key 'k' by multiplication method. time complexity: O(1)
    constant value is 'A' described in (11.2) page no' 195 in the book.
     */
    private int hashFunc(int k) {
        return (int) (size * ((k * CONSTANT) % 1));
    }

    /**
     * this method adds new node to the hash table. time complexity: O(1).
     */
    public void add(Node n) {
        if (n != null) {
            int place = hashFunc(n.getValue());
            table[place].add(n);

        }
    }

    /**
     * this method search for node in the hash table with text equals as input text.
     * time complexity: anticipation O((1+(n/m))+length(text)).
     * @param text the text to search for.
     * @return the node with given text if found, null otherwise.
     */
    Node search(String text) {
        if (text.isEmpty())
                return null;
        int value = 0;
        for (int i = 0; i < text.length(); i++) {
            value += (int) (text.charAt(i));
        }
        int place = hashFunc(value);
            return table[place].search(text);
    }

    /**
     * this method adds paragraph as words new nodes to the hash table. time complexity: O(length(text)).
     * @param text the paragraph to adds the words into the hash table.
     */
    public void addData(String text) {
        String word = "";
        text = text + " ";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!(word.isEmpty())) {
                    Node n = new Node(word);
                    this.add(n);
                    word = "";
                }
            } else {
                word = word + text.charAt(i);
            }
        }
    }
    /*
    this method finds typos with exchanged letters,
    by runs on any letter in the word and tries to exchange it with the one next to it, if found in the hash table,
    found the right correction. otherwise didn't find the correction.
    time complexity: anticipated O(length(word) * (1 + n/m))
     */
    private String typoCase2(String word) { //EXCHANGE LETTERS
        String temp = "";
        for (int j = 0; j < word.length(); j++) {
            if (j < word.length() - 1) {
                temp = word.substring(0, j) + word.charAt(j + 1) + word.charAt(j) + word.substring(j + 2);
                Node n = this.search(temp);
                if (n != null) {
                    return temp;
                }
            }
        }
        return "";
    }
/*
this method looks for typos with removed letter, changed letter or added redundant letter.
runs on any letter in the word and first tries to delete it, next tries all the abc..
 by changing the letter od adds new one
any try it looks for the word created in the hash table, if found the word is corrected, else didn't find the correction.
time complexity: anticipated O(length(word)*26 * (1+(n/m))) = O(length(word) * (1+(n/m))).
 */
    private String typoCase1 (String word){ //REMOVE, CHANGE, ADD
        String temp = "";
        Node n = null;
        char ch = 'a';
        for (int j = 0; j<word.length(); j++){
            for (int t = -1; t < 26; t ++){
                if (t == -1) // REMOVE
                    temp = (word.substring(0,j) + word.substring(j+1));
                else
                    temp = (word.substring(0,j) +(char)((int)(ch)+t)+ word.substring(j+1)); // CHANGE
                n = this.search(temp);
                if (n != null) {
                    return temp;
                }
                else {
                    temp = (word.substring(0,j) +(char)((int)(ch)+t)+ word.substring(j)); // ADD
                    n = this.search(temp);
                    if (n != null) {
                        return temp;
                    }
                }
            }
        }
        return "";
    }

    /**
     * this method finds and print all the typos in the text left.
     * time complexity: anticipated O(length(text) * (1+(n/m))).
     * @param text all the text left to look for typos.
     */
    public void findTypos(String text) {
        String word = "";
        text = text + " ";
        String temp = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!(word.isEmpty())) {
                    temp = typoCase1(word);
                    if (!temp.equals("")) {
                        System.out.println("instead of: " + word + " maybe you meant: " + temp);
                        word = "";
                        continue;
                    }
                    temp = typoCase2(word);
                    if (!temp.equals("")) {
                        System.out.println("instead of: " + word + " maybe you meant: " + temp);
                        word = "";
                        continue;
                    }
                    word = "";
                }
            } else {
                word = word + text.charAt(i);
            }
        }
    }

    /**
     * this method returns a text - paragraph of words found in the hash table.
     * time complexity: anticipated O(length(text) * (1+n/m)).
     * @param text the paragraph of text to look for words.
     * @return a new text - paragraph of words that found in the hash table.
     */
    public String checkWords(String text) {
        String word = "";
        text = text + " ";
        String res = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!(word.isEmpty())) {
                    Node n = this.search(word);
                    if (n != null)
                        res += n.getText() + " ";
                    word = "";
                }
            } else {
                word = word + text.charAt(i);
            }
        }
        return res;
    }
}
