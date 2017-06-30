/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import Business.FileBusiness;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Pablo Castillo
 */
public class Tree {
    public Node root;
    private int position;
    public ArrayList<Word> word;
    private String chain;

    public Tree() {

        this.word = new ArrayList<>();
        this.root = null;
        this.position = 0;
        this.chain = "";

    }//constructor

    public int balance(Node x) {

        if (x == null) {
            return -1;
        } else {
            return x.isBalanced;
        }
    }//balance

    public Node left(Node c) {

        Node aux = c.right;
        c.right = aux.left;
        aux.left = c;
        c.isBalanced = Math.max(balance(c.left), balance(c.right)) + 1;
        aux.isBalanced = Math.max(balance(aux.left), balance(aux.right));

        return aux;
    }// left

    public Node right(Node c) {

        Node aux = c.left;
        c.left = aux.right;
        aux.right = c;

        c.isBalanced = Math.max(balance(c.left), balance(c.right));
        aux.isBalanced = Math.max(balance(aux.left), balance(aux.right));

        return aux;
    }//rightRotation

    public Node leftRight(Node c) {

        Node temp;
        c.left = left(c.left);
        temp = right(c);

        return temp;

    }// leftR

    public Node rightLeft(Node c) {

        Node temp;
        c.right = right(c.right);
        temp = left(c);

        return temp;

    }//rightL

    public Node insert(Node node, Node subTree) {
        Node newParent = subTree;

        if (node.getWord().compareTo(subTree.getWord()) < 0) {
            if (subTree.left == null) {
                subTree.left = node;
                subTree.left.setPositionsWord(subTree.left.getPositionsWord() + this.position + "-");
                this.position++;
            } else {
                subTree.left = insert(node, subTree.left);

                if ((balance(subTree.left) - balance(subTree.right) == 2)) {
                    if (node.getWord().compareTo(subTree.left.getWord()) < 0) {
                        newParent = right(subTree);
                    } else {

                        newParent = leftRight(subTree);

                    }
                }
            }
        } else if (node.getWord().compareTo(subTree.getWord()) > 0) {
            if (subTree.right == null) {
                subTree.right = node;
                subTree.right.setPositionsWord(subTree.right.getPositionsWord() + this.position + "-");
                this.position++;
            } else {
                subTree.right = insert(node, subTree.right);

                if ((balance(subTree.left) - balance(subTree.right) == -2)) {
                    if (node.getWord().compareTo(subTree.right.getWord()) > 0) {
                        newParent = left(subTree);
                    } else {

                        newParent = rightLeft(subTree);

                    }
                }
            }
        } else {
            subTree.setPositionsWord(subTree.getPositionsWord() + this.position + "-");
            this.position++;
        }
        //updata bf
        if ((subTree.left == null) && (subTree.right != null)) {
            subTree.isBalanced = subTree.right.isBalanced + 1;
        } else if ((subTree.right == null) && (subTree.left != null)) {
            subTree.isBalanced = subTree.left.isBalanced + 1;
        } else {
            subTree.isBalanced = Math.max(balance(subTree.left), balance(subTree.right)) + 1;
        }

        return newParent;
    }//insertAVL

    public void insert(String c) {
        Node newLNode = new Node(c);
        if (this.root == null) {
            this.root = newLNode;
            this.root.setPositionsWord(this.root.getPositionsWord() + this.position + "-");////preguntar
            this.position++;
        } else {
            this.root = insert(newLNode, root);

        }//else-if
    }//insert

    public void saveNodes(Node r, String rute) throws FileNotFoundException {

        FileBusiness treeBusiness = new FileBusiness(rute);
        if (r != null) {

            if (r.getWord().length() < 4 || r.getPositionsWord().length() < 5) {
                shortWord(r);
            } else {
                treeBusiness.saveTree(r);
            }
            saveNodes(r.left, rute);
            saveNodes(r.right, rute);
        } //else-if
    }//saveNodes

