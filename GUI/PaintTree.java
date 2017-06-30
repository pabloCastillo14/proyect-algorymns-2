/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Domain.Node;
import Domain.Tree;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author jeannette
 */
public class PaintTree extends JPanel {

    private Tree tree;
    private Node node;
    private Graphics2D graphics2D;
    private BufferedImage imageBackground;
    private int x;
    private int y;

    public PaintTree(Tree myTree) {
        super();
        this.tree = myTree;
        this.node = this.tree.root;
        try {
            this.imageBackground = ImageIO.read(getClass().getResourceAsStream("/assets/background.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PaintTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.graphics2D = (Graphics2D) this.imageBackground.getGraphics();

    }//constructor

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(imageBackground, 0, 0, 4500, 4500, null);
        paint(g, node, 2000, 20, 200, 15, 0);
    }//paintComponent

    private void paint(Graphics g, Node node, int x, int y, int distanceX, int distanceY, int count) {
        if (node != null) {

            //*****************pinto las lineas******************************************
            g.setColor(Color.cyan);
            if (node.left != null) {
                g.drawLine(x, y, x - distanceX + (count * 2) + 40, (y + distanceY) + 100);
            }//left
            g.setColor(Color.cyan);
            if (node.right != null) {
                g.drawLine(x, y, x + distanceX + (count * 2) + 40, (y + distanceY) + 100);
            }//right

            //*****************pinto los circulos*****************************************
            g.setColor(Color.cyan);
            g.fillOval(x - 70, y - 15, 130, 80);

            //**************pinto el contenido de las palabras*****************************
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
            String positionsWord = node.getPositionsWord();
            positionsWord = positionsWord.substring(0, positionsWord.length() - 1);
            g.drawString(node.getWord() + "[" + positionsWord + "]", x - 50, y + 15);

            //********vuelvo a llamar el metodo, por la izquierda ************************
            paint(g, node.left, (int) (x - distanceX), (y + distanceY) + 100, ((distanceX + 50) + count * 2) - 20, distanceY + 77, count + 1);
            //********vuelvo a llamar el metodo, por la derecha***************************
            paint(g, node.right, (int) (x + distanceX), (y + distanceY) + 100, ((distanceX + 200) - count * 2) - 20, distanceY + 57, count + 1);
        }//if
    }//paintTree

}//class
