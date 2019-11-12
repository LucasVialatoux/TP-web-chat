/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Tools;
import fr.univlyon1.m1if.m1if03.classes.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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

public class GroupeId extends HttpServlet {
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        Groupe g=Global.getNom(s[3]);
        if(g!=null){
            if(request.getHeader("Accept").equals("text/html")){
                request.setAttribute("groupe", g);
                response.setStatus(200);
                request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
            }else{
                try {
                    String testJson5 = Tools.parseGroupe(g);

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
        
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        Groupe g=Global.getNom(s[3]);
        if(g!=null){
            Global.groupe.remove(s[3]);
            response.setStatus(204);
        }else{
            response.setStatus(404);
        }
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        Groupe g = Global.getNom(s[3]);
        if (g!=null){
            String nom = null,description = "",proprietaire = null; 
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) { }
            JsonObject jo = new Gson().fromJson(jb.toString(),JsonObject.class);
            if (jo!=null){
                if (jo.get("nom") != null ){
                    nom = jo.get("nom").getAsString();
                }
                if(jo.get("proprietaire")!=null){
                    proprietaire = jo.get("proprietaire").getAsString();
                }
                if (jo.get("description")!=null){
                    description = jo.get("description").getAsString();
                }
            }

            User u = Global.getUser(proprietaire);
            if(nom!=null){
                Global.groupe.remove(s[3]);
                Global.groupe.put(nom, g);
                g.setNom(nom);
            }
            if(description!=null){
                g.setDescription(description);
            }
            if(u!=null){
                g.setAuteur(u);
            }
            response.setStatus(204);
        } else {
            response.setStatus(404);
        }
    }

}
