import model.Bibliotheque;
import model.BibliothequeGUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                BibliothequeGUI gui = new BibliothequeGUI();
                gui.setVisible(true);

            }
        });
    }
}