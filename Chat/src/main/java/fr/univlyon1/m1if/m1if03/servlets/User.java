/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
@WebServlet(name = "User", urlPatterns = {"/User"})
public class User extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String groupe = request.getParameter("groupe");
        
        if((pseudo != null && !pseudo.equals("")) && (groupe != null && !groupe.equals(""))) {
            ServletContext contexte = request.getServletContext();
            HashMap<String, Groupe> grpMap = (HashMap)contexte.getAttribute("map");
            if(grpMap.containsKey(groupe)){
                if (!grpMap.get(groupe).isParticipant(pseudo)){
                    grpMap.get(groupe).addParticipants(pseudo);
                }
            } else {
                Groupe newGrp = new Groupe();
                newGrp.setNom(groupe);
                newGrp.setProprio(pseudo);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("groupe", groupe);
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String pseudo =(String)session.getAttribute("pseudo");
        String groupe =(String)session.getAttribute("groupe");
        
        if((pseudo != null && !pseudo.equals("")) && (groupe != null && !groupe.equals(""))) {
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

}
