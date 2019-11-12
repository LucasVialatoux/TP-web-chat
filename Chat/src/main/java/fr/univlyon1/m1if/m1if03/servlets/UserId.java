/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univlyon1.m1if.m1if03.classes.Global;
import fr.univlyon1.m1if.m1if03.classes.Tools;
import fr.univlyon1.m1if.m1if03.classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

/**
 *
 * @author antoine dulhoste
 */

public class UserId extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URI=(String)request.getAttribute("URI");
        String[] s=URI.split("/");
        User u =Global.getUser(s[3]);
        if(u!=null){
            if(request.getHeader("Accept").equals("text/html")){
                request.setAttribute("user", u);
                response.setStatus(200);
                request.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(request, response);
            }else{
                try {
                    String testJson5 = Tools.parseUser(u);

                    ObjectMapper mapper5 = new ObjectMapper();
                    Object jsonObject = mapper5.readValue(testJson5, Object.class);
                    String prettyJson = mapper5.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(prettyJson);
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }else{
            response.setStatus(404);
        }
        
    }
}
