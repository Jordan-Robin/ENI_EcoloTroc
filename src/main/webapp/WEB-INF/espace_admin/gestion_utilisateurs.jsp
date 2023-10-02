<!-- HEADER -->
<%@ include file="includes/entete.jspf"%>

<body>

	<!-- MENU -->
	<%@ include file="includes/menu.jspf"%>
	
    <div class="container-fluid">
        <div class="row">
        
            <!-- MENU ADMIN -->
			<%@ include file="includes/menu_admin.jspf"%>

            <!-- Contenu principal -->
            <main class="col-md-10 ms-sm-auto">
            
                <h1 class="my-2">Espace Administrateur</h1>
                <h4 class="my-1">-> Gestion des Utilisateurs</h4><br>
                	
				<table class="table">
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Pseudo</th>
							<th scope="col">Nom</th>
							<th scope="col">Prénom</th>
							<th scope="col">E-Mail</th>
							<th scope="col">Administrateur</th>
							<th scope="col">Activation</th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col"></th>
						   </tr>
					</thead>
					<tbody>
						<c:forEach var="utilisateurs" items="${utilisateurs}">
							<tr>
							    <th scope="row"><c:out value="${utilisateurs.identifiant}" /></th>
							    <td><c:out value="${utilisateurs.pseudo}" /></td>
								<td><c:out value="${utilisateurs.nom}" /></td>
							 	<td><c:out value="${utilisateurs.prenom}" /></td>
								<td><c:out value="${utilisateurs.email}" /></td>
								<td>
								
								    <input type="checkbox" id="adminSwitch-${utilisateurs.identifiant}" data-on-text="ON" data-off-text="OFF" 
								    	data-on-color="success" data-off-color="danger" ${utilisateurs.administrateur ? 'checked' : ''} 
								        data-servlet-url="<%=request.getContextPath()%>/espace_admin/gestion_utilisateurs" 
								        data-utilisateur-id="${utilisateurs.identifiant}" name="adminSwitch" />
								        
								    <script>
								    	$(document).ready(function() {
								
								           	$('#adminSwitch-${utilisateurs.identifiant}').bootstrapSwitch();
								
								           	$('#adminSwitch-${utilisateurs.identifiant}').on('switchChange.bootstrapSwitch', function(event, state) {
								           	
								               	// Cette fonction sera exécutée lorsque l'utilisateur coche ou décoche la case à cocher.
								               	var servletURL = $(this).data('servlet-url');
								               	var utilisateurId = $(this).data('utilisateur-id');
												
								                // Envoie une requête Ajax au servlet avec l'identifiant de l'utilisateur et l'état de la case à cocher	                
								                console.log('servletURL:', servletURL);
												console.log('utilisateurId:', utilisateurId);
												console.log('state:', state);
								                
								                $.ajax({
								                    type: "POST",
								                    url: servletURL,
								                    data: {
								                        utilisateurId: utilisateurId,
								                        adminSwitch: state
								                    },
								                    success: function(response) {
								                    	 console.log('Requête réussie, réponse du serveur');
								                    },
								                    error: function(jqXHR, textStatus, errorThrown) {
								                    	console.error('Erreur lors de la requête :', errorThrown);
								                    }
								                });
								               	
								           	});
								       	});
								   	</script>    
								        
								</td>
								<td>
								
									<input type="checkbox" id="activateSwitch-${utilisateurs.identifiant}" data-on-text="ON" data-off-text="OFF" 
										data-on-color="success" data-off-color="danger" ${utilisateurs.activate ? 'checked' : ''} 
								        data-servlet-url="<%=request.getContextPath()%>/espace_admin/gestion_utilisateurs" 
								        data-utilisateur-id="${utilisateurs.identifiant}" name="activateSwitch" />
								        
								    <script>
								    	$(document).ready(function() {
								
								           	$('#activateSwitch-${utilisateurs.identifiant}').bootstrapSwitch();
								
								           	$('#activateSwitch-${utilisateurs.identifiant}').on('switchChange.bootstrapSwitch', function(event, state) {
								           	
								               	// Cette fonction sera exécutée lorsque l'utilisateur coche ou décoche la case à cocher.
								               	var servletURL = $(this).data('servlet-url');
								               	var utilisateurId = $(this).data('utilisateur-id');
												
								                // Envoie une requête Ajax au servlet avec l'identifiant de l'utilisateur et l'état de la case à cocher	                
								                console.log('servletURL:', servletURL);
												console.log('utilisateurId:', utilisateurId);
												console.log('state:', state);
								                
								                $.ajax({
								                    type: "POST",
								                    url: servletURL,
								                    data: {
								                        utilisateurId: utilisateurId,
								                        activateSwitch: state
								                    },
								                    success: function(response) {
								                    	 console.log('Requête réussie, réponse du serveur');
								                    },
								                    error: function(jqXHR, textStatus, errorThrown) {
								                    	console.error('Erreur lors de la requête :', errorThrown);
								                    }
								                });
								               	
								           	});
								       	});
								   	</script>    
								        
								</td>
								<td>
				              		<button type="submit" class="btn btn-success btn-sm"
				              			data-bs-toggle="modal" data-bs-target="#modifyOneUser-${utilisateurs.identifiant}">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
										  	<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
										</svg>
				                		&nbsp;Modifier
				                   	</button>
				                    <!-- Structure de la fenêtre modale -->
				                    <div class="modal fade" id="modifyOneUser-${utilisateurs.identifiant}" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">

											<form method="post"
												action="<%=request.getContextPath()%>/espace_admin/gestion_utilisateurs"
												class="row">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="exampleModalLabel">
															Modification de l'Utilisateur n°<c:out value="${utilisateurs.identifiant}" /><br>
															Crédit : <c:out value="${utilisateurs.credit}" />
														</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close">
														</button>
													</div>
													<div class="modal-body">
														<input type="hidden" name="utilisateurId"
															value="${utilisateurs.identifiant}">
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label for="pseudo">Pseudo</label> <input type="text"
																		class="form-control" id="pseudo" name="pseudo"
																		value="<c:out value="${utilisateurs.pseudo}" />">
																</div>
																<div class="form-group">
																	<label for="nom">Nom</label> <input type="text"
																		class="form-control" id="nom" name="nom"
																		value="<c:out value="${utilisateurs.nom}" />">
																</div>
																<div class="form-group">
																	<label for="prenom">Prénom</label> <input type="text"
																		class="form-control" id="prenom" name="prenom"
																		value="<c:out value="${utilisateurs.prenom}" />">
																</div>
																<div class="form-group">
																	<label for="email">Email</label> <input type="email"
																		class="form-control" id="email" name="email"
																		value="<c:out value="${utilisateurs.email}" />">
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label for="telephone">Téléphone</label> <input type="tel"
																		class="form-control" id="telephone" name="telephone"
																		value="<c:out value="${utilisateurs.telephone}" />">
																</div>
																<div class="form-group">
																	<label for="rue">Rue</label> <input type="text"
																		class="form-control" id="rue" name="rue"
																		value="<c:out value="${utilisateurs.rue}" />">
																</div>
																<div class="form-group">
																	<label for="codePostal">Code postal</label> <input
																		type="number" class="form-control" id="codePostal"
																		name="codePostal"
																		value="<c:out value="${utilisateurs.codePostal}" />">
																</div>
																<div class="form-group">
																	<label for="ville">Ville</label> <input type="text"
																		class="form-control" id="ville" name="ville"
																		value="<c:out value="${utilisateurs.ville}" />">
																</div>
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary btn-sm"
															data-bs-dismiss="modal">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
															  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
															  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
															</svg>
															&nbsp;Annuler
														</button>
														<button type="submit" class="btn btn-success btn-sm"
															name="modifyOneUser">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
															  	<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
															</svg>
									                		&nbsp;Modifier
														</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/espace_admin/gestion_utilisateurs"
										class="row">
										<input type="hidden" name="utilisateurId"
											value="${utilisateurs.identifiant}">
										<input type="hidden" name="email"
											value="${utilisateurs.email}">
										<button type="submit" class="btn btn-primary btn-sm"
					              			name="mdpOublie">
											<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope-arrow-up" viewBox="0 0 16 16">
											  <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v4.5a.5.5 0 0 1-1 0V5.383l-7 4.2-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h5.5a.5.5 0 0 1 0 1H2a2 2 0 0 1-2-1.99V4Zm1 7.105 4.708-2.897L1 5.383v5.722ZM1 4v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1Z"/>
											  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Zm.354-5.354 1.25 1.25a.5.5 0 0 1-.708.708L13 12.207V14a.5.5 0 0 1-1 0v-1.717l-.28.305a.5.5 0 0 1-.737-.676l1.149-1.25a.5.5 0 0 1 .722-.016Z"/>
											</svg>
					                		&nbsp;Réinitialiser Mot de Passe
					                   	</button>
					            	</form>
					            </td>	
								<td>
				              		<button type="submit" class="btn btn-danger btn-sm"
				              			data-bs-toggle="modal" data-bs-target="#deleteOneUser-${utilisateurs.identifiant}">
				                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  											<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
  											<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
										</svg>
				                		&nbsp;Supprimer
				                   	</button>
				                    <!-- Structure de la fenêtre modale -->
				                    <div class="modal fade" id="deleteOneUser-${utilisateurs.identifiant}" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">

											<form method="post"
												action="<%=request.getContextPath()%>/espace_admin/gestion_utilisateurs"
												class="row">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="exampleModalLabel">
															Suppression de l'Utilisateur n°<c:out value="${utilisateurs.identifiant}" /><br>
															"<c:out value="${utilisateurs.prenom}" />&nbsp;<c:out value="${utilisateurs.nom}" />"
														</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close">
														</button>
													</div>
													<div class="modal-body">
														<p>Attention, cette action est irréversible !</p>
														<div class="form-check">
															<input type="hidden" name="utilisateurId"
																 value="${utilisateurs.identifiant}">
															<input class="form-check-input" type="checkbox"
																value="" id="invalidCheck2" required>
															<label class="form-check-label" for="invalidCheck2">
																Oui, je désire supprimer cet utilisateur
															</label>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary btn-sm"
															data-bs-dismiss="modal">
															<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
															  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
															  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
															</svg>
															&nbsp;Annuler
														</button>
														<button type="submit" class="btn btn-danger btn-sm"
															name="deleteOneUser">
									                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
					  											<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
					  											<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
															</svg>
									                		&nbsp;Supprimer
														</button>
													</div>
												</div>
											</form>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					
            </main>
            
        </div>
    </div>
    
	<!-- Gestion du Message de Retour après demande de Réinitialisation d'un Mot de Passe -->
	<c:if test="${not empty requestScope.sendMailMdpOubliOk}">
	    <script>
	        document.addEventListener('DOMContentLoaded', function() {
	            setTimeout(function() {
	                var sendMailMdpOubliOk = ${requestScope.sendMailMdpOubliOk};
	                if (sendMailMdpOubliOk === true) {
	                    alert("L'E-Mail de réinitialisation du Mot de Passe a bien été envoyé !");
	                } else {
	                    alert("Echec lors de l'envoi de l'E-Mail pour la réinitialisation de Mot de Passe");
	                }
	            }, 1000); // Délai en millisecondes
	        });
	    </script>
	</c:if>

    <!-- FOOTER -->
    <%@ include file="includes/footer.jspf"%>
    
</body>

</html>
