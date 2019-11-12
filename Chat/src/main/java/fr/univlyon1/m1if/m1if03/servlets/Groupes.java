package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.User;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import fr.univlyon1.m1if.m1if03.classes.Tools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
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
 * @author lucas
 */

public class Groupes extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getHeader("Accept").equals("text/html")){
            request.getRequestDispatcher("WEB-INF/jsp/groupes.jsp").forward(request, response);
        }else{
            ArrayList<String> liste=new ArrayList();
            for (Map.Entry map : Global.groupe.entrySet()) { 
                Groupe g = (Groupe)map.getValue();
                String s=request.getScheme()+"://"+request.getServerName()+":"
                        +request.getServerPort()+"/groupes/"+g.getNom();
                liste.add(s);
            }
            
            try {
                String testJson5 = Tools.parseListe(liste);
        
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
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = null,description = null;
        if(request.getHeader("Content-Type")!=null && 
                            request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
            nom = request.getParameter("nom");
            description = request.getParameter("description");
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
                if (jo.get("nom") != null ){
                    if(jo.get("nom")!=null){
                        nom = jo.get("nom").getAsString();
                    }
                    if(jo.get("description")!=null){
                        description = jo.get("description").getAsString();
                    }
                }  
            }
        }
        if(description==null){
            description="";
        }
        User user = (User)request.getAttribute("user");
        if(nom!=null && !nom.equals("") && Global.getNom(nom)==null ){
            ArrayList list = new ArrayList();
            list.add(user);
            new Groupe(nom, description, user, list);
            response.setStatus(201);
        }else{
            if(Global.getNom(nom)!=null){
                response.setStatus(304);
            }else{
                response.setStatus(400);
            }
        }
    }

}
