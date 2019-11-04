package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Groupe;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "GestionGroupe", urlPatterns = "/GestionGroupe")
public class GestionGroupe extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String pseudo =(String)session.getAttribute("pseudo");
        ArrayList list = new ArrayList();
        list.add(pseudo);
        new Groupe((String)request.getParameter("groupe"), (String)request.getParameter("desc"), pseudo, list);
        request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
    }

}
