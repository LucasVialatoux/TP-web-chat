package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import java.util.List;

public class GestionBillets {

    public List<Billet> getBillets() {
        return billets;
    }
    private List<Billet> billets;
    
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
