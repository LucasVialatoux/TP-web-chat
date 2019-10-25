<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="myBean" type="fr.univlyon1.m1if.m1if03.classes.Groupe" scope="request" />
<jsp:getProperty name="myBean" property="nom" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>${myBean.nom}</p>


    </body>
</html>
