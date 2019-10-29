package fr.univlyon1.m1if.m1if03.servlets;

import fr.univlyon1.m1if.m1if03.classes.Global;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

@WebServlet(name = "Init", urlPatterns = "/Init")
public class Init extends HttpServlet {
    
    public void init(ServletConfig config,HttpServletRequest request){
        ServletContext contexte= request.getServletContext();
        contexte.setAttribute("map", Global.groupes);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        
        if(pseudo != null && !pseudo.equals("")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("pseudo", pseudo);
            request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String pseudo =(String)session.getAttribute("pseudo");
        
        if(pseudo != null && !pseudo.equals("")) {
            request.getRequestDispatcher("WEB-INF/jsp/groupe.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
}
