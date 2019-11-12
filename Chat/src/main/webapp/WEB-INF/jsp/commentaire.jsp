<%@page import="fr.univlyon1.m1if.m1if03.classes.Commentaire"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>   
<!doctype html>
<html>
<head>
    <!--<meta http-equiv="refresh" content="5;url=billets" /> -->
    <title>commentaire</title>
</head>
<body>

<% Commentaire c=(Commentaire)request.getAttribute("commentaire");%>
        <p> auteur = <%= (String)c.getAuteur().pseudo%></p>
        <p> texte = <%= (String)c.getTexte()%></p>
</body>
</html>
