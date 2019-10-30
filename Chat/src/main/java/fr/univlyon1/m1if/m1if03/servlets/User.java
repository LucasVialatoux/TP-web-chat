/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.io.IOException;
import java.util.List;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("billet",null);
        String cle = (String)request.getParameter("groupes");
        if (cle == null || cle.equals("")){
            request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
        }
        Groupe grp = Global.getNom(cle);
        session.setAttribute("groupe",grp);
        session.setAttribute("nomGroupe",cle);
        String pseudo =(String)session.getAttribute("pseudo");
        if (!grp.getListeParticipants().contains(pseudo)){
            grp.getListeParticipants().add(pseudo);
        }
        List<Billet> list = grp.getBillets();
        session.setAttribute("listeBillet",list);
        request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
    }

}
