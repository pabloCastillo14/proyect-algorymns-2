/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Business.FileBusiness;
import Domain.Tree;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Pablo Castillo
 */
public class MainWindow extends JFrame implements ActionListener{
    JMenuBar barra;
    JMenu menu;
    JMenuItem jmiCargarArchivo;
    JMenuItem jmiArchivoCreado;
    JMenuItem jmiBuscarPalabra;
    JMenuItem jmiGuardarArchivo;
    JMenuItem jmiMOstrarTexto;
    String rute = "";
    private PaintTree paintTree;
    private JScrollPane scroll;

    private Tree tree;
    private JFileChooser chooser;
    String ruta = "";

    public MainWindow() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        tree = new Tree();
        this.barra = new JMenuBar();
        this.setJMenuBar(barra);
        this.menu = new JMenu("Menú");
        barra.add(menu);
        this.jmiCargarArchivo = new JMenuItem("Cargar archivo");
        menu.add(jmiCargarArchivo);
        this.jmiCargarArchivo.addActionListener(this);
        jmiMOstrarTexto = new JMenuItem("Mostrar archivo original");
        menu.add(jmiMOstrarTexto);
        jmiMOstrarTexto.addActionListener(this);
        this.jmiBuscarPalabra = new JMenuItem("Buscar palabra");
        menu.add(this.jmiBuscarPalabra);
        this.jmiBuscarPalabra.addActionListener(this);
        this.jmiGuardarArchivo = new JMenuItem("Guardar archivo");
        this.jmiGuardarArchivo.addActionListener(this);
        menu.add(this.jmiGuardarArchivo);
        this.jmiArchivoCreado = new JMenuItem("Mostrar archivo creado");
        menu.add(jmiArchivoCreado);
        this.jmiArchivoCreado.addActionListener(this);

    }//const

    public void paint() {
        this.paintTree = new PaintTree(this.tree);
        this.scroll = new JScrollPane();
        this.scroll.setBounds(new Rectangle(10, 100, 1345, 600));
        this.scroll.setViewportView(this.paintTree);
        this.scroll.getViewport().setView(this.paintTree);
        this.paintTree.setPreferredSize(new Dimension(4500, 4500));
        this.paintTree.repaint();
        this.paintTree.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.add(this.scroll);
    }//paint

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jmiCargarArchivo) {
            try {
                String information = "";
                chooser = new JFileChooser();
                String rute;
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    FileReader fileReader = null;
                    try {
                        rute = chooser.getSelectedFile().getAbsolutePath();
                        this.ruta = rute;
                        File file = new File(rute);
                        fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            information += line + "\n";
                        }//while
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String[] createArray = information.split("[ \n]");
                for (int i = 0; i < createArray.length; i++) {
                    this.tree.insert(createArray[i]);
                }//for i
                paint();
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == jmiBuscarPalabra) {

            SearchWord search = new SearchWord(tree);
            search.setVisible(true);
            // this.add(search);
        } else if (jmiGuardarArchivo == ae.getSource()) {

            chooser = new JFileChooser();
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("txt", "TXT");
            chooser.setFileFilter(extensionFilter);
            try {
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    rute = chooser.getSelectedFile().getAbsolutePath();

                    tree.saveNodes(tree.root, rute);
                    tree.subTreeInsert(rute);
                }//if
            } catch (Exception ex) {
            }//try-catch

        } else if (ae.getSource() == jmiMOstrarTexto) {
            try {
                ShowTextOriginal mostra = new ShowTextOriginal(leerARchivo());
                mostra.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == jmiArchivoCreado) {
            FileBusiness fileBusiness = new FileBusiness(rute);
            try {
                /// JOptionPane.showMessageDialog(null, fileBusiness.backToText());
                ShowTree2 arbol = new ShowTree2(fileBusiness.backToText());
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//actionPerformed

    public String leerARchivo() throws IOException {
        String information = "";
//        chooser = new JFileChooser();
        String rute;
        //      if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        FileReader fileReader = null;
        try {
            //          rute = chooser.getSelectedFile().getAbsolutePath();
            File file = new File(ruta);
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                information += line + "\n";
            }//while
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        //    } 
        return information;

    }
}
