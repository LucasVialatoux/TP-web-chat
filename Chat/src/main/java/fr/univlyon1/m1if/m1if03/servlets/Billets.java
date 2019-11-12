package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;

public class Billets extends HttpServlet {
    
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
                ArrayList<String> liens=new ArrayList();
                for(Billet b : g.getBillets()){
                    String lien = request.getScheme()+"://"+request.getServerName()+":"
                        +request.getServerPort()+"/v2/groupes/"+s[3]+"/billets/"+b.getID();
                    liens.add(lien);
                }
                if(request.getHeader("Accept").equals("text/html")){
                    request.setAttribute("liens",liens);
                    response.setStatus(200);
                    request.getRequestDispatcher("WEB-INF/jsp/billets.jsp").forward(request, response);
                }else{
                     try {
                        String testJson5 = Tools.parseListe(liens);

                        ObjectMapper mapper5 = new ObjectMapper();
                        Object jo = mapper5.readValue(testJson5, Object.class);
                        String prettyJson = mapper5.writerWithDefaultPrettyPrinter().writeValueAsString(jo);
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
                String auteur = null, titre = null, contenu = null;
                if(request.getHeader("Content-Type")!=null && 
                            request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
                        titre = request.getParameter("titre");
                        contenu = request.getParameter("contenu");
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
                        if (jo.get("auteur") != null){
                            auteur = jo.get("auteur").getAsString();
                        }
                        if (jo.get("titre") != null){
                            titre = jo.get("titre").getAsString();
                        }
                        if (jo.get("contenu") != null){
                            contenu = jo.get("contenu").getAsString();
                        }
                        
                    }
                }
                if(contenu==null){
                    contenu="";
                }
                User u=new User();
                if(titre!=null && auteur!= null){
                    u.pseudo=auteur;
                    Billet b = new Billet(titre,contenu,u);
                    g.add(b);
                    response.setStatus(201);
                }else{
                    response.setStatus(400);   
                }
            }else{
                response.setStatus(403);
            } 
        }else{
            response.setStatus(404);
        }
    }
}
