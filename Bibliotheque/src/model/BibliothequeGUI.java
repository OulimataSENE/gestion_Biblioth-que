package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BibliothequeGUI extends JFrame {
    private Bibliotheque bibliotheque; // Référence à l'instance de Bibliotheque
    private JButton btnAjouterLivre, btnSupprimerLivre, btnRechercherLivre, btnEmprunterLivre, btnRetournerLivre,
            btnVerifierEligibilite, btnAfficherStatistiques,btnAfficherLivresEmpruntes,btnAjouterUtilisateur;
    private JTable tableUtilisateurs;
    private DefaultTableModel tableModel;

    private ArrayList<Livre> listeLivres;

    public BibliothequeGUI() {
        setTitle("Gestionnaire de Bibliothèque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centrer la fenêtre
        // Initialisation de la bibliothèque avec une référence à cette instance de BibliothequeGUI
        bibliotheque = new Bibliotheque(this);

        listeLivres = new ArrayList<>();
        tableModel = new DefaultTableModel();

        // Création des boutons
        btnAjouterLivre = new JButton("Ajouter un livre");
        btnSupprimerLivre = new JButton("Supprimer un livre");
        btnRechercherLivre = new JButton("Rechercher un livre");
        btnEmprunterLivre = new JButton("Enregistrer l'emprunt");
        btnRetournerLivre = new JButton("Enregistrer le retour");
        btnVerifierEligibilite = new JButton("Vérifier éligibilité");
        btnAfficherStatistiques = new JButton("Afficher statistiques");
        btnAfficherLivresEmpruntes = new JButton("Afficher les livres empruntés");
        btnAjouterUtilisateur = new JButton("Ajouter un utilisateur");

// Ajout du bouton au panneau
        // Ajout des boutons au panneau
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(btnAjouterLivre);
        panel.add(btnSupprimerLivre);
        panel.add(btnRechercherLivre);
        panel.add(btnEmprunterLivre);
        panel.add(btnRetournerLivre);
        panel.add(btnVerifierEligibilite);
        panel.add(btnAfficherStatistiques);
        panel.add(btnAfficherLivresEmpruntes);
        panel.add(btnAjouterUtilisateur);

        // Ajout du panneau à la fenêtre
        getContentPane().add(panel);

        // Action listeners pour chaque bouton
        btnAjouterLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une boîte de dialogue pour saisir les détails du livre
                String titre = JOptionPane.showInputDialog("Titre du livre :");
                String auteur = JOptionPane.showInputDialog("Auteur du livre :");
                String anneeStr = JOptionPane.showInputDialog("Année de publication :");
                int anneePublication = Integer.parseInt(anneeStr);
                String ISBN = JOptionPane.showInputDialog("ISBN du livre :");

                // Création du livre et ajout à la liste des livres
                Livre livre = new Livre(titre, auteur, anneePublication, ISBN);
                Bibliotheque.ajouterLivre(livre);

                // Affichage d'un message de confirmation
                JOptionPane.showMessageDialog(null, "Livre ajouté avec succès !");
            }
        });

        btnSupprimerLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une boîte de dialogue pour sélectionner le livre à supprimer
                if (listeLivres.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun livre à supprimer !");
                } else {
                    Livre livreASupprimer = (Livre) JOptionPane.showInputDialog(null, "Choisir un livre à supprimer :",
                            "Supprimer un livre", JOptionPane.QUESTION_MESSAGE, null, listeLivres.toArray(),
                            listeLivres.get(0));
                    if (livreASupprimer != null) {
                        Bibliotheque.supprimerLivre(livreASupprimer);
                        JOptionPane.showMessageDialog(null, "Livre supprimé avec succès !");
                    }
                }
            }
        });

        // Action listener pour le bouton Afficher les livres empruntés
        btnAfficherLivresEmpruntes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Ouvrir une boîte de dialogue pour saisir le nom de l'utilisateur
//                String nomUtilisateur = JOptionPane.showInputDialog("Nom de l'utilisateur :");

                // Recherche de l'utilisateur dans la liste
//                Utilisateur utilisateur = Bibliotheque.rechercherUtilisateur(nomUtilisateur);

                // Vérification si l'utilisateur a été trouvé
//                if (utilisateur != null) {
                    // Afficher les livres empruntés par cet utilisateur
                    Bibliotheque.getListeLivres();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Utilisateur non trouvé !");
