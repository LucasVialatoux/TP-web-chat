/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univlyon1.m1if.m1if03.classes;

import java.io.IOException;
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
public class Routeur implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        String URI=request.getRequestURI();
         int status=0;
        if(request.getAttribute("status")!=null){
            status =(int)request.getAttribute("status");
        }
        String[] s=URI.split("/");
        request.setAttribute("URI", URI);
        if (s.length>2 && s[2].equals("groupes") && status!=401) {
            if(s.length>3 && !s[3].equals("")){
                if(s.length>4 && s[4].equals("billets")){
                    if(s.length>5 && !s[5].equals("")){
                        if(s.length>6 && s[6].equals("commentaires")){
                            if(s.length>7 && !s[7].equals("")){
                                if(s.length>8){
                                    resp.setStatus(404);
                                }else{
                                    request.getRequestDispatcher("/commentaire").forward(request, resp);
                                    return;
                                }
                            }else{
                                request.getRequestDispatcher("/commentaires").forward(request, resp);
                                return;
                            }
                        }else{
                            if(s.length>6 ){
                                resp.setStatus(404);
                            }else{
                                request.getRequestDispatcher("/billet").forward(request, resp);
                                return;
                            } 
                        }
                    }else{
                        request.getRequestDispatcher("/billets").forward(request, resp);
                        return;
                    }
                }else{
                    if(s.length>4 ){
                        resp.setStatus(404);
                    }else{
                        request.getRequestDispatcher("/groupe").forward(request, resp);
                        return;
                    }
                }     
                }else{
                    request.getRequestDispatcher("/groupes").forward(request, resp);
                    return;
                }
       }else{
            fc.doFilter(sr, sr1);
        }
    }
}
