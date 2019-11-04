/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author antoine dulhoste
 */
public class Global  implements Serializable{
    public static HashMap<String, Groupe> groupes = new HashMap();
    
    public static Groupe getNom(String nom){
        return groupes.get(nom);
    }
}
