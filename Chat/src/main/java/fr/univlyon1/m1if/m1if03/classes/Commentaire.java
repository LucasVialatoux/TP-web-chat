/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.classes;

/**
 *
 * @author lucas
 */
public class Commentaire {
    private int id;
    private static int compteur = 0;
    private String texte;
    private User auteur;
    
    public Commentaire(String texte,User auteur){
        this.id=compteur;
        compteur++;
        this.auteur=auteur;
        this.texte=texte;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * @param texte the texte to set
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * @return the auteur
     */
    public User getAuteur() {
        return auteur;
    }

    /**
     * @param auteur the auteur to set
     */
    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }
   
   
   
}
