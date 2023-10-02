<!-- HEADER -->
<%@ include file="../outils/entete.jspf"%>

<body>

	<!-- MENU -->
	<%@ include file="../outils/menu.jsp"%>
	
    <div class="container-fluid">
        <div class="row">

            <!-- Contenu principal -->
			<main class="col-md-12 ms-sm-auto" style="min-height: 50vh; display: flex; flex-direction: column; justify-content: center; align-items: center;">
			    
			    <h1 class="my-5 text-center">Félicitation ! Votre Mot de Passe a bien été réinitialisé.</h1>
			    
			    <h3 class="my-5 text-center">Cliquez sur le bouton ci-dessous pour aller sur la Page d'accueil</h3>
			    
			    <form method="post" action="<%=request.getContextPath()%>/reinitialisation_mdp">
			    
			    	<input type="hidden" name="pseudoUser" id="pseudoUser" value="<%= request.getAttribute("pseudoUser") %>">
			    	<input type="hidden" name="mdpUser" id="mdpUser" value="<%= request.getAttribute("mdpUser") %>">

			    	<input type="submit" name="goToHome" class="btn btn-primary btn-lg" value="www.ecolotroc.com">
			    
			    </form>
			    
			    <h5 class="my-5 text-center">Une fois sur la page d'accueil, votre compte sera connecté automatiquement !</h5>
			    
			</main>
            
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="../outils/footer.jspf"%>
    
</body>
</html>
