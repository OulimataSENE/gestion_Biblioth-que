package model;

import java.util.ArrayList;
import java.util.HashMap;
// import java.util.List;

public class Bibliotheque {
//    public static final String SessionUtilisateur ;
private BibliothequeGUI bibliothequeGUI; // Référence à l'instance de BibliothequeGUI

    public static ArrayList<Livre> listeLivres;
    static HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;
    private ArrayList<Utilisateur> listeUtilisateurs;

    public Bibliotheque(BibliothequeGUI bibliothequeGUI) {
        listeLivres = new ArrayList<>();
        this.bibliothequeGUI = bibliothequeGUI; // Initialisation de la référence à BibliothequeGUI

        listeUtilisateurs = new ArrayList<>();
        empruntsUtilisateurs = new HashMap<>();

    }
    // Méthode pour obtenir la liste des utilisateurs
    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }
    public void ajouterrUtilisateur(Utilisateur utilisateur) {
        listeUtilisateurs.add(utilisateur);
        bibliothequeGUI.refreshTable();
    }

    public static void ajouterLivre(Livre livre) {
        if (listeLivres == null) {
            // Si c'est le cas, l'initialiser
            return;
        }
        listeLivres.add(livre);
    }
    public static ArrayList<Livre> getListeLivres() {
        return listeLivres;
    }
    public static void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    // Méthode pour rechercher un utilisateur par son nom
    public Utilisateur rechercherUtilisateur(String nom) {

        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return utilisateur;
            }
        }
        return null;
    }
    
    public static Livre rechercherLivre(String critere) throws LibraryException {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equals(critere) || livre.getAuteur().equals(critere) || livre.getISBN().equals(critere)) {
                return livre;
            }
        }
        throw new LibraryException.LivreNonTrouveException("Livre non trouvé avec le critère : " + critere);
    }

    public static void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!listeLivres.contains(livre)) {
            throw new LibraryException.LivreNonTrouveException("Livre non trouvé dans la bibliothèque");
        }
        if (!utilisateur.isCotisationsAJour()) {
            throw new LibraryException.EmpruntNonAutoriseException("Emprunt non autorisé pour l'utilisateur");
        }
        if (empruntsUtilisateurs.containsKey(utilisateur) && empruntsUtilisateurs.get(utilisateur).size() >= utilisateur.getNombreMaxEmprunts()) {
            throw new LibraryException.NombreEmpruntsDepasseException("Nombre maximum d'emprunts atteint pour l'utilisateur");
        }
        empruntsUtilisateurs.computeIfAbsent(utilisateur, k -> new ArrayList<>()).add(livre);
    }

    // public void emprunterLivre(Utilisateur utilisateur, Livre livre) {
    //     if (!listeLivres.contains(livre)) {
    //         System.out.println("Livre non trouvé dans la bibliothèque");
    //         return;
    //     }
    
    //     if (!utilisateur.utilisateurEligibleEmprunt()) {
    //         System.out.println("Emprunt non autorisé pour l'utilisateur");
    //         return;
    //     }
    
    //     if (empruntsUtilisateurs.containsKey(utilisateur) && empruntsUtilisateurs.get(utilisateur).size() >= utilisateur.getNombreMaxEmprunts()) {
    //         System.out.println("Nombre maximum d'emprunts atteint pour l'utilisateur");
    //         return;
    //     }
    
    //     empruntsUtilisateurs.computeIfAbsent(utilisateur, k -> new ArrayList<>()).add(livre);
    // }
    

    public void retournerLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!empruntsUtilisateurs.containsKey(utilisateur) || !empruntsUtilisateurs.get(utilisateur).contains(livre)) {
            throw new LibraryException.RetourLivreException("Livre non trouvé dans les emprunts de l'utilisateur");
        }
        empruntsUtilisateurs.get(utilisateur).remove(livre);
    }



}
