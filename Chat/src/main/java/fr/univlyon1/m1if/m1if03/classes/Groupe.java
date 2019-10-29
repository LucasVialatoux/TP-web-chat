package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Groupe implements Serializable{
    private String description, proprio;
    private ArrayList listeParticipants = new ArrayList();
    private ArrayList<Billet> listeBillets;

    public Groupe() {
    }

    public Groupe(String nom, String description, String proprio, ArrayList listeParticipants) {
        this.description = description;
        this.proprio = proprio;
        this.listeParticipants = listeParticipants;
        Global.groupes.put(nom, this);
        this.listeBillets = new ArrayList();
    }
    
    public Billet getBillet(int i) {
        return this.listeBillets.get(i);
    }
    
    public void add(Billet billet) {
        this.listeBillets.add(billet);
    }

    public ArrayList getBillets() {
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
}
