<%@page import="fr.univlyon1.m1if.m1if03.classes.Groupe"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groupe</title>
    </head>
    <body>
        <% Groupe g=(Groupe)request.getAttribute("Groupe");%>
        <p> nom = <%= (String)g.getNom()%></p>
        <p> description = <%= (String)g.getDescription()%></p>
        <p> proprietaire = <%= (String)g.getProprio()%></p>
        

    </body>
</html>
