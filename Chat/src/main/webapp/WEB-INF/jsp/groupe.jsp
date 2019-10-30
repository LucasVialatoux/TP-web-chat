<%@page import="fr.univlyon1.m1if.m1if03.classes.Groupe"%>
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
            <label for="grp-select">Puis sélectionnez un groupe:</label>
            
            <% for (Map.Entry map : Global.groupes.entrySet()) { 
            Groupe g = (Groupe)map.getValue();
            %>
            <br>
            <input type="radio" id="dewey" name="groupes" value="<%= map.getKey()%>">
            <label for="groupes"><%= map.getKey()%>
                Description : <%= (String)g.getDescription()%>
                Propriétaire : <%= (String)g.getProprio()%>
            </label>
            
            <%}; %>
            </br>
            <input type="submit" value="Envoyer">
    
        </form>
        

    </body>
</html>
