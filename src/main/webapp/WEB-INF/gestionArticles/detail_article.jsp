<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../outils/entete.jspf"%>

<body>
	<%@ include file="../outils/menu.jsp"%>

	<div class="container">
		<h1 class="my-2">Détail article</h1>

		<c:if test="${not empty requestScope.EnchereMise}">
			<div class="alert alert-success">Nous avons bien enregistré
				votre enchère.</div>
		</c:if>
		<c:if test="${not empty requestScope.CreditsInsuffisants}">
			<div class="alert alert-danger">Vos crédits sont insuffisants.</div>
		</c:if>

		<div class="row d-flex justify-content-center py-3">
		
			<!-- Confirmation éventuelle de modification de l'article par le vendeur -->
			<c:if test="${ confirmationModification }">
				<div class="alert alert-success my-2" role="alert" style="text-align: center">
					Votre article a bien été modifié !		
				</div>
			</c:if>
		
			<!-- Formulaire d'inscription -->
			<div class="col-md-8 bg-light m-3 p-4">
				<div class="row">
					<div class="col-8">
						<h2>
							<c:out value="${ article.nomArticle }" />
						</h2>
					</div>
					<div class="col-4">
						<c:if test="${ article.etatVente =='EN_COURS' }">
							<div class="alert alert-success">En cours</div>
						</c:if>
						<c:if test="${ article.etatVente =='NON_DEBUTEE' }">
							<div class="alert alert-warning">Prochainement</div>
						</c:if>
						<c:if test="${ article.etatVente =='TERMINEE' }">
							<div class="alert alert-danger">Vente terminée</div>
						</c:if>
					</div>
				</div>
				<img src="https://picsum.photos/1400/600?random=<c:out value="${ article.noArticle }" />" class="img-fluid my-3" alt="Image">
				<div class="d-flex">
					<div class="row py-3 col-10">

						<div class="col-1">
							<div class="text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-card-text" viewBox="0 0 16 16">
  <path
										d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
  <path
										d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8zm0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z" />
</svg>
							</div>
						</div>
						<div class="col-11">
							<p>
								<strong>Description</strong> <br>
								<c:out value="${ article.description }" />
							</p>
						</div>

						<div class="col-1">
							<div class="text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-person-circle"
									viewBox="0 0 16 16">
  							<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
  							<path fill-rule="evenodd"
										d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
							</svg>
							</div>
						</div>
						<div class="col-11">
							<p>
								<strong>Vendeur</strong> </br>
								<c:out value="${ article.vendeur.nom }" />
								<c:out value="${ article.vendeur.prenom }" />
							</p>
						</div>

						<div class="col-1">
							<div class="text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-calendar-x"
									viewBox="0 0 16 16">
  <path
										d="M6.146 7.146a.5.5 0 0 1 .708 0L8 8.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 9l1.147 1.146a.5.5 0 0 1-.708.708L8 9.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 9 6.146 7.854a.5.5 0 0 1 0-.708z" />
  <path
										d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z" />
</svg>
							</div>
						</div>
						<div class="col-11">
							<p>
								<strong>Fin de l'enchère</strong> </br>
								<c:out value="${ article.dateFinEncheres }" />

							</p>
						</div>

						<div class="col-1">
							<div class="text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-tag-fill" viewBox="0 0 16 16">
  <path
										d="M2 1a1 1 0 0 0-1 1v4.586a1 1 0 0 0 .293.707l7 7a1 1 0 0 0 1.414 0l4.586-4.586a1 1 0 0 0 0-1.414l-7-7A1 1 0 0 0 6.586 1H2zm4 3.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
</svg>
							</div>
						</div>
						<div class="col-11">
							<p>
								<strong>Catégorie</strong> </br>
								<c:out value="${ article.categorie.libelle }" />

							</p>
						</div>

						<div class="col-1">
							<div class="text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
  <path
										d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z" />
</svg>
							</div>
						</div>
						<div class="col-11">
							<p>
								<strong>Retrait</strong> <br>
								<c:out value="${article.retrait.rue}"/> <br>
								<c:out value="${article.retrait.codePostal}"/><c:out value="${article.retrait.ville}"/>
							</p>
						</div>

					</div>


					<!-- CADRE DERNIERE ENCHERE -->

					<div class="col-2">
						<div class="card p-3" id="derniereEnchere">
							<strong>Dernière enchère</strong>
							<h2>
								<c:out value="${ article.prixVente }" />
							</h2>
							<p>Crédits</p>
						</div>


						<!-- ENCHERE -->
						<c:if test="${ article.etatVente =='EN_COURS' }">
							<div class="d-grid gap-2 py-2">

								<button type="button" class="btn btn-warning"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									
										<svg xmlns="http://www.w3.org/2000/svg" class="me-1" width="16" height="16"
											fill="currentColor" class="bi bi-cash-coin"
											viewBox="0 0 16 16">
  <path fill-rule="evenodd"
												d="M11 15a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm5-4a5 5 0 1 1-10 0 5 5 0 0 1 10 0z" />
  <path
												d="M9.438 11.944c.047.596.518 1.06 1.363 1.116v.44h.375v-.443c.875-.061 1.386-.529 1.386-1.207 0-.618-.39-.936-1.09-1.1l-.296-.07v-1.2c.376.043.614.248.671.532h.658c-.047-.575-.54-1.024-1.329-1.073V8.5h-.375v.45c-.747.073-1.255.522-1.255 1.158 0 .562.378.92 1.007 1.066l.248.061v1.272c-.384-.058-.639-.27-.696-.563h-.668zm1.36-1.354c-.369-.085-.569-.26-.569-.522 0-.294.216-.514.572-.578v1.1h-.003zm.432.746c.449.104.655.272.655.569 0 .339-.257.571-.709.614v-1.195l.054.012z" />
  <path
												d="M1 0a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.083c.058-.344.145-.678.258-1H3a2 2 0 0 0-2-2V3a2 2 0 0 0 2-2h10a2 2 0 0 0 2 2v3.528c.38.34.717.728 1 1.154V1a1 1 0 0 0-1-1H1z" />
  <path
												d="M9.998 5.083 10 5a2 2 0 1 0-3.132 1.65 5.982 5.982 0 0 1 3.13-1.567z" />