    public void subTreeInsert(String rute) throws FileNotFoundException {

        Word[] arrayText = new Word[this.word.size()];

        for (int i = 0; i < arrayText.length; i++) {
            arrayText[i] = this.word.get(i);
        }//for i

        Word[] texts = insertion(arrayText);
        FileBusiness business = new FileBusiness(rute + "_a1.txt");
        for (int j = 0; j < texts.length; j++) {
            business.saveSubTree(texts[j].getName());
        }//for j

    }//subTreeInsert

    private void shortWord(Node node) {

        switch (node.getWord()) {
            case "porque":
                chain = "p";
                break;
            case "de":
                chain = "~";
                break;
            case "hasta":
                chain = "h";
                break;

            case "en":
                chain = "*";
                break;
            case "donde":
                chain = "d";
                break;

            case "la":
                chain = "<";
                break;
            case "esa":
                chain = "e";
                break;

            case "es":
                chain = ">";
                break;

            case "esta":
                chain = "t";
                break;

            case "el":
                chain = "+";
                break;
            case "las":
                chain = "/";
                break;
            case "los":
                chain = "-";
                break;
            case "por":
                chain = ".";
                break;
            case "que":
                chain = "_";
                break;
            case "con":
                chain = "#";
                break;
            case "del":
                chain = "d";
                break;
            case "sus":
                chain = "s";
                break;
            case "su":
                chain = "z";
                break;
            case "más":
                chain = "m";
                break;
            case "sea":
                chain = "v";
                break;
            case "ya":
                chain = "k";
                break;
            case "ni":
                chain = "n";
                break;
            case "al":
                chain = "l";
                break;
            case "así":
                chain = "&";
                break;
            case "si":
                chain = "i";
                break;
            case "fue":
                chain = "f";
                break;
            case "son":
                chain = "o";
                break;
            case "ahí":
                chain = "í";
                break;
            case "no":
                chain = "x";
                break;

            case "ha":
                chain = "$";
                break;
            case "han":
                chain = "g";
                break;
            case "mi":
                chain = "w";
                break;
            case "ese":
                chain = "p";
                break;
            case "para":
                chain = "r";
                break;
            case "pero":
                chain = "q";
                break;
            default:
                chain = node.getWord();
                break;
        }//switch

        String positions = node.getPositionsWord();
        positions = positions.substring(0, positions.length() - 1);
        String[] vPositions = positions.split("-");

        for (int i = 0; i < vPositions.length; i++) {
            Word backToText = new Word(chain, Integer.parseInt(vPositions[i]));

            this.word.add(backToText);

        }//for i

    }//makeList

    private Word[] insertion(Word[] backToTexts) {
        int p, j;
        Word aux;
        for (p = 1; p < backToTexts.length; p++) {
            aux = backToTexts[p];
            j = p - 1;
            while ((j >= 0) && (aux.getPosition() < backToTexts[j].getPosition())) {

                backToTexts[j + +1] = backToTexts[j];
                j--;
            }//while
            backToTexts[j + 1] = aux;
        }//for i

        return backToTexts;
    }//insertion

    public ArrayList<Node> search(Node node, String text) {
        char v[] = new char[text.length()];
        char v1[];
        boolean boo = true;
        int tam = 0;
        ArrayList<Node> list = new ArrayList<Node>();
        while (node != null) {
            for (int i = 0; i < text.length(); i++) {
                v[i] = text.charAt(i);
            }
            v1 = new char[node.getWord().length()];
            for (int i = 0; i < node.getWord().length(); i++) {
                v1[i] = node.getWord().charAt(i);
            }
            if (v1.length < v.length) {
                tam = node.getWord().length();
            } else {
                tam = text.length();
            }
            boo = true;
            for (int i = 0; i < tam; i++) {
                if ((v[i] == v1[i])) {
                } else {
                    boo = false;
                }
            }
            if (boo == true) {
                list.add(node);
            }
            if (node.getWord().compareTo(text) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }//else-if
        }//while
        return list;
    }//fsearch
}
