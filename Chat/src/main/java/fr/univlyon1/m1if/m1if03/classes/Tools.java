/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.classes;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author antoine dulhoste
 */
public class Tools {
    public static String parseListe(ArrayList<String> liste) throws JSONException{
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        for (String s : liste){
            ja.put(s);
        }
        jo.put("liens", ja);
        return jo.toString();
    }
    
    public static String parseGroupe(Groupe groupe) throws JSONException{
        
        JSONObject jo = new JSONObject();
        jo.put("nom", groupe.getNom());
        jo.put("description", groupe.getDescription());
        jo.put("proprietaire", groupe.getAuteur().pseudo);
        ArrayList<User> membres = groupe.getMembres();
        ArrayList<Billet> billets = groupe.getBillets();
        
        JSONArray ja = new JSONArray();
        for (User m : membres){
            ja.put(m.pseudo);
        }
        jo.put("membres", ja);
        
        JSONArray jar = new JSONArray();
        for (Billet b : billets){
            jar.put(b.getTitre());
        }
        jo.put("billets", jar);
        return  jo.toString();
    }
    
    public static String parseUser(User user) throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put("pseudo", user.pseudo);
        
        return  jo.toString();
    }
    
    public static String parseCommentaire(Commentaire commentaire) throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put("auteur", commentaire.getAuteur().pseudo);
        jo.put("texte", commentaire.getTexte());
        
        return  jo.toString();
    }
    
    public static String parseBillet(Billet billet) throws JSONException{
        JSONObject jo = new JSONObject();
        jo.put("titre", billet.getTitre());
        jo.put("contenu", billet.getContenu());
        jo.put("auteur", billet.getAuteur().pseudo);
        ArrayList<Commentaire> commentaires = billet.getListeCommentaire();
        JSONArray ja = new JSONArray();
        for (Commentaire c : commentaires){
            ja.put(c.getTexte());
        }
        jo.put("commentaires", ja);
        
        return  jo.toString();
    }
    
}
