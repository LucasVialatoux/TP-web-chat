<%@page import="java.util.ArrayList"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Commentaire"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.univlyon1.m1if.m1if03.classes.Billet" %>   
<!doctype html>
<html>
<head>
    <!--<meta http-equiv="refresh" content="5;url=billets" /> -->
    <title>commentaires</title>
</head>
<body>
    <%
        ArrayList<String> list = (ArrayList<String>)request.getAttribute("liens");
        %>
        <%for(String s : list){%>
        <p><%=s%></p>
        <%}; %>
</body>
</html>
