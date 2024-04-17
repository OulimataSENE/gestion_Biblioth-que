 package model;

public class LivreSpecialise {
    class Roman extends Livre {
        private String genre;
    
        public Roman(String titre, String auteur, int anneePublication, String ISBN, String genre) {
            super(titre, auteur, anneePublication, ISBN);
            this.genre = genre;
        }
    
        // Getters, Setters, toString() spécifiques aux romans
        public String getGenre() {
            return genre;
        }
    
        public void setGenre(String genre) {
            this.genre = genre;
        }
        public String toString() {
            return "Roman{" +
                    "genre='" + genre + '\'' +
                    "} " + super.toString();
        }
    }
    
    class Essai extends Livre {
        private String sujet;
    
        public Essai(String titre, String auteur, int anneePublication, String ISBN, String sujet) {
            super(titre, auteur, anneePublication, ISBN);
            this.sujet = sujet;
        }
    
        // Getters, Setters, toString() spécifiques aux essais
        public String getSujet() {
            return sujet;
        }
    
        public void setSujet(String sujet) {
            this.sujet = sujet;
        }
    
       
        public String toString() {
            return "Essai{" +
                    "sujet='" + sujet + '\'' +
                    "} " + super.toString();
        }
    }
    
    class LivreAudio extends Livre {
        private String parleur;
    
        public LivreAudio(String titre, String auteur, int anneePublication, String ISBN, String parleur) {
            super(titre, auteur, anneePublication, ISBN);
            this.parleur = parleur;
        }
    
        // Getters, Setters, toString() spécifiques aux livres audio
        public String getparleur() {
            return parleur;
        }
    
        public void setparleur(String parleur) {
            this.parleur = parleur;
        }
    
        
        public String toString() {
            return "LivreAudio{" +
                    "parleur='" + parleur + '\'' +
                    "} " + super.toString();
        }
    }
    
}
