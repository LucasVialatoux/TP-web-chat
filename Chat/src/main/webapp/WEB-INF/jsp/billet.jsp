<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>   
<!doctype html>
<html>
<head>
    <meta http-equiv="refresh" content="5;url=GestionBillet" />
    <title>Billet</title>
</head>
<body>
    <%
       List<Billet> list = (List<Billet>)session.getAttribute("listeBillet");
if (!list.isEmpty()){
    for (Billet b : list){ 
    %>
    <a href="./GestionBillet?idAffiche=<%= b.getID()%>"><%= b.getTitre()%></a>
<% }} %>
<h1>Hello <%= session.getAttribute("pseudo")%> !</h1>
<h2>Groupe : <%= session.getAttribute("nomGroupe")%> !</h2>
<%  if(session.getAttribute("billet") !=null ){
    Billet b = (Billet)session.getAttribute("billet");
    %>
    <p>Ceci est un billet de <%= b.getAuteur() %></p>
    <h3><c:out value="<%= b.getTitre()%>"/></h3>
    <div class="contenu"><%= b.getContenu()%></div>
    <hr>
<% for (String comments : b.getListeCommentaire()){ %>
    <div class="contenu"><%= comments %></div>
    <hr>
<% } %>
<form method="post" action="GestionBillet">
    <p>
        Commentaire :
        <input type="hidden" name="ID" value="<%= b.getID()%>">
        <input type="text" name="commentaire">
        <input type="submit" value="Envoyer">
    </p>
</form>
<% } %>

<p><a href="saisie.jsp">Saisir un nouveau billet</a></p>
<p><a href="Redirection">Choisir un groupe</a></p>
<p><a href="Deco">Se déconnecter</a></p>

</body>
</html>
