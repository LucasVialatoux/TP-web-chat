/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.modeles;

import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lucas
 */
public class GroupeModele {
    
    public GroupeModele(HttpServletRequest request){
        ServletContext contexte = request.getServletContext();
        HashMap<String, Groupe> grp = (HashMap)contexte.getAttribute("map");
        for (int i=0; i<grp.size();i++){
            //Groupe grp = new Groupe();
        //grp.setNom("test");
        //request.setAttribute("myBean", grp);
        }
    }
    
        
}
