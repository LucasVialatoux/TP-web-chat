<%@page import="java.util.Map"%>
<%@page import="fr.univlyon1.m1if.m1if03.classes.Global"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groupe</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>${myBean.nom}</p>
        
        <form method="post" action="GestionGroupe">
            Entrez votre groupe :
            <input type="text" name="groupe">
            <br>
            <br>
            Entrez la description :
            <input type="text" name="desc">
            <input type="submit" value="Envoyer">
        </form>
        <br>
        <form method="post" action="User">
            <br><br>
            <label for="grp-select">Ou sélectionner un groupe:</label>
            
            <select name="groupes" id="grp-select">
                <% for (Map.Entry map : Global.groupes.entrySet()) { %>
                <option value="<%= map.getKey()%>"><%= map.getKey()%></option>
                <%}; %>
            </select>
            <input type="submit" value="Envoyer">
    
        </form>
        

    </body>
</html>
