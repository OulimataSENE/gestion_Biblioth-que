package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bibliothèque {
    private List<Livre> listeLivres; // Liste des livres disponibles dans la bibliothèque
    private Map<Utilisateur, List<Livre>> empruntsUtilisateurs; // Détails des emprunts effectués par les utilisateurs

    /**
     * Constructeur pour initialiser une nouvelle instance de Bibliothèque.
     */
    public Bibliothèque() {
        this.listeLivres = new ArrayList<>(); // Initialisation de la liste des livres
        this.empruntsUtilisateurs = new HashMap<>(); // Initialisation de la map des emprunts des utilisateurs
    }

    /**
     * Ajoute un livre à la bibliothèque.
     * @param livre Le livre à ajouter.
     */
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    /**
     * Supprime un livre de la bibliothèque.
     * @param livre Le livre à supprimer.
     */
    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    /**
     * Recherche un livre dans la bibliothèque en fonction du critère spécifié (titre, auteur ou ISBN).
     * @param critere Le critère de recherche.
     * @return Le livre trouvé.
     * @throws LibraryException.LivreNonTrouveException Si aucun livre correspondant au critère n'est trouvé.
     */
    public Livre rechercherLivre(String critere) throws LibraryException {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equals(critere) || livre.getAuteur().equals(critere) || livre.getISBN().equals(critere)) {
                return livre;
            }
        }
        throw new LibraryException.LivreNonTrouveException("Livre non trouvé avec le critère : " + critere);
    }

    /**
     * Emprunte un livre pour un utilisateur spécifié.
     * @param utilisateur L'utilisateur qui emprunte le livre.
     * @param livre Le livre à emprunter.
     * @throws LibraryException Si l'emprunt n'est pas autorisé ou si le nombre maximum d'emprunts est dépassé.
     */
    public void emprunterLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!listeLivres.contains(livre)) {
            throw new LibraryException.LivreNonTrouveException("Livre non trouvé dans la bibliothèque");
        }
        if (!utilisateur.utilisateurEligibleEmprunt(utilisateur)) {
            throw new LibraryException.EmpruntNonAutoriseException("Emprunt non autorisé pour l'utilisateur");
        }
        if (empruntsUtilisateurs.containsKey(utilisateur) && empruntsUtilisateurs.get(utilisateur).size() >= utilisateur.getNombreMaxEmprunts()) {
            throw new LibraryException.NombreEmpruntsDepasseException("Nombre maximum d'emprunts atteint pour l'utilisateur");
        }
        empruntsUtilisateurs.computeIfAbsent(utilisateur, k -> new ArrayList<>()).add(livre);
    }

    /**
     * Retourne un livre emprunté par un utilisateur spécifié.
     * @param utilisateur L'utilisateur qui retourne le livre.
     * @param livre Le livre à retourner.
     * @throws LibraryException.RetourLivreException Si le livre n'est pas trouvé dans les emprunts de l'utilisateur.
     */
    public void retournerLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!empruntsUtilisateurs.containsKey(utilisateur) || !empruntsUtilisateurs.get(utilisateur).contains(livre)) {
            throw new LibraryException.RetourLivreException("Livre non trouvé dans les emprunts de l'utilisateur");
        }
        empruntsUtilisateurs.get(utilisateur).remove(livre);
    }
}
