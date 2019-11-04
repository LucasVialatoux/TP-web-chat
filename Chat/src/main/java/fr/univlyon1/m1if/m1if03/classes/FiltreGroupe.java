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
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
public class FiltreGroupe implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        HttpSession session = request.getSession(true);
        Groupe groupe =(Groupe)session.getAttribute("groupe");
        String pseudo =(String)session.getAttribute("pseudo");
        if (groupe != null){
            ArrayList list = groupe.getListeParticipants();
            if (list.contains(pseudo)){
                fc.doFilter(sr, sr1);
            }
        }else {
           resp.sendRedirect("GestionGroupe"); 
        }
    }
    
}
