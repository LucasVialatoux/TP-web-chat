<%@page import="fr.univlyon1.m1if.m1if03.classes.User"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utilisateur</title>
    </head>
    <body>  
        <% User user=(User)request.getAttribute("User");%>
        <p>pseudo = <%= user.pseudo %></p>
    </body>
</html>
