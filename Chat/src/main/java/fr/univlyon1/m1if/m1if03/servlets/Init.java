package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.GestionBillets;
import fr.univlyon1.m1if.m1if03.classes.Groupe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

@WebServlet(name = "Init", urlPatterns = "/Init")
public class Init extends HttpServlet {
    
    public void init(ServletConfig config,HttpServletRequest request){
        ServletContext contexte= request.getServletContext();
        HashMap<String, Groupe> groupes = new HashMap();
        contexte.setAttribute("map", groupes);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String groupe = request.getParameter("groupe");
        
        if((pseudo != null && !pseudo.equals("")) && (groupe != null && !groupe.equals(""))) {
            HttpSession session = request.getSession(true);
            session.setAttribute("pseudo", pseudo);
            session.setAttribute("groupe", groupe); 
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
