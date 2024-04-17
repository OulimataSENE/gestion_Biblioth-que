 package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Utilisateur {
    private String nom;
    private int numeroIdentification;
    private int nombreMaxEmprunts;
    private ArrayList<Livre> livresEmpruntes;
    private double cotisationsPayees; // Montant des cotisations payées
    private double cotisationsRequises; // Somme demandée pour être à jour

    public Utilisateur(String nom, int numeroIdentification, int nombreMaxEmprunts, double cotisationsPayees, double cotisationsRequises) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.nombreMaxEmprunts = nombreMaxEmprunts;
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationsRequises = cotisationsRequises;
        this.cotisationsPayees = cotisationsPayees;
    }
    public boolean isCotisationsAJour() {
        return cotisationsPayees >= cotisationsRequises;
    }


    public void payerCotisations(double montant) {
        cotisationsPayees += montant;
    }
     // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
     public static boolean utilisateurEligibleEmprunt(Utilisateur utilisateur) {
        return utilisateur.isCotisationsAJour();
    }

    public void emprunterLivre(Livre livre) throws LibraryException, LibraryException.NombreEmpruntsDepasseException {
        if (livresEmpruntes.size() >= nombreMaxEmprunts) {
            throw new LibraryException.NombreEmpruntsDepasseException("Nombre maximum d'emprunts atteint pour l'utilisateur");
        }
        livresEmpruntes.add(livre);
    }

    public void retournerLivre(Utilisateur utilisateur, Livre livre) throws LibraryException {
        if (!livresEmpruntes.contains(livre)) {
            throw new LibraryException.RetourLivreException("Livre non trouvé dans les emprunts de l'utilisateur");
        }
        livresEmpruntes.remove(livre);
    }

    public void afficherLivresEmpruntes() {
        System.out.println("Livres empruntés par " + nom + ":");
        for (Livre livre : livresEmpruntes) {
            System.out.println("- " + livre.getTitre());
        }
    }

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
    public double getCotisationsPayees() {
        return cotisationsPayees;
    }

    public double getCotisationsRequises() {
        return cotisationsRequises;
    }


    public void setCotisationsPayees(double cotisationsPayees) {
        this.cotisationsPayees = cotisationsPayees;
    }

    public void setCotisationsRequises(double cotisationsRequises) {
        this.cotisationsRequises = cotisationsRequises;
    }
     // Méthode pour afficher les statistiques de la bibliothèque
     public void genererRapportLivresPlusEmpruntes(int nombreTop) {
        HashMap<Livre, Integer> empruntsParLivre = new HashMap<>();
        for (ArrayList<Livre> emprunts : Bibliotheque.empruntsUtilisateurs.values()) {
            for (Livre livre : emprunts) {
                empruntsParLivre.put(livre, empruntsParLivre.getOrDefault(livre, 0) + 1);
            }
        }

        System.out.println("Livres les plus empruntés :");
        empruntsParLivre.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(nombreTop)
                .forEach(entry -> System.out.println(entry.getKey().getTitre() + " - " + entry.getValue() + " emprunts"));
    }

    public void genererRapportUtilisateursPlusActifs(int nombreTop) {
        HashMap<Utilisateur, Integer> empruntsParUtilisateur = new HashMap<>();
        for (Entry<Utilisateur, ArrayList<Livre>> entry : Bibliotheque.empruntsUtilisateurs.entrySet()) {
            empruntsParUtilisateur.put(entry.getKey(), entry.getValue().size());
        }

        System.out.println("Utilisateurs les plus actifs :");
        empruntsParUtilisateur.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(nombreTop)
                .forEach(entry -> System.out.println(entry.getKey().getNom() + " - " + entry.getValue() + " emprunts"));
    }


}