</svg>
									Enchérir
									
								</button>
							</div>

							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">

									<form method="post"
										action="<%=request.getContextPath()%>/article" class="row">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5" id="exampleModalLabel">Enchérir</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<strong>Votre solde : <c:out
														value="${ user.credit }" /> crédits
												</strong>
												<p>Ajouter le montant de votre enchère.</p>
												<div class="form-check">
													<input type="hidden" name="idUser"
														value="${ user.identifiant}"> <input type="hidden"
														name="noArticle" value="${article.noArticle}"> <input
														type="number" class="form-control"
														min="${ article.prixVente + 1 }"
														value="${ article.prixVente + 1 }" name="montantEnchere">
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Annuler</button>
												<button type="submit" class="btn btn-warning">Enchérir
													maintenant</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</c:if>

						<!-- DEBUT BOUTONS MODIFICATION/SUPPRESSION D'ARTICLE PAR LE VENDEUR -->
						
						<c:if test="${ user.nom == article.vendeur.nom && article.etatVente == 'NON_DEBUTEE' }">
							
							<!-- Modification d'article : redirection vers page de modification -->
							<form method="get" action="ServletModifierArticle">
								<input type="hidden" name="idArticleAModifier" value="${article.noArticle}">
								<button type="submit" class="btn btn-warning my-2">Modifier l'article</button>
							</form>
							
							<!-- Annulation d'un article : confirmation avec modal -->
							<button type="submit" class="btn btn-danger btn-sm"
		              			data-bs-toggle="modal" data-bs-target="#deleteOneArticle-${article.noArticle}">
		                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
									<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5Zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6Z"/>
									<path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1ZM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118ZM2.5 3h11V2h-11v1Z"/>
								</svg>
		                		&nbsp;Annuler la vente
				            </button>
							
							<!-- Modal pour suppression d'un article -->
							<div class="modal fade" id="deleteOneArticle-${article.noArticle}" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<form method="post" action="ServletAnnulerArticle">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5" id="exampleModalLabel">
													Êtes-vous sûr de vouloir supprimer l'Article <c:out value="${article.nomArticle}" /> ?<br>
												</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close">
												</button>
											</div>
											<div class="modal-body">
												<p>Attention, cette action est irréversible !</p>
												<div class="form-check">
													<input type="hidden" name="idArticleASupprimer"
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
						<!-- FIN MODIFICATION/SUPPRESSION D'ARTICLE PAR LE VENDEUR -->

					</div>
				</div>
				<table class="table">
					<thead>
						<tr class="table-success">
							<th scope="col">#</th>
							<th scope="col">Pseudo</th>
							<th scope="col">Date de l'enchère</th>
							<th scope="col">Montant</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="enchere" items="${article.encheres}">

							<c:choose>
								<c:when test="${ enchere.etatEnchere == 'MEILLEUR' }">
									<tr class="table-success">
								</c:when>
								<c:otherwise>
									<tr>
								</c:otherwise>
							</c:choose>


							<td><c:if test="${ enchere.etatEnchere == 'MEILLEUR' }">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-trophy" viewBox="0 0 16 16">
  <path
											d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935zM3.504 1c.007.517.026 1.006.056 1.469.13 2.028.457 3.546.87 4.667C5.294 9.48 6.484 10 7 10a.5.5 0 0 1 .5.5v2.61a1 1 0 0 1-.757.97l-1.426.356a.5.5 0 0 0-.179.085L4.5 15h7l-.638-.479a.501.501 0 0 0-.18-.085l-1.425-.356a1 1 0 0 1-.757-.97V10.5A.5.5 0 0 1 9 10c.516 0 1.706-.52 2.57-2.864.413-1.12.74-2.64.87-4.667.03-.463.049-.952.056-1.469H3.504z" />
</svg>
								</c:if></td>
							<td><a
								href="ServletAffichageProfilLambda?noUtilisateur=<c:out value="${ enchere.noUtilisateur }" />"
								class="text-decoration-none">${enchere.pseudoUtilisateur}</td>
							<td>${enchere.dateEnchere}</td>
							<td>${enchere.montantEnchere}</td>
							</tr>
						</c:forEach>


					</tbody>
				</table>
			</div>


		</div>
	</div>

	<!-- FOOTER -->
	<%@ include file="../outils/footer.jspf"%>

</body>
</html>