<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>
<% if (request.getMethod().equals("POST")) {
    if (request.getParameter("ID")!=null) {
        Billet b = Global.gb.getBillet(Integer.parseInt(request.getParameter("ID")));
        String s = session.getAttribute("pseudo")+": "+request.getParameter("commentaire");
        b.getListeCommentaire().add(s);
    } else if (request.getParameter("titre")!=null){
        Billet billet = new Billet();
        billet.setContenu(request.getParameter("contenu"));
        billet.setTitre(request.getParameter("titre"));
        billet.setAuteur((String) session.getAttribute("pseudo"));
        billet.setGroupe((String) session.getAttribute("groupe"));
        Global.gb.add(billet);
    }
} %>
<!doctype html>
<html>
<head>
    <!--<meta http-equiv="refresh" content="5;url=/Init" /> --> 
    <title>Billet</title>
</head>
<body>
<h1>Hello <%= session.getAttribute("pseudo")%> !</h1>
<h2>Groupe : <%= session.getAttribute("groupe")%> !</h2>
<%
    String groupe = (String)session.getAttribute("groupe");
if (!Global.gb.getBilletsByGrp(groupe).isEmpty()){
    for (Billet b : Global.gb.getBilletsByGrp(groupe)){ 
    %>
    <p>Ceci est un billet de <%= b.getAuteur() %></p>
    <h3><c:out value="<%= b.getTitre()%>"/></h3>
    <div class="contenu"><%= b.getContenu()%></div>
    <hr>
<% for (String s : b.getListeCommentaire()){ %>
    <div class="contenu"><%= s %></div>
    <hr>
<% } %>
<form method="post" action="billet.jsp">
    <p>
        Commentaire :
        <input type="hidden" name="ID" value="<%= b.getID()%>">
        <input type="text" name="commentaire">
        <input type="submit" value="Envoyer">
    </p>
</form>
<% }} %>

<p><a href="saisie.html">Saisir un nouveau billet</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>
