package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class GestionBillets {
    private List<Billet> billets;
    
    public List<Billet> getBillets() {
        return billets;
    }
    
    public List<Billet> getBilletsByGrp(String groupe) {
        ArrayList listToReturn = new ArrayList<>();
        for (int i = 0;i<billets.size();i++){
            if (billets.get(i).getGroupe().equals(groupe)){
                listToReturn.add(billets.get(i));
            }
        }
        return listToReturn;
    }
    
    
    
    public GestionBillets() {
        this.billets = new ArrayList<>();
    }
    
    public void add(Billet billet) {
        this.getBillets().add(billet);
    }
    
    public Billet getBillet(int i) {
        return getBillets().get(i);
    }

    public Billet getLastBillet(int i) {
        if (getBillets().size() > 0)
            return this.getBillet(getBillets().size() -1);
        throw new IndexOutOfBoundsException("Erreur dans l'appel à la fonction getLastBillet");
    }
}
