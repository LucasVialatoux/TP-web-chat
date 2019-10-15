package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;

public class Billet {
    private String titre, contenu, auteur, groupe;
    private static int ID = -1;
    private ArrayList<String> listeCommentaire;

    public Billet() {
        ID++;
        this.titre = "Rien";
        this.contenu = "Vide";
        this.auteur = "Personne";
        this.groupe = "Aucun";
        this.listeCommentaire = new ArrayList<>();
    }

    public Billet(String titre, String contenu, String auteur, String groupe) {
        this.titre = titre;
        this.contenu = contenu;
        this.auteur = auteur;
        this.groupe = groupe;
    }
    
    public String getGroupe() {
        return groupe;
    }
    
    public void setGroupe(String groupe) {
        this.groupe = groupe;
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
    
    public static int getID() {
        return ID;
    }
}
