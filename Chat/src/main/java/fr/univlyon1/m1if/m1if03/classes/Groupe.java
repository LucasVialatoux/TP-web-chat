package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Groupe implements Serializable{
    private String description,nom;
    private User auteur;
    private ArrayList<User>  membres = new ArrayList();
    private ArrayList<Billet> listeBillets;

    public Groupe() {
    }

    public Groupe(String nom, String description, User auteur, ArrayList membres) {
        this.description = description;
        this.auteur = auteur;
        this.membres = membres;
        Global.groupe.put(nom, this);
        this.nom = nom;
        this.listeBillets = new ArrayList();
    }
    
    public String getNom() {
        return nom;
    }
    
    public Billet getBillet(int i) {
        for(Billet b:listeBillets){
            if(b.getID()==i){
                return b;
            }
        }
        return null;
    }
    
    public void add(Billet billet) {
        this.listeBillets.add(billet);
    }

    public ArrayList<Billet> getBillets() {
        return this.listeBillets;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @return the listeParticipants
     */
    public ArrayList<User> getMembres() {
        return membres;
    }

    /**
     * @param listeParticipants the listeParticipants to set
     */
    public void setMembres(ArrayList listeParticipants) {
        this.membres = listeParticipants;
    }
    
    public void addParticipants(User username){
        this.membres.add(username);
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
