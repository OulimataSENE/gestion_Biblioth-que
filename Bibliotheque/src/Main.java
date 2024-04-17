import model.Bibliotheque;
import model.BibliothequeGUI;

import javax.swing.SwingUtilities;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        // Créez une instance de BibliothequeGUI
//        BibliothequeGUI bibliothequeGUI = new BibliothequeGUI();
//
//        // Appelez getBibliotheque() sur cette instance
//        Bibliotheque bibliotheque = bibliothequeGUI.getBibliotheque();
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                BibliothequeGUI gui = new BibliothequeGUI();
                gui.setVisible(true);
            }

        });
    }
}