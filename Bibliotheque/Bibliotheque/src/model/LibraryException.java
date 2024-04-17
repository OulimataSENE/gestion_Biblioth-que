package model;

/**
 * Exception personnalisée pour la gestion des erreurs dans la bibliothèque.
 */
public class LibraryException extends Exception {
    /**
     * Constructeur pour créer une nouvelle exception avec un message donné.
     * @param message Message décrivant l'erreur.
     */
    public LibraryException(String message) {
        super(message);
    }

    /**
     * Exception spécifique indiquant qu'un livre n'a pas été trouvé.
     */
    public static class LivreNonTrouveException extends LibraryException {
        /**
         * Constructeur pour créer une nouvelle exception de livre non trouvé avec un message donné.
         * @param message Message décrivant l'erreur.
         */
        public LivreNonTrouveException(String message) {
            super(message);
        }
    }

    /**
     * Exception spécifique indiquant que le nombre d'emprunts autorisés est dépassé.
     */
    public static class NombreEmpruntsDepasseException extends LibraryException {
        /**
         * Constructeur pour créer une nouvelle exception de dépassement du nombre d'emprunts avec un message donné.
         * @param message Message décrivant l'erreur.
         */
        public NombreEmpruntsDepasseException(String message) {
            super(message);
        }
    }

    /**
     * Exception spécifique indiquant qu'un utilisateur n'est pas autorisé à emprunter un livre.
     */
    public static class EmpruntNonAutoriseException extends LibraryException {
        /**
         * Constructeur pour créer une nouvelle exception d'emprunt non autorisé avec un message donné.
         * @param message Message décrivant l'erreur.
         */
        public EmpruntNonAutoriseException(String message) {
            super(message);
        }
    }

    /**
     * Exception spécifique indiquant qu'un livre ne peut pas être retourné.
     */
    public static class RetourLivreException extends LibraryException {
        /**
         * Constructeur pour créer une nouvelle exception de retour de livre avec un message donné.
         * @param message Message décrivant l'erreur.
         */
        public RetourLivreException(String message) {
            super(message);
        }
    }
}
