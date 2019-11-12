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
public class Commentaires extends HttpServlet {
    
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
                    ArrayList<String> liens=new ArrayList();
                    for(Commentaire c : b.getListeCommentaire()){
                        String lien = request.getScheme()+"://"+request.getServerName()+":"
                            +request.getServerPort()+"/groupes/"+s[3]+"/billets/"+b.getID()+"/commentaires/"+c.getId();
                        liens.add(lien);
                    }
                    if(request.getHeader("Accept").equals("text/html")){
                        request.setAttribute("liens",liens);
                        response.setStatus(200);
                        request.getRequestDispatcher("WEB-INF/jsp/commentaires.jsp").forward(request, response);
                    }else{
                        try {
                            String testJson5 = Tools.parseListe(liens);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                    String auteur = null, texte = null;
                    if(request.getHeader("Content-Type")!=null && 
                            request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
                        texte = request.getParameter("texte");
                        auteur = request.getParameter("auteur");
                    }else{
                        StringBuilder jb = new StringBuilder();
                        String line = null;
                        try {
                          BufferedReader reader = request.getReader();
                          while ((line = reader.readLine()) != null)
                            jb.append(line);
                        } catch (Exception e) { }

                        JsonObject jo = new Gson().fromJson(jb.toString(),JsonObject.class);
                        if(jo!=null){
                            if (jo.get("auteur") != null && jo.get("texte")!=null ){
                                auteur = jo.get("auteur").getAsString();
                            }
                            if(jo.get("texte")!=null){
                                texte = jo.get("texte").getAsString();
                            }
                        }
                        
                    }
                    if(texte==null){
                        texte="";
                    }
                    User u = new User();
                    if(auteur!=null){
                        u.pseudo=auteur;
                        Commentaire c = new Commentaire(texte, user);
                        b.getListeCommentaire().add(c);
                        response.setStatus(201);
                    }else{
                       response.setStatus(400); 
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

}
