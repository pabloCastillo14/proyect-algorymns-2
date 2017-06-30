/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Node;
import Domain.Tree;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Pablo Castillo
 */
public class SearchWord extends JFrame implements ActionListener {

    private JLabel jlbSearch;
    private JTextField jtfSearch;
    private JButton jbtnSearch;
    private Tree tree;
    private String text;
    private JTextArea jtaPalabrasBuscadas;

    public SearchWord(Tree aVLtree) {//recibo el arbol puro
        super("Buscar Palabra");
        this.tree = aVLtree;
        this.setLayout(null);
        this.setSize(600, 500);
        this.setLayout(null);
        this.setVisible(true);
        init();
    }//constructor

    private void init() {

        this.jlbSearch = new JLabel("Buscar la palabra: ");
        this.jlbSearch.setBounds(50, 50, 340, 30);

        this.jtfSearch = new JTextField();
        this.jtfSearch.setBounds(250, 50, 100, 30);

        this.jbtnSearch = new JButton("Buscar");
        this.jbtnSearch.setBounds(400, 50, 85, 35);
        this.jbtnSearch.addActionListener(this);

        this.jtaPalabrasBuscadas = new JTextArea();
        this.jtaPalabrasBuscadas.setBounds(50, 120, 500, 300);

        this.add(this.jtaPalabrasBuscadas);
        this.add(this.jtfSearch);
        this.add(this.jlbSearch);
        this.add(this.jbtnSearch);

    }// init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtnSearch) {
            text = "";
            ArrayList<Node> list = new ArrayList<Node>();
            list = this.tree.search(tree.root, this.jtfSearch.getText());
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);
                if (node != null) {
                    String node1 = "";
                    node1 = node.getPositionsWord();
                    node1 = node1.substring(0, node1.length() - 1);
                    text += "La palabra es: " + node.getWord() + " [" + node1 + "]." + "\n";
                }
            }//for
            if (text == "") {
                text += "No existe la palabra: " + this.jtfSearch.getText();
            }
            this.jtaPalabrasBuscadas.setText(text);
            this.jtaPalabrasBuscadas.setEditable(false);
            text = "";

        }//jbtnSearch
    }//action

}//class

