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
public class Routeur implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        String URI=request.getRequestURI();
        String[] s=URI.split("/");
        request.setAttribute("URI", URI);
        if (s.length>1 && s[1].equals("groupes")) {
            if(s.length>2 && !s[2].equals("")){
                if(s.length>3 && s[3].equals("groupes")){
                    request.getRequestDispatcher("/groupes").forward(request, resp);
                    return;
                }else{
                    if(s.length>3 ){
                        resp.setStatus(404);
                        return;
                    }else{
                        request.getRequestDispatcher("/user").forward(request, resp);
                    }
                }
            }else{
                request.getRequestDispatcher("/users").forward(request, resp);
                return;
            }
       }else{
            fc.doFilter(sr, sr1);
        }
    }
    
    private Billet ajoutBillet(String[] s,HttpServletRequest request,Groupe grp) throws IOException{
        Billet billet = new Billet();
        billet.setContenu(request.getParameter("contenu"));
        billet.setTitre(request.getParameter("titre"));
        //billet.setAuteur(s[2]);
        grp.add(billet);
        return billet;
    }
    
    private void ajoutGroupe(String[] s,HttpServletRequest request){
        if ((String)request.getParameter("groupe")!=null){
            String pseudo =s[2];
            ArrayList list = new ArrayList();
            list.add(pseudo);
            //new Groupe((String)request.getParameter("groupe"), (String)request.getParameter("desc"), pseudo, list);
        }
    }
}
