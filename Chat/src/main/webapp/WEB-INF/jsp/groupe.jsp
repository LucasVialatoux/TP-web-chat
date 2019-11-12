<%@page import="fr.univlyon1.m1if.m1if03.classes.User"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Groupe"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Billet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groupe</title>
    </head>
    <body>
        <% Groupe g=(Groupe)request.getAttribute("groupe");%>
        <p> nom = <%= (String)g.getNom()%></p>
        <p> description = <%= (String)g.getDescription()%></p>
        <p> proprietaire = <%= (String)g.getAuteur().pseudo%></p>
        <p> liste des membres :</p>
        <% for (User u : g.getMembres()) { %>
                <p>pseudo = <%= u.pseudo%></p>
        <% } %>
        <p> liste des billets :</p>
        <% for (Billet b : g.getBillets()) { %>
                <p> billet = <%= b.getTitre()%></p>
        <% } %>
    </body>
</html>
