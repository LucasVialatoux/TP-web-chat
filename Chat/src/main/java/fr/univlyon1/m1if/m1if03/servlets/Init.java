package fr.univlyon1.m1if.m1if03.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Init", urlPatterns = "/Init")
public class Init extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        if(pseudo != null && !pseudo.equals("")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("pseudo", pseudo);
            request.getRequestDispatcher("billet.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}
