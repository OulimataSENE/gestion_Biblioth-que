package model;// package model;

public class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }

    public static class LivreNonTrouveException extends LibraryException {
        public LivreNonTrouveException(String message) {
            super(message);
        }
    }

    public static class NombreEmpruntsDepasseException extends LibraryException {
        public NombreEmpruntsDepasseException(String message) {
            super(message);
        }
    }

    public static class EmpruntNonAutoriseException extends LibraryException {
        public EmpruntNonAutoriseException(String message) {
            super(message);
        }
    }

    public static class RetourLivreException extends LibraryException {
        public RetourLivreException(String message) {
            super(message);
        }
    }

    // Ajoutez d'autres exceptions personnalisées selon vos besoins
}
