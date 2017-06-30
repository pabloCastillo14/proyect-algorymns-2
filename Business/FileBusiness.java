/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.FileData;
import Domain.Node;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author jeannette
 */
public class FileBusiness {
    private FileData treeData;

    public FileBusiness(String fileName) {
        this.treeData = new FileData(fileName);
    }//constructor

    public void saveTree(Node Node) throws FileNotFoundException {

        this.treeData.saveTree(Node);

    }//saveTree

    public String getTree() throws FileNotFoundException, IOException {

        return this.treeData.getTree();

    }//getTree

    public String backToText() throws IOException {
        return this.treeData.backToText();
    }

    public void saveSubTree(String chain) throws FileNotFoundException {
        this.treeData.saveSubTree(chain);
    }//saveSubTree
}
