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
                <h4 class="my-1">-> Gestion des Catégories</h4>
             	
				<button type="submit" class="btn btn-success btn-lg my-3" 
					data-bs-toggle="modal" data-bs-target="#createOneCategoryModal">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
						<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
						<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
					</svg>
					&nbsp;Ajouter une Nouvelle Catégorie
				</button>
				
				<div class="modal fade" id="createOneCategoryModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<form method="post"
							action="<%=request.getContextPath()%>/espace_admin/gestion_categories"
							class="row">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter une Nouvelle Catégorie</h1>
									<button type="button" class="btn-close"
										data-bs-dismiss="modal" aria-label="Close">
									</button>
								</div>
								<div class="modal-body">
									<input type="text" name="libelle" placeholder="Libellé de la Catégorie" value="">
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
										name="createOneCategory">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
											<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
											<path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
										</svg>
										Ajouter
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
   	
				<table class="table">
				    <thead>
				        <tr>
				            <th scope="col" class="col-1">#</th>
				            <th scope="col" class="col-3">Libellé</th>
				            <th scope="col" class="col-1"></th>
				            <th scope="col"></th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="categorie" items="${categories}">
				            <tr>
				                <th scope="row"><c:out value="${categorie.noCategorie}" /></th>
				                <td><c:out value="${categorie.libelle}" /></td>
				                <td>
				                	                
				                	<button type="button" class="btn btn-primary btn-sm" 
				                		data-bs-toggle="modal" data-bs-target="#modifyOneCategoryModal-${categorie.noCategorie}">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
										  	<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
										</svg>
				               			Modifier
				               		</button> <!-- Modal -->
				               		
									<div class="modal fade" id="modifyOneCategoryModal-${categorie.noCategorie}" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<form method="post"
												action="<%=request.getContextPath()%>/espace_admin/gestion_categories"
												class="row">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="exampleModalLabel">Modification de la Catégorie n°<c:out value="${categorie.noCategorie}" /></h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close">
														</button>
													</div>
													<div class="modal-body">
														<input type="hidden" name="noCategorie" value="${categorie.noCategorie}">
														<input type="text" name="libelle" value="${categorie.libelle}">
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
														<button type="submit" class="btn btn-primary btn-sm"
															 name="modifyOneCategory">
															 <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
										  						<path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
															</svg>
															 Modifier
														</button>
													</div>
												</div>
											</form>
										</div>
									</div>
									
				                </td>
				                <td>
				                    
				              		<button type="submit" class="btn btn-danger btn-sm"
				              			data-bs-toggle="modal" data-bs-target="#deleteOneCategory-${categorie.noCategorie}">
				                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  											<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
  											<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
										</svg>
				                		&nbsp;Supprimer
				                   	</button> <!-- Modal -->
				                    
				                    <div class="modal fade" id="deleteOneCategory-${categorie.noCategorie}" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">

											<form method="post"
												action="<%=request.getContextPath()%>/espace_admin/gestion_categories"
												class="row">
												<div class="modal-content">
													<div class="modal-header">
														<h1 class="modal-title fs-5" id="exampleModalLabel">
															Suppression de la Catégorie n°<c:out value="${categorie.noCategorie}" /><br>
															"<c:out value="${categorie.libelle}" />"
														</h1>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close">
														</button>
													</div>
													<div class="modal-body">
														<p>Attention, cette action est irréversible !</p>
														<div class="form-check">
															<input type="hidden" name="noCategorie"
																 value="${categorie.noCategorie}">
															<input class="form-check-input" type="checkbox"
																value="" id="invalidCheck2" required>
															<label class="form-check-label" for="invalidCheck2">
																Oui, je désire supprimer cette catégorie
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
															name="deleteOneCategory">
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

    <!-- FOOTER -->
    <%@ include file="includes/footer.jspf"%>
    
</body>
</html>
