<%@page import="fr.univlyon1.m1if.m1if03.classes.Groupe"%>
<%@page import="java.util.Map"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste de groupes</title>
    </head>
    <body>
        <% for (Map.Entry map : Global.groupe.entrySet()) { 
        Groupe g = (Groupe)map.getValue();
        %>
        <p><%= request.getScheme()%>://<%= request.getServerName()%>:<%=request.getServerPort()%>/groupes/<%= g.getNom()%></p>
        <%}; %>
        

    </body>
</html>
