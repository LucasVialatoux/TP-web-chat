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
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Tools;
import fr.univlyon1.m1if.m1if03.classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import java.io.BufferedReader;

/**
 *
 * @author antoine dulhoste
 */
public class BilletId extends HttpServlet {
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
                    if(request.getHeader("Accept").equals("text/html")){
                        request.setAttribute("billet",b);
                        response.setStatus(200);
                        request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
                    }else{
                        try {
                            String testJson5 = Tools.parseBillet(b);

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
                    g.getBillets().remove(b);
                    response.setStatus(204);
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
                String titre = null,contenu = "",auteur = null;
                StringBuilder jb = new StringBuilder();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { }
                JsonObject jsonObject = new Gson().fromJson(jb.toString(),JsonObject.class);
                if (jsonObject!=null){
                    if (jsonObject.get("titre") != null ){
                        titre = jsonObject.get("titre").getAsString();
                    }
                    if (jsonObject.get("contenu") != null ){
                        contenu = jsonObject.get("contenu").getAsString();
                    }
                    if (jsonObject.get("auteur") != null ){
                        auteur = jsonObject.get("auteur").getAsString();
                    }
                }
                if (g.getBillet(i)!=null){
                    Billet b = g.getBillet(i);
                    if (auteur != null && !auteur.equals("")){
                        User u = new User();
                        u.pseudo=auteur;
                        b.setAuteur(u);
                    }
                    if (contenu != null && !contenu.equals("")){
                        b.setContenu(contenu);
                    }
                    if (titre != null && !titre.equals("")){
                        b.setTitre(titre);
                    }
                    response.setStatus(204);
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
