/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Jeannette
 */
public class Node {
    public Node left;
    public Node right;
    private String word;
    private String positionsWord;

    int isBalanced;

    public Node(String content) {
        this.left = null;
        this.right = null;
        this.word = content;
        this.positionsWord = "";
        this.isBalanced = 0;
    }//cons

    public String getWord() {
        return word;
    }

    public void setWord(String chain) {
        this.word = chain;
    }

    public String getPositionsWord() {
        return positionsWord;
    }

    public void setPositionsWord(String positionsWord) {
        this.positionsWord = positionsWord;
    }

}
