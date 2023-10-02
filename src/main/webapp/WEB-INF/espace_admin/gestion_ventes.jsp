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
                <h4 class="my-1">-> Gestion des Ventes</h4><br><hr><br>
                
                <c:forEach var="article" items="${articles}">
                
                	<!-- GESTION DES ARTICLES -->
                	
					<h4 class="my-2">----- VENTE du <c:out value="${article.getAffichageDateDebutEncheres()}" /> au <c:out value="${article.getAffichageDateFinEncheres()}" /> -----</h4><br>
                	
                	<h4 class="my-2">--> ARTICLE</h4>
                	
	                <table class="table">
					    <thead>
					        <tr>
					            <th scope="col" class="col-1">#</th>
					            <th scope="col" class="col-1">Libellé</th>
					            <th scope="col" class="col-1">Catégorie</th>
					            <th scope="col" class="col-1">Vendeur</th>
					            <th scope="col" class="col-1 text-center">Mise à Prix</th>
					            <th scope="col" class="col-1 text-center">Meilleure Enchère</th>
					            <th scope="col" class="col-1 text-center">Meilleur Acheteur</th>
					            <th scope="col" class="col-1 text-center">Statut</th>
					            <th scope="col" class="col-1">Activation</th>
					            <th scope="col" class="col-1"></th>
					        </tr>
					    </thead>
				    	<tbody>
				           <tr class="${article.etatVente == 'TERMINEE' ? 'table-danger' : (article.etatVente == 'EN_COURS' ? 'table-success' : 'table-warning')}">
				                
				                <th scope="row"><c:out value="${article.noArticle}" /></th>
				                <th scope="row"><c:out value="${article.nomArticle}" /></th>
				                <td><c:out value="${article.categorie.libelle}" /></td>
				                
								<c:choose>
								    <c:when test="${not empty article.vendeur}">
								    	<c:if test="${article.vendeur.activate eq true}">
								        	<td><c:out value="${article.vendeur.pseudo}" /></td>
								        </c:if>
								        <c:if test="${article.vendeur.activate eq false}">
								        	<td>
								        		<c:out value="${article.vendeur.pseudo}" /><br>
								        		(profil désactivé)
								        	</td>
								        </c:if>
								    </c:when>
								    <c:otherwise>
								        <th scope="row">VENDEUR SUPPRIMÉ</th>
								    </c:otherwise>
								</c:choose>
				                
				                <td class="text-center"><c:out value="${article.miseAPrix}" />&nbsp;&euro;</td>
				                
								<c:if test="${article.etatVente eq 'TERMINEE'}">
									<c:if test="${article.prixVente eq 0}">
								    	<td class="text-center">Aucune Enchère</td>
								    	<td class="text-center">Pas d'Acheteur</td>
								    </c:if>
								    <c:if test="${article.prixVente ne 0}">
								    	<th scope="row" class="text-center"><c:out value="${article.prixVente}" />&nbsp;&euro;<br>(prix final)</th>
								    	<th scope="row" class="text-center">
							    			Remporté par<br>
							    			<c:out value="${article.acheteur.pseudo}" />
								    	</th>
								    </c:if>
								</c:if>
								<c:if test="${article.etatVente eq 'EN_COURS'}">
									<c:if test="${article.prixVente eq 0}">
								    	<td class="text-center">Aucune Enchère</td>
								    	<td class="text-center">Pas d'Acheteur</td>
								    </c:if>
								    <c:if test="${article.prixVente ne 0}">
								    	<th scope="row" class="text-center"><c:out value="${article.prixVente}" />&nbsp;&euro;</th>
								    	<td class="text-center">
								    		<c:out value="${article.acheteur.pseudo}" />
								    	</td>
								    </c:if>
								</c:if>
								<c:if test="${article.etatVente eq 'NON_DEBUTEE'}">
								    <td class="text-center">-----</td>
								    <td class="text-center">-----</td>
								</c:if>
								
								<th scope="row" class="text-center">
								    <c:choose>
								        <c:when test="${article.etatVente == 'TERMINEE'}">
								            VENTE TERMINÉE
								        </c:when>
								        <c:when test="${article.etatVente == 'EN_COURS'}">
								            VENTE EN COURS
								        </c:when>
								        <c:otherwise>
								            VENTE NON DÉBUTÉE
								        </c:otherwise>
								    </c:choose>
								</th>
								
								<td>
								
									<c:if test="${article.etatVente ne 'TERMINEE'}">
								
										<input type="checkbox" id="activateSwitch-${article.noArticle}" data-on-text="ON" data-off-text="OFF" 
											data-on-color="success" data-off-color="danger" ${article.activate ? 'checked' : ''} 
									        data-servlet-url="<%=request.getContextPath()%>/espace_admin/gestion_ventes" 
									        data-article-id="${article.noArticle}" name="activateSwitch" />
									        
									    <script>
									    	$(document).ready(function() {
									
									           	$('#activateSwitch-${article.noArticle}').bootstrapSwitch();
									
									           	$('#activateSwitch-${article.noArticle}').on('switchChange.bootstrapSwitch', function(event, state) {
									           	
									               	// Cette fonction sera exécutée lorsque l'utilisateur coche ou décoche la case à cocher.
									               	var servletURL = $(this).data('servlet-url');
									               	var articleId = $(this).data('article-id');
													
									                // Envoie une requête Ajax au servlet avec l'identifiant de l'utilisateur et l'état de la case à cocher	                
									                console.log('servletURL:', servletURL);
													console.log('articleId:', articleId);
													console.log('state:', state);
									                
									                $.ajax({
									                    type: "POST",
									                    url: servletURL,
									                    data: {
									                    	articleId: articleId,
									                        activateSwitch: state
									                    },
									                    success: function(response) {
									                    	 console.log('Requête réussie, réponse du serveur');
									                         // Redirigez vers la même page après que la réponse ait été obtenue
									                         window.location.reload();
									                    },
									                    error: function(jqXHR, textStatus, errorThrown) {
									                    	console.error('Erreur lors de la requête :', errorThrown);
									                    }
									                });
									               	
									           	});
									       	});
									   	</script> 
									   	
									</c:if>   
								        
								</td>
								
								<td>
								
									<c:if test="${article.etatVente ne 'TERMINEE'}">
								
					              		<button type="submit" class="btn btn-danger btn-sm"
					              			data-bs-toggle="modal" data-bs-target="#deleteOneArticle-${article.noArticle}">
					                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
	  											<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
	  											<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
											</svg>
					                		&nbsp;Supprimer
					                   	</button>
					                   	
					                    <!-- Structure de la fenêtre modale -->
					                    <div class="modal fade" id="deleteOneArticle-${article.noArticle}" tabindex="-1"
											aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog">
	
												<form method="post"
													action="<%=request.getContextPath()%>/espace_admin/gestion_ventes"
													class="row">
													<div class="modal-content">
														<div class="modal-header">
															<h1 class="modal-title fs-5" id="exampleModalLabel">
																Suppression de l'Article n°<c:out value="${article.noArticle}" /><br>
																"<c:out value="${article.nomArticle}" />"
															</h1>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close">
															</button>
														</div>
														<div class="modal-body">
															<p>Attention, cette action est irréversible !</p>
															<div class="form-check">
																<input type="hidden" name="articleId"
																	 value="${article.noArticle}">
																<input class="form-check-input" type="checkbox"
																	value="" id="invalidCheck2" required>
																<label class="form-check-label" for="invalidCheck2">
																	Oui, je désire supprimer cet article
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
																name="deleteOneArticle">
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
										
									</c:if>
									
								</td>

				           	</tr>
				  		</tbody>
					</table>	
	           		
	           		
	           		<!-- GESTION DES ENCHERES -->
					<c:if test="${empty article.encheres}">
					    <h4 class="my-2">----- AUCUNE ENCHÈRE -----</h4>
					</c:if>
	           		
	           		<c:if test="${not empty article.encheres}">
	           		
	           		 	<div class="col-md-6">
	           		 	
	           		 	 	<h4 class="my-2">--> ENCHÈRES</h4>
	           		
	           				<table class="table">
	           			
							    <thead>
							    
									<tr>
							            <th scope="col" class="col-1">#</th>
							            <th scope="col" class="col-1">Date</th>
							            <th scope="col" class="col-1 text-center">Enchérisseur</th>
							            <th scope="col" class="col-1 text-center">Montant</th>
							            <th scope="col" class="col-1 text-center">État</th>
							            <th scope="col" class="col-1 text-center">Activation</th>
							        </tr>
							        
							    </thead>
		           		
		           				<tbody>
	
							    	<c:forEach var="enchere" items="${article.encheres}">
						    	
						  				<tr class="${enchere.etatEnchere == 'PERDU' ? 'table-danger' : (enchere.etatEnchere == 'MEILLEUR' ? 'table-success' : 'table-warning')}">
						                
						                	<th scope="row"><c:out value="${enchere.noEnchere}" /></th>
											<th scope="row"><c:out value="${enchere.getAffichageDateEnchere()}" /></th>
						                	<th scope="row" class="text-center"><c:out value="${enchere.pseudoUtilisateur}" /></th>
						                	<th scope="row" class="text-center"><c:out value="${enchere.montantEnchere}" />&nbsp;&euro;</th>
						                	
						                	<th scope="row" class="text-center">
									        	<c:choose>
											        <c:when test="${enchere.etatEnchere == 'MEILLEUR'}">
											            MEILLEUR ENCHÉREUR
											        </c:when>
											        <c:when test="${enchere.etatEnchere == 'PERDU'}">
											            VENTE PERDUE
											        </c:when>
											        <c:otherwise>
											        	VENTE REMPORTÉE
											        </c:otherwise>
											    </c:choose>
											</th>
						                
											<td class="text-center">
												<input type="checkbox" id="adminSwitch-${enchere.noEnchere}"
											  		data-on-text="ON" data-off-text="OFF"
											    	data-on-color="success" data-off-color="danger"
											   	 	${enchere.activate ? 'checked' : ''} disabled name="adminSwitch" />
											   	 	
												<script>
											 		$(document).ready(function() {
											        	$('#adminSwitch-${enchere.noEnchere}').bootstrapSwitch();
											    	});
												</script>
											</td>
											
						           		</tr>
						           	
						           	</c:forEach>
						  			
						  		</tbody>
						  		
							</table>
							
						</div>
						
					</c:if><br><hr><br>
							 
	        	</c:forEach>
	           		
            </main>
            
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="includes/footer.jspf"%>
    
</body>
</html>
