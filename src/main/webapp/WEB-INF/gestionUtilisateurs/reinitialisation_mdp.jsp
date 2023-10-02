<!-- HEADER -->
<%@ include file="../outils/entete.jspf"%>

<body>

	<!-- MENU -->
	<%@ include file="../outils/menu.jsp"%>
	
    <div class="container-fluid">
        <div class="row">

            <!-- Contenu principal -->
			<main class="col-md-12 ms-sm-auto" style="min-height: 50vh; display: flex; flex-direction: column; justify-content: center; align-items: center;">
			    
			    <h1 class="my-5 text-center">Réinitialisation de votre Mot de Passe</h1>
			    
			    <form method="post" action="<%=request.getContextPath()%>/reinitialisation_mdp" class="mb-3" onsubmit="return validerFormulaire();">
			
					<div class="row">
	    				<div class="col-md-12">
					        <div class="mb-2">
					            <label for="newPwd"><strong>Choisissez votre Nouveau Mot de Passe :</strong></label>
					        </div>
					        <div class="mb-2">
					         	<input type="hidden" name="numid" id="numid" value="">
					            <input type="password" name="newPwd" class="form-control" placeholder="Mot de Passe" value="">
					        </div>
					        <div class="mb-3">
					            <label for="newPwd"><i>8 caractères minimum</i></label>
					        </div>
						</div>
				    </div>
				    <div class="row">
	    				<div class="col-md-12">    
				            <div class="mb-2">
				                <label for="newPwd"><strong>Confirmer votre Nouveau Mot de Passe :</strong></label>
				            </div>
				            <div class="mb-2">
				                <input type="password" name="verifNewPwd" class="form-control" placeholder="Mot de Passe" value="">
				            </div>
				            <div class="mb-4">
					            <label for="newPwd"><i>8 caractères minimum</i></label>
					        </div>
				        </div>
				    </div>
				    
				    <div class="row">
					    <div class="col-md-6">
					        <div class="mb-3">
					            <button type="button" onclick="effacerChamps()" class="btn btn-danger btn-lg">Effacer Tout</button>
					        </div>
					    </div>
					    <div class="col-md-6">
					        <div class="mb-3">
					            <input type="submit" name="reinitMdp" value="Confirmer" class="btn btn-success btn-lg">
					        </div>
					    </div>
					</div>
				    
			    </form>
			    
			</main>
            
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="../outils/footer.jspf"%>
    
    <script>
	    function effacerChamps() {
	        document.getElementsByName("newPwd")[0].value = "";
	        document.getElementsByName("verifNewPwd")[0].value = "";
	    }
	</script>
	
	<script>
	
        // Fonction pour extraire le paramètre "numid" de l'URL
        function getParamFromURL(paramName) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(paramName);
        }

        // Récupérer la valeur du paramètre "numid" de l'URL
        const numidValue = getParamFromURL('numid');

        // Mettre la valeur dans l'input hidden
        document.getElementById('numid').value = numidValue;
        
    </script>
    
    <script>
    
	    function effacerChamps() {
	        document.getElementsByName("newPwd")[0].value = "";
	        document.getElementsByName("verifNewPwd")[0].value = "";
	    }
	
	    function validerFormulaire() {
	        var newPwd = document.getElementsByName("newPwd")[0].value;
	        var verifNewPwd = document.getElementsByName("verifNewPwd")[0].value;

	        if (newPwd !== verifNewPwd) {
	            alert("Les mots de passe ne correspondent pas. Veuillez les saisir à nouveau.");
	            return false; // Empêche l'envoi du formulaire si les mots de passe ne correspondent pas.
	        }

	        if (newPwd.length < 8) {
	            alert("Le mot de passe doit contenir au moins 8 caractères.");
	            return false; // Empêche l'envoi du formulaire si le mot de passe est trop court.
	        }
	        
	        return true; // Permet l'envoi du formulaire si les mots de passe correspondent et ont au moins 8 caractères.
	    }
	    
	</script>
    
</body>
</html>
