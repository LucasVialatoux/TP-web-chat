package fr.univlyon1.m1if.m1if03.classes;

public class Billet {
    private String titre, contenu, auteur;

    public Billet() {
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
    }

    public Billet(String titre, String contenu, String auteur) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
