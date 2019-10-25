<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="myBean" type="fr.univlyon1.m1if.m1if03.classes.Groupe" scope="request" />
<jsp:getProperty name="myBean" property="nom" />

<jsp:useBean id="groupes" type="fr.univlyon1.m1if.m1if03.modeles.GroupeModele" scope="page" />
<jsp:getProperty name="groupes" property="nom" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groupe</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>${myBean.nom}</p>
        
        <form method="post" action="User">
    
            Entrez votre groupe :
            <input type="text" name="groupe">
            <br><br>
            <label for="grp-select">Ou sélectionner un groupe:</label>
            <select name="groupes" id="grp-select">
                <option value="grp1">Grp1</option>
                <option value="grp2">Grp2</option>
                <option value="grp3">Grp3</option>
                <option value="grp4">Grp4</option>
                <option value="grp5">Grp5</option>
                <option value="grp6">Grp6</option>
            </select>
            <input type="submit" value="Envoyer">
    
        </form>
        

    </body>
</html>
