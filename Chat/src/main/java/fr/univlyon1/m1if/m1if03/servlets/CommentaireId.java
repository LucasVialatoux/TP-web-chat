/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Commentaire;
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Tools;
import fr.univlyon1.m1if.m1if03.classes.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author antoine dulhoste
 */
public class CommentaireId extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        Groupe g=Global.getNom(s[3]);
        if(g!=null){
            ArrayList<User> list=g.getMembres();
            User user =(User)request.getAttribute("user");
            boolean userList=false;
            for(User u : list){
                if(u.pseudo==user.pseudo){
                    userList=true;
                }
            }
            if(userList){
                int i= -9;
                try{
                    i=Integer.parseInt(s[5]);
                }catch(Exception e){
                    response.setStatus(404);
                    return ;
                }
                Billet b =g.getBillet(i);
                if(b!=null){
                    ArrayList<Commentaire> listC=b.getListeCommentaire();
                    int j= -9;
                    try{
                        j=Integer.parseInt(s[7]);
                    }catch(Exception e){
                        response.setStatus(404);
                        return ;
                    }
                    Commentaire com = null;
                    for(Commentaire c :listC){
                        if(c.getId()==j)com=c;
                    }
                    if(com==null){
                        response.setStatus(404);
                    }else{
                        if(request.getHeader("Accept").equals("text/html")){
                            request.setAttribute("commentaire",com);
                            response.setStatus(200);
                            request.getRequestDispatcher("WEB-INF/jsp/commentaire.jsp").forward(request, response);
                        }else{
                            try {
                                String testJson5 = Tools.parseCommentaire(com);

                                ObjectMapper mapper5 = new ObjectMapper();
                                Object jsonObject = mapper5.readValue(testJson5, Object.class);
                                String prettyJson = mapper5.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
                                PrintWriter out = response.getWriter();
                                response.setContentType("application/json");
                                response.setCharacterEncoding("UTF-8");
                                out.print(prettyJson);
                                out.flush();
                            } catch (JSONException ex) {
                                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }else{
                    response.setStatus(404);
                }
            }else{
                 response.setStatus(403);
            } 
        }else{
            response.setStatus(404);
        }
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        Groupe g=Global.getNom(s[3]);
        if(g!=null){
            ArrayList<User> list=g.getMembres();
            User user =(User)request.getAttribute("user");
            boolean userList=false;
            for(User u : list){
                if(u.pseudo==user.pseudo){
                    userList=true;
                }
            }
            if(userList){
                int i= -9;
                try{
                    i=Integer.parseInt(s[5]);
                }catch(Exception e){
                    response.setStatus(404);
                    return ;
                }
                Billet b =g.getBillet(i);
                if(b!=null){
                    ArrayList<Commentaire> listC=b.getListeCommentaire();
                    int j= -9;
                    try{
                        j=Integer.parseInt(s[7]);
                    }catch(Exception e){
                        response.setStatus(404);
                        return ;
                    }
                    Commentaire com = null;
                    for(Commentaire c :listC){
                        if(c.getId()==j)com=c;
                    }
                    if(com==null){
                        response.setStatus(404);
                    }else{
                        listC.remove(com);
                        response.setStatus(204);
                    }
                }else{
                    response.setStatus(404);
                }
            }else{
                 response.setStatus(403);
            } 
        }else{
            response.setStatus(404);
        }
        
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = (String)request.getAttribute("URI");
        String[] s = URI.split("/");
        Groupe g = Global.getNom(s[3]);
        
        //Le groupe existe et il contient le billet
        if (g!=null){
            ArrayList<User> list=g.getMembres();
            User user =(User)request.getAttribute("user");
            boolean userList=false;
            for(User u : list){
                if(u.pseudo==user.pseudo){
                    userList=true;
                }
            }
            if(userList){
                int i= -9;
                try{
                    i=Integer.parseInt(s[5]);
                }catch(Exception e){
                    response.setStatus(404);
                    return ;
                }
                Billet b = g.getBillet(i);
                if (b!=null){
                    int j = -9;
                    try{
                        j=Integer.parseInt(s[7]);
                    }catch(Exception e){
                        response.setStatus(404);
                        return ;
                    }
                    String auteur = null,texte = "";
                    StringBuilder jb = new StringBuilder();
                    String line = null;
                    try {
                        BufferedReader reader = request.getReader();
                        while ((line = reader.readLine()) != null)
                            jb.append(line);
                    } catch (Exception e) { }
                    JsonObject jsonObject = new Gson().fromJson(jb.toString(),JsonObject.class);
                    if (jsonObject!=null){
                        if (jsonObject.get("auteur") != null ){
                            auteur = jsonObject.get("auteur").getAsString();
                        }
                        if (jsonObject.get("texte") != null ){
                            texte = jsonObject.get("texte").getAsString();
                        }
                    }
                    ArrayList<Commentaire> listC = g.getBillet(i).getListeCommentaire();
                    Commentaire com = null;
                    for (Commentaire c : listC ){
                        if (c.getId()==j)com=c;
                    }
                    if (com!=null){
                        if (auteur != null && !auteur.equals("")){
                            User u = new User();
                            u.pseudo=auteur;
                            com.setAuteur(u);
                        }
                        if (texte != null && !texte.equals("")){
                            com.setTexte(texte);
                        }
                        response.setStatus(204);
                    } else {
                        response.setStatus(404);
                    }
                } else {
                   response.setStatus(404); 
                }
            } else {
                response.setStatus(403);
            }
        } else {
            response.setStatus(404);
        }
    }
}
