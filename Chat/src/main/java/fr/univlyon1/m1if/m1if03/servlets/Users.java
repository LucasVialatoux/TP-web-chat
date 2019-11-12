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
import fr.univlyon1.m1if.m1if03.classes.Tools;
import fr.univlyon1.m1if.m1if03.classes.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */

public class Users extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getHeader("Accept").equals("text/html")){
            request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
        }else{
            ArrayList<String> liste=new ArrayList();
            for(User u : Global.userList){
                String s=request.getScheme()+"://"+request.getServerName()+":"
                        +request.getServerPort()+"/users/"+u.pseudo;
                liste.add(s);
            }
            
            try {
                String testJson5 = Tools.parseListe(liste);
        
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = new User();
        String pseudo=null;
        if(request.getHeader("Content-Type")!=null 
                && request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
            pseudo = request.getParameter("pseudo");
            
        }else{
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                jb.append(line);
            } catch (Exception e) {}

            JsonObject jo = new Gson().fromJson(jb.toString(), JsonObject.class);
            if(jo!=null){
               if(jo.get("pseudo")!=null){
                pseudo = jo.get("pseudo").getAsString();
                } 
            }
        }
        if(pseudo!=null && !pseudo.equals("")){
                User user=new User();
                user.pseudo=pseudo;
                Global.userList.add(user);
                response.setStatus(201);
            } 
            else if(Global.getUser(pseudo) != null){
                response.setStatus(304);
            }else{
                response.setStatus(400);
            }
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(403);
    }
    

}
