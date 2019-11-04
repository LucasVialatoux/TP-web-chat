package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Billet  implements Serializable{
    private String titre, contenu, auteur;
    private static int compteur = 0;
    private int id;
    private ArrayList<String> listeCommentaire;

    public Billet() {
        this.id=compteur;
        compteur++;
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
        this.listeCommentaire = new ArrayList<>();
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

    public ArrayList<String> getListeCommentaire() {
        return listeCommentaire;
    }

    public void setListeCommentaire(ArrayList<String> listeCommentaire) {
        this.listeCommentaire = listeCommentaire;
    }
    
    public int getID() {
        return this.id;
}
}
