<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
    <form method="post" action="<%=request.getContextPath()%>/TestUtilisateurs">
        <p>
            <label for="nom">Nom : </label>
            <input type="text" name="nom" id="nom" />
        </p>
        <p>
            <label for="prenom">Prénom : </label>
            <input type="text" name="prenom" id="prenom" />
        </p>
        <p>
            <label for="mot de passe">Mot de passe : </label>
            <input type="text" name="mot de passe" id="mot de passe" />
        </p>
        
        <input type="submit" />
    </form>    
</body>
</html>