//                }
            }
        });
        btnAjouterUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une boîte de dialogue pour saisir les informations sur le nouvel utilisateur
                String nomUtilisateur = JOptionPane.showInputDialog("Nom de l'utilisateur :");
                String numeroIdentificationStr = JOptionPane.showInputDialog("Numéro d'identification :");
                String nombreMaxEmpruntsStr = JOptionPane.showInputDialog("Nombre maximal d'emprunts :");
                String cotisationsPayeesStr = JOptionPane.showInputDialog("Cotisations payées :");
                String cotisationsRequisesStr = JOptionPane.showInputDialog("Cotisations requises :");

                // Convertir les chaînes en types appropriés
                int numeroIdentification = Integer.parseInt(numeroIdentificationStr);
                int nombreMaxEmprunts = Integer.parseInt(nombreMaxEmpruntsStr);
                double cotisationsPayees = Double.parseDouble(cotisationsPayeesStr);
                double cotisationsRequises = Double.parseDouble(cotisationsRequisesStr);

                // Créer un nouvel utilisateur avec les informations saisies
                Utilisateur nouvelUtilisateur = new Utilisateur(nomUtilisateur, numeroIdentification, nombreMaxEmprunts, cotisationsPayees, cotisationsRequises);
                bibliotheque.ajouterrUtilisateur(nouvelUtilisateur);

                // Affichage d'un message de confirmation
                JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !");
            }
        });

        

        // Action listener pour le bouton Rechercher un livre
        btnRechercherLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String critere = JOptionPane
                        .showInputDialog("Entrez le titre, l'auteur ou l'ISBN du livre à rechercher :");
                try {
                    Livre livreTrouve = Bibliotheque.rechercherLivre(critere);
                    JOptionPane.showMessageDialog(null, "Livre trouvé :\n" +
                            "Titre : " + livreTrouve.getTitre() + "\n" +
                            "Auteur : " + livreTrouve.getAuteur() + "\n" +
                            "Année de publication : " + livreTrouve.getAnneePublication() + "\n" +
                            "ISBN : " + livreTrouve.getISBN());
                } catch (LibraryException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener pour le bouton Emprunter un livre
        btnEmprunterLivre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String critere = JOptionPane
                        .showInputDialog("Entrez le titre, l'auteur ou l'ISBN du livre à emprunter :");
                try {
                    Livre livreTrouve = Bibliotheque.rechercherLivre(critere);
                    SessionUtilisateur sessionUtilisateur = new SessionUtilisateur();
                    Utilisateur utilisateurActuel = sessionUtilisateur.getUtilisateurActuel();
                    if (utilisateurActuel != null) {
                        Bibliotheque.emprunterLivre(utilisateurActuel, livreTrouve);
                        JOptionPane.showMessageDialog(null, "Livre emprunté avec succès !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun utilisateur actuel trouvé.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (LibraryException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

//       btnRetournerLivre.addActionListener(new ActionListener() {
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         // Ouvrir une boîte de dialogue pour saisir les détails du retour de livre
//         String nomUtilisateur = JOptionPane.showInputDialog("Nom de l'utilisateur :");
//         Livre titreLivre = JOptionPane.showInputDialog("Titre du livre :");

//         // Recherche de l'utilisateur et du livre dans les listes
//         Utilisateur utilisateur = Bibliotheque.rechercherUtilisateur(nomUtilisateur);
//         Livre livre = Bibliotheque.rechercherLivre(titreLivre);

//         // Vérification si l'utilisateur et le livre ont été trouvés
//         if (utilisateur != null && livre != null) {
//             // Retourner le livre
//             try {
//                 Bibliotheque.retournerLivre(utilisateur, livre);
//                 JOptionPane.showMessageDialog(null, "Livre retourné avec succès !");
//             } catch (LibraryException.RetourLivreException ex) {
//                 JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
//             } catch (LibraryException ex) {
//                 JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
//             }

//         } else {
//             JOptionPane.showMessageDialog(null, "Utilisateur ou livre non trouvé !");
//         }
//     }
// });


        btnVerifierEligibilite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir une boîte de dialogue pour saisir le nom de l'utilisateur
                String nomUtilisateur = JOptionPane.showInputDialog("Nom de l'utilisateur :");
                // Obtenir une instance de Bibliotheque à partir de l'instance de BibliothequeGUI
                Bibliotheque bibliothequeInstance = getBibliotheque();
                // Recherche de l'utilisateur dans la liste
                Utilisateur utilisateur = bibliothequeInstance.rechercherUtilisateur(nomUtilisateur);

                // Vérification si l'utilisateur a été trouvé
                if (utilisateur != null) {
                    // Vérifier l'éligibilité de l'utilisateur
                    boolean eligible = Utilisateur.utilisateurEligibleEmprunt(utilisateur);
                    if (eligible) {
                        JOptionPane.showMessageDialog(null, "L'utilisateur est éligible à emprunter un livre.");
                    } else {
                        JOptionPane.showMessageDialog(null, "L'utilisateur n'est pas éligible à emprunter un livre.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Utilisateur non trouvé !");
                }
            }
        });

        btnAfficherStatistiques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour afficher les statistiques de la bibliothèque
            }
        });
    }

    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    // Méthode pour rafraîchir le contenu du tableau avec les utilisateurs

    public void refreshTable() {
        // Effacer les lignes existantes dans le tableau

        tableModel.setRowCount(0);

        // Ajouter les utilisateurs à partir de la liste à la table
        for (Utilisateur utilisateur : bibliotheque.getListeUtilisateurs()) {
            tableModel.addRow(new Object[]{
                    utilisateur.getNom(),
                    utilisateur.getNumeroIdentification(),
                    utilisateur.getNombreMaxEmprunts(),
                    utilisateur.getCotisationsPayees(),
                    utilisateur.getCotisationsRequises()
            });
    }};
   
}
