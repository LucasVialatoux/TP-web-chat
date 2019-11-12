package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Billet  implements Serializable{
    private String titre, contenu;
    private User auteur;
    private static int compteur = 0;
    private int id;
    private ArrayList<Commentaire> commentaires;

    public Billet(String titre,String contenu,User auteur) {
        this.id=compteur;
        compteur++;
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.commentaires = new ArrayList<>();
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

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public ArrayList<Commentaire> getListeCommentaire() {
        return commentaires;
    }

    public void setListeCommentaire(ArrayList<Commentaire> listeCommentaire) {
        this.commentaires = listeCommentaire;
    }
    
    public int getID() {
        return this.id;
}
}
