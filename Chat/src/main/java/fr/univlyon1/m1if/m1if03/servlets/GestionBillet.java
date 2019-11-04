/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Billet;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
public class GestionBillet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Groupe grp = (Groupe)session.getAttribute("groupe");
        if (request.getParameter("idAffiche")!=null) {
            int id =Integer.parseInt(request.getParameter("idAffiche"));
            Billet billet = grp.getBillet(id);
            session.setAttribute("billet",billet);
        }
        request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Groupe grp = (Groupe)session.getAttribute("groupe");
        if (request.getParameter("titre")!=null){
            Billet billet = new Billet();
            billet.setContenu(request.getParameter("contenu"));
            billet.setTitre(request.getParameter("titre"));
            billet.setAuteur((String) session.getAttribute("pseudo"));
            session.setAttribute("billet",billet);
            grp.add(billet);
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM YYYY hh:mm:ss z");  
            String strDate = dateFormat.format(date);
            response.setHeader("Last-Modified", strDate);
        }
        if (request.getParameter("ID")!=null) {
            Billet b = grp.getBillet(Integer.parseInt(request.getParameter("ID")));
            String s = session.getAttribute("pseudo")+": "+request.getParameter("commentaire");
            b.getListeCommentaire().add(s);
        }
        request.getRequestDispatcher("WEB-INF/jsp/billet.jsp").forward(request, response);
    }
}
