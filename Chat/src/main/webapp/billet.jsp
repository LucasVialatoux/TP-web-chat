<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<%! private Billet billet = new Billet(); %>
<% if (request.getMethod().equals("POST")) {
    billet.setContenu(request.getParameter("contenu"));
    billet.setTitre(request.getParameter("titre"));
    billet.setAuteur((String) session.getAttribute("pseudo"));
} %>
<!doctype html>
<html>
<head>
    <title>Billet</title>
</head>
<body>
<h2>Hello <%= session.getAttribute("pseudo")%> !</h2>
<p>Ceci est un billet de <%= billet.getAuteur() %></p>
<h1><c:out value="<%= billet.getTitre()%>"/></h1>
<div class="contenu"><%= billet.getContenu()%></div>
<hr>
<form method="post" action="billet.jsp">
    <p>
        Commentaire :
        <input type="text" name="commentaire">
        <input type="submit" value="Envoyer">
    </p>
</form>
<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>
