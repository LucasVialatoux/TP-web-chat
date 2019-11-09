/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.classes;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas
 */
public class FiltreConnexion implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        String URI=request.getRequestURI();
        String[] s=URI.split("/");
        request.setAttribute("URI", URI);
        
        String pseudo=null;
        String token = request.getHeader("Authorization");
        if (token!=null) {
            pseudo=JWToken.decodeJWT(token);
            if(pseudo!=null){
                request.setAttribute("pseudo", pseudo);
            }else{
                resp.sendRedirect("/users");
            }
        }
        if(pseudo!=null && s[1].equals("groupes")){
            fc.doFilter(sr, sr1);
        }else{
            if (s.length>1 && s[1].equals("users")) {
                if (s.length>2 && s[2].equals("login")) {
                    request.getRequestDispatcher("/login").forward(request, resp);
                    return;
                }else if(s.length>2 && s[2].equals("logout")){
                    request.getRequestDispatcher("/logout").forward(request, resp);
                    return;
                }else if(s.length>2 && !s[2].equals("")){
                    request.getRequestDispatcher("/user").forward(request, resp);
                    return;
                }else{
                    request.getRequestDispatcher("/users").forward(request, resp);
                    return;
                }
            }else{
                resp.sendRedirect("/users");
            }
        }
    }
    
}
