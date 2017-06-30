/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author jeannette
 */
public class ShowTree2 extends JFrame {

    String txt = "";
    private JTextArea area;

    public ShowTree2(String texto) {
        Dimension dime = super.getToolkit().getScreenSize();
        this.setSize(dime);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        txt = texto;
        init();
    }

    public void init() {
        this.area = new JTextArea(200, 200);
        this.add(this.area);
        area.setText(txt);
    }
}

