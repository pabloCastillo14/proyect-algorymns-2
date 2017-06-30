/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Domain.Node;
import Domain.Word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author jeannette
 */
public class FileData {
    private String fileName;
    private String chain;

    public FileData(String fileName) {
        this.fileName = fileName;
        this.chain = "";
    }//constructor

    public void saveTree(Node node) throws FileNotFoundException {//guardo un arbol en el archivo, se guarda desordenado
        File file = new File(this.fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        String temp = node.getPositionsWord();
        temp = temp.substring(0, temp.length() - 1);
        printStream.println(node.getWord() + ":" + temp);
    }//saveTree

    public String getTree() throws FileNotFoundException, IOException {
        String result = "";
        File file = new File(this.fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {

            result += line + "&";

        }//while
        result = result.substring(0, result.length() - 1);
        //JOptionPane.showMessageDialog(null, result);
        return result;
    }//getTree

    public String backToText() throws IOException {
        String splitBar = this.getTree();
        String[] splitBars = splitBar.split("&");
        ArrayList<Word> bt = new ArrayList<>();

        for (int i = 0; i < splitBars.length; i++) {
            //JOptionPane.showMessageDialog(null,splitBars[i]);
            String[] temp = splitBars[i].split(":");
            String tempWord = temp[0], tempNumber = temp[1];
            String[] tempNumbers = tempNumber.split("-");

            for (int j = 0; j < tempNumbers.length; j++) {
                bt.add(new Word(tempWord, Integer.parseInt(tempNumbers[j])));
            }//forTempNumber

        }//forSplitBar

        Word[] finalResult = new Word[bt.size()];

        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = bt.get(i);
        }//for
        ArrayList<String> list = backToSubTree();
        int p, j;
        Word aux;
        for (p = 1; p < finalResult.length; p++) {
            aux = finalResult[p];
            j = p - 1;
            while ((j >= 0) && (aux.getPosition() < finalResult[j].getPosition())) {

                finalResult[j + 1] = finalResult[j];
                j--;
            }
            finalResult[j + 1] = aux;
        }//for i

        Word[] btt = new Word[list.size()];
        for (int i = 0; i < list.size(); i++) {
            btt[i] = new Word(list.get(i), i);
        }//for

        Word[] backToTexts1 = new Word[finalResult.length + btt.length];
        int cont = 0;
        String result = "";

        for (int i = 0; i < finalResult.length; i++) {
            backToTexts1[finalResult[i].getPosition()] = finalResult[i];
        }//for i
        for (int k = 0; k < backToTexts1.length; k++) {
            if (backToTexts1[k] == null) {
                backToTexts1[k] = btt[cont];
                cont++;
            }//if
            String temp = ashortWords(backToTexts1[k].getName());
            result += temp + " ";
        }//for j

        return result;
    }//backToText

    public void saveSubTree(String chain) throws FileNotFoundException {
        File file = new File(this.fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(chain);
    }//saveSubTree

    public ArrayList<String> backToSubTree() throws FileNotFoundException, IOException {//quitar

        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(this.fileName + "_a1.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            arrayList.add(line);
        }//while

        return arrayList;
    }//backToSubTree

    private String ashortWords(String word) {

        switch (word) {
            case "~":
                chain = "de";
                break;
            case "*":
                chain = "en";
                break;
            case "<":
                chain = "la";
                break;
            case ">":
                chain = "es";
                break;
            case "+":
                chain = "el";
                break;
            case "/":
                chain = "las";
                break;
            case "-":
                chain = "los";
                break;
            case ".":
                chain = "por";
                break;
            case "_":
                chain = "que";
                break;
            case "#":
                chain = "con";
                break;
            case "d":
                chain = "del";
                break;
            case "s":
                chain = "sus";
                break;
            case "z":
                chain = "su";
                break;
            case "m":
                chain = "más";
                break;
            case "v":
                chain = "sea";
                break;
            case "k":
                chain = "ya";
                break;
            case "n":
                chain = "ni";
                break;
            case "l":
                chain = "al";
                break;
            case "&":
                chain = "así";
                break;
            case "i":
                chain = "si";
                break;
            case "f":
                chain = "fue";
                break;
            case "o":
                chain = "son";
                break;
            case "í":
                chain = "ahí";
                break;
            case "x":
                chain = "no";
                break;
            case "t":
                chain = "esa";
                break;
            case "$":
                chain = "ha";
                break;
            case "g":
                chain = "han";
                break;
            case "w":
                chain = "mi";
                break;
            case "p":
                chain = "ese";
                break;
            case "r":
                chain = "para";
                break;
            case "q":
                chain = "pero";
                break;
            default:
                chain = word;
                break;
        }
        return chain;
    }//shortWord
}
