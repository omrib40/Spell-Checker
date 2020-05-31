/**
 * this project creates a dictionary of given words and gets a paragraph and
 * returns words with typos and suggested correction.
 * @author Gal Cohen, Omri Berkovitch
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Driver {
    public static void main(String[] args) {
        String dict = readFile("dict.txt"); // reads the dict file
        String text = readFile("text.txt"); // reads the text file.
        Hash table = new Hash(sizeDict(dict)/3); // creates new Hash table with size proportional to dict size.
        RBTree tree = new RBTree(); // creates new RedBlack Tree.
        table.addData(dict); // adds the words from dict to has as dictionary
        tree.addData(text); // adds the words in text to RedBlack Tree.
        String res = tree.inOrder(); //get the words in the tree.
        res = table.checkWords(res); //find the words in the tree that in the dictionary too.
        tree.deleteWords(res); // delete the words that found in dictionary from the tree.
        res = tree.inOrder(); //get all the words left in the tree.
        System.out.println(res); // prints the words left in the tree that wasn't find in the dictionary.
        System.out.println("*****CORRECTIONS*****");
        table.findTypos(res); // find and print all typos and suggested corrections to the words left in tree.
    }
    /*
    return the size of the dict (number of words). time complexity: O(length(text)).
    iterates on letters in text and when space (" ") is found, count new word.
     */
    private static int sizeDict(String text){
        text += " ";
        String word = "";
        int counter = 0;
        for (int i = 0; i<text.length(); i++){
            if(text.charAt(i) == ' '){
                if(!word.isEmpty()){
                    counter ++;
                    word = "";
                }
            }
            else
                word += text.charAt(i);
        }
        return counter;
    }
    /*
    finds the path of the project and adds the resources folder. time complexity: O(1).
     */
    private static String loadResource(String fileName) {
        String pwd = Paths.get(".").toAbsolutePath().toString();
        pwd = pwd.substring(0,pwd.length()-1);
        String absPath = pwd + "\\Resources\\" + fileName;
        return absPath;
    }
/*
reads txt file into String and returns the string. time complexity: O(1).
 */
    private static String readFile(String fileName){
        String res = "";
        try (FileReader reader = new FileReader(loadResource(fileName));
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                res +=line;
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return res;
    }
}
