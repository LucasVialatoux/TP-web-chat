<%@page import="fr.univlyon1.m1if.m1if03.classes.Commentaire"%>
<%@page import="java.util.Arrays"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Groupe"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>   
<!doctype html>
<html>
<head>
    <!--<meta http-equiv="refresh" content="5;url=billets" /> -->
    <title>Billet</title>
</head>
<body>
    <%
        Billet b = (Billet)request.getAttribute("billet");
        %>
        <p> titre = <%= b.getTitre()%></p>
        <p> contenu = <%=b.getContenu()%></p>
        <p> auteur = <%=b.getAuteur().pseudo%></p>
        <p> liste des commentaires :</p>
        <% for (Commentaire c : b.getListeCommentaire()) { %>
                <p><%=c.getAuteur().pseudo%> :<%= c.getTexte()%></p>
        <% } %>
</body>
</html>
