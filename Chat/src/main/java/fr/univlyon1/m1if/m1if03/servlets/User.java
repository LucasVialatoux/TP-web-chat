/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Global;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "User")
public class User extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = (String)request.getAttribute("URI");
        String[] s = URI.split("/");
        String pseudo = s[2];
        fr.univlyon1.m1if.m1if03.classes.User user = Global.getUser(pseudo);
        if(user != null){
            request.setAttribute("User", user);
            request.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(request, response);
        } else {
            response.setStatus(204);
        }
    }
    
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = (String)request.getAttribute("URI");
        String[] s = URI.split("/");
        String pseudo1 = s[2];
        String pseudo2 = request.getParameter("pseudo");
        fr.univlyon1.m1if.m1if03.classes.User user = Global.getUser(pseudo1);
        if(user != null && pseudo2!=null && !pseudo2.isEmpty()){
            user.pseudo=pseudo2;
            Global.userList.add(user);
            response.setStatus(200);
        } else {
            response.setStatus(204);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(403);
    }
    
     @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = (String)request.getAttribute("URI");
        String[] s = URI.split("/");
        String pseudo = s[2];
        fr.univlyon1.m1if.m1if03.classes.User user = Global.getUser(pseudo);
        response.setStatus(204);
        if(user != null){
            Global.userList.remove(user);
            
        }
    }
}