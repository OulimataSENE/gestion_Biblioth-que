package model;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Utilisateur {
    private String nom; // Nom de l'utilisateur
    private int numeroIdentification; // Numéro d'identification de l'utilisateur
    private int nombreMaxEmprunts; // Nombre maximum de livres qu'un utilisateur peut emprunter simultanément
    private ArrayList<Livre> livresEmpruntes; // Liste des livres empruntés par l'utilisateur
    private double cotisationsPayees; // Montant des cotisations payées
    private double cotisationsRequises; // Somme demandée pour être à jour avec les cotisations

    /**
     * Constructeur pour créer un nouvel utilisateur avec les informations fournies.
     * @param nom Nom de l'utilisateur.
     * @param numeroIdentification Numéro d'identification de l'utilisateur.
     * @param nombreMaxEmprunts Nombre maximum de livres qu'un utilisateur peut emprunter simultanément.
     * @param cotisationsPayees Montant des cotisations payées par l'utilisateur.
     * @param cotisationsRequises Somme demandée pour être à jour avec les cotisations.
     */
    public Utilisateur(String nom, int numeroIdentification, int nombreMaxEmprunts, double cotisationsPayees, double cotisationsRequises) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.nombreMaxEmprunts = nombreMaxEmprunts;
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationsRequises = cotisationsRequises;
        this.cotisationsPayees = cotisationsPayees;
    }

    /**
     * Vérifie si les cotisations de l'utilisateur sont à jour.
     * @return true si les cotisations sont à jour, sinon false.
     */
    public boolean isCotisationsAJour() {
        return cotisationsPayees >= cotisationsRequises;
    }

    /**
     * Met à jour le montant des cotisations payées par l'utilisateur.
     * @param montant Montant des cotisations à ajouter.
     */
    public void payerCotisations(double montant) {
        cotisationsPayees += montant;
    }

    /**
     * Vérifie l'éligibilité de l'utilisateur à emprunter un livre.
     * @param utilisateur L'utilisateur à vérifier.
     * @return true si l'utilisateur est éligible à l'emprunt, sinon false.
     */
    public static boolean utilisateurEligibleEmprunt(Utilisateur utilisateur) {
        return utilisateur.isCotisationsAJour();
    }

    /**
     * Emprunte un livre pour l'utilisateur.
     * @param livre Le livre à emprunter.
     * @throws LibraryException.NombreEmpruntsDepasseException Si le nombre maximum d'emprunts est dépassé.
     */
    public void emprunterLivre(Livre livre) throws LibraryException, LibraryException.NombreEmpruntsDepasseException {
        if (livresEmpruntes.size() >= nombreMaxEmprunts) {
            throw new LibraryException.NombreEmpruntsDepasseException("Nombre maximum d'emprunts atteint pour l'utilisateur");
        }
        livresEmpruntes.add(livre);
    }

    /**
     * Retourne un livre emprunté par l'utilisateur.
     * @param utilisateur L'utilisateur qui retourne le livre.
     * @param livre Le livre à retourner.
     * @throws LibraryException.RetourLivreException Si le livre n'est pas trouvé dans les emprunts de l'utilisateur.
     */
    public void retournerLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!livresEmpruntes.contains(livre)) {
            throw new LibraryException.RetourLivreException("Livre non trouvé dans les emprunts de l'utilisateur");
        }
        livresEmpruntes.remove(livre);
    }

    /**
     * Affiche les livres empruntés par l'utilisateur.
     */
    public void afficherLivresEmpruntes() {
        System.out.println("Livres empruntés par " + nom + ":");
        for (Livre livre : livresEmpruntes) {
            System.out.println("- " + livre.getTitre());
        }
    }

    // Getters et setters pour les attributs de l'utilisateur

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }

    public int getNombreMaxEmprunts() {
        return nombreMaxEmprunts;
    }

    public void setNombreMaxEmprunts(int nombreMaxEmprunts) {
        this.nombreMaxEmprunts = nombreMaxEmprunts;
    }

    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }

    public double getCotisationsPayees() {
        return cotisationsPayees;
    }

    public void setCotisationsPayees(double cotisationsPayees) {
        this.cotisationsPayees = cotisationsPayees;
    }

    public double getCotisationsRequises() {
        return cotisationsRequises;
    }

    public void setCotisationsRequises(double cotisationsRequises) {
        this.cotisationsRequises = cotisationsRequises;
    }
}
