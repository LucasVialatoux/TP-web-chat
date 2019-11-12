/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.univlyon1.m1if.m1if03.classes.JWToken;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pseudo=null;
        if(request.getHeader("Content-Type")!=null && request.getHeader("Content-Type").equals("application/x-www-form-urlencoded")){
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
            String token = JWToken.createJWT(pseudo);
            response.setHeader("Authorization",token);
            response.setStatus(201);
        } else {
            response.setStatus(400);
        }
    }

}
