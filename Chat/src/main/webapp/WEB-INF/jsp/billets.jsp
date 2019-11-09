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
    <title>Liste de billets</title>
</head>
<body>
    <%
       String URI=(String)request.getAttribute("URI");
       String[] s=URI.split("/");
       Groupe g = Global.getNom(s[4]);
       if (g != null){
       List<Billet> list = g.getBillets();
if (!list.isEmpty()){
    for (Billet b : list){ 
    %>
    <a href="./<%= b.getID()%>"><%= b.getTitre()%></a>
<% }} %>
<h1>Hello <%= s[2]%>
<h2>Groupe : <%= s[4] %> !</h2>
<%  if(s.length >6 && !list.get(1).equals(null) ){
    Billet b = list.get(0);
    %>
    <p>Ceci est un billet de <%= b.getAuteur() %></p>
    <h3><c:out value="<%= b.getTitre()%>"/></h3>
    <div class="contenu"><%= b.getContenu()%></div>
    <hr>
<% for (String comments : b.getListeCommentaire()){ %>
    <div class="contenu"><%= comments %></div>
    <hr>
<% } %>

</body>
</html>
