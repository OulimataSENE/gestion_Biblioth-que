// package model;

// import model.*;
package model;
import java.util.Scanner;

public class Test extends BibliothequeGUI {
    public void main(String[] args) throws LibraryException {
        Bibliotheque bibliothèque = new Bibliotheque(this);
        Scanner scanner = new Scanner(System.in);

        Livre livre1 = new Livre("Harry Potter", "J.K. Rowling", 1997, "9780545010221");
        Livre livre2 = new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, "9782070612880");

        Utilisateur utilisateur1 = new Utilisateur("Alice", 1001, 3, 0, 222);

        bibliothèque.ajouterLivre(livre1);
        bibliothèque.ajouterLivre(livre2);

        System.out.println("Bienvenue dans la bibliothèque !");
        int choix;
        do {
            System.out.println("\nMenu :");
            System.out.println("1. Rechercher un livre");
            System.out.println("2. Ajouter un livre");
            System.out.println("3. Supprimer un livre");
            System.out.println("4. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le saut de ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrez le titre, l'auteur ou l'ISBN du livre : ");
                    String critere = scanner.nextLine();
                    try {
                        Livre livreRecherche = bibliothèque.rechercherLivre(critere);
                        System.out.println("Livre trouvé : " + livreRecherche);
                    } catch (LibraryException.LivreNonTrouveException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    // Ajout d'un livre
                    System.out.print("Entrez le titre du livre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Entrez l'auteur du livre : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Entrez l'année de publication du livre : ");
                    int annee = scanner.nextInt();
                    scanner.nextLine(); // Pour consommer le saut de ligne
                    System.out.print("Entrez le numéro ISBN du livre : ");
                    String isbn = scanner.nextLine();
                    Livre nouveauLivre = new Livre(titre, auteur, annee, isbn);
                    bibliothèque.ajouterLivre(nouveauLivre);
                    System.out.println("Livre ajouté avec succès !");

                    // Affichage des livres après l'ajout
                    System.out.println("Livres dans la bibliothèque après l'ajout :");
                    for (Livre livre : bibliothèque.getListeLivres()) {
                        System.out.println(livre);
                    }
                    break;
                case 3:
                    System.out.print("Entrez le titre, l'auteur ou l'ISBN du livre à supprimer : ");
                    String critereSuppression = scanner.nextLine();
                    try {
                        Livre livreASupprimer = bibliothèque.rechercherLivre(critereSuppression);
                        bibliothèque.supprimerLivre(livreASupprimer);
                        System.out.println("Livre supprimé avec succès !");
                    } catch (LibraryException.LivreNonTrouveException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide !");
                    break;
            }
        } while (choix != 4);
    }
}

