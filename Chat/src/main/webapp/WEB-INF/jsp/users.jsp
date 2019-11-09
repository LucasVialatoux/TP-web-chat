<%@page import="fr.univlyon1.m1if.m1if03.classes.User"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste d'utilisateurs</title>
    </head>
    <body>  
        <% for ( User u: Global.userList) { %>
        <p><%= request.getScheme()%>://<%= request.getServerName()%>:<%=request.getServerPort()%>/users/<%= u.pseudo%></p>
        <%}; %>
    </body>
</html>
