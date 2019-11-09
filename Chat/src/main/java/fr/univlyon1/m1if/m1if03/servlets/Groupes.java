package fr.univlyon1.m1if.m1if03.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
@WebServlet(name = "groupes", urlPatterns = "/groupes")
public class Groupes extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/groupes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(403);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI = (String)request.getAttribute("URI");
        String[] s = URI.split("/");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String proprietaire = request.getParameter("proprietaire");
        if(nom!=null && !nom.equals("") ){
            String pseudo =s[2];
            ArrayList list = new ArrayList();
            list.add(pseudo);
            //new Groupe((String)request.getParameter("groupe"), (String)request.getParameter("desc"), pseudo, list);
        }
    }

}
