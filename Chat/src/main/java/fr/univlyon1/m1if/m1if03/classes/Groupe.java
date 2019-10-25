package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.ArrayList;


public class Groupe implements Serializable{
    private String nom, description, proprio;
    private ArrayList listeParticipants;
    private GestionBillets gest;

    public Groupe() {
    }

    public Groupe(String nom, String description, String proprio, ArrayList listeParticipants, GestionBillets gest) {
        this.nom = nom;
        this.description = description;
        this.proprio = proprio;
        this.listeParticipants = listeParticipants;
        this.gest = gest;
    }
    
    public boolean getCreator(String proprio) {
        if (this.proprio == proprio) {
            return true;
        }
        return false;
    }
    
    public boolean isParticipant(String username) {
        if (this.listeParticipants.contains(username)){
            return true;
        }
        return false;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
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
     * @return the proprio
     */
    public String getProprio() {
        return proprio;
    }

    /**
     * @param proprio the proprio to set
     */
    public void setProprio(String proprio) {
        this.proprio = proprio;
    }

    /**
     * @return the listeParticipants
     */
    public ArrayList getListeParticipants() {
        return listeParticipants;
    }

    /**
     * @param listeParticipants the listeParticipants to set
     */
    public void setListeParticipants(ArrayList listeParticipants) {
        this.listeParticipants = listeParticipants;
    }
    
    public void addParticipants(String username){
        this.listeParticipants.add(username);
    }

    /**
     * @return the gest
     */
    public GestionBillets getGest() {
        return gest;
    }

    /**
     * @param gest the gest to set
     */
    public void setGest(GestionBillets gest) {
        this.gest = gest;
    }
}
