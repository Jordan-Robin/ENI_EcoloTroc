<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="../outils/entete.jspf"%>

<body>
	<%@ include file="../outils/menu.jsp"%>
	
	<!-- Confirmation éventuelle de suppression d'un article par son vendeur -->
	<c:if test="${ confirmationSuppression }">
		<div class="alert alert-success my-2" role="alert" style="text-align: center">
			Votre article a bien été supprimé !		
		</div>
	</c:if>

	<div class="container">
		<h1 class="my-2">Bienvenue</h1>

		<div class="row d-flex justify-content-between py-3">
			<!-- Cartes principales -->
			<div class="col-md-8 bg-light">
				<h2>Objets en vente</h2>

				<div class="row">
					<c:forEach var="articles" items="${ articles }">

						<!-- 					Vérifier l'article Activate (mode dev pour l'instant) -->

						<c:if test="${ articles.activate }">
							<div class="col-lg-4 col-md-6 mb-3">
								<c:if test="${not empty sessionScope.profilConnecte}">
									<a
										href="article?noArticle=<c:out value="${ articles.noArticle }" />"
										class="text-decoration-none">
								</c:if>
								<div class="card h-100" id="carteArticle">
									<img
										src="https://picsum.photos/1400/600?random=<c:out value="${ articles.noArticle }" />"
										class="card-img-top" alt="Image">
									<div class="card-body">
										<h3 class="text-end">
											<c:out value="${ articles.prixVente }" />
											€
										</h3>
										<h5 class="card-title">
											<c:out value="${ articles.nomArticle }" />
										</h5>
										<p>
											Fin de la vente le :
											<c:out value="${ articles.dateFinEncheres }" />
										</p>

									</div>

									<!-- AFFICHAGE DE L'ETAT DE LA VENTE -->
									<c:if test="${ articles.etatVente =='EN_COURS' }">
										<div class="card-footer border-success">
											<small class="text-body-secondary"><c:out
													value="${ articles.categorie.libelle }" /></small>
										</div>
										<div class="position-absolute top-0 end-0">
											<span class="badge text-bg-success m-2">Vente en cours</span>
										</div>
									</c:if>
									<c:if test="${ articles.etatVente =='NON_DEBUTEE' }">
										<div class="card-footer border-warning">
											<small class="text-body-secondary"><c:out
													value="${ articles.categorie.libelle }" /></small>
										</div>
										<div class="position-absolute top-0 end-0">
											<span class="badge text-bg-warning m-2">Prochainement</span>
										</div>
									</c:if>
									<c:if test="${ articles.etatVente =='TERMINEE' }">
										<div class="card-footer border-danger">
											<small class="text-body-secondary"><c:out
													value="${ articles.categorie.libelle }" /></small>
										</div>
										<div class="position-absolute top-0 end-0">
											<span class="badge text-bg-danger m-2">Vente terminée</span>
										</div>
									</c:if>
								</div>
								<c:if test="${not empty sessionScope.profilConnecte}">
									</a>
								</c:if>
							</div>
						</c:if>
					</c:forEach>
				</div>

			</div>

			<c:if test="${not empty sessionScope.profilConnecte}">
				<div class="col-md-3 bg-light">
					<h2>Mes enchères</h2>
					<div class="row">
						<c:forEach var="article" items="${articles}">
							<c:set var="breakLoop" value="true" />
							<c:forEach var="enchere" items="${article.encheres}">
								<c:if
									test="${fn:contains(enchere.noUtilisateur, user.identifiant) && breakLoop == true}">

									<c:set var="breakLoop" value="true" />

									<div class="col-lg-12 mb-3">
										<a
											href="article?noArticle=<c:out value="${ article.noArticle }" />"
											class="text-decoration-none" class="text-decoration-none">
											<div class="card">


												<!-- CARTE -->
												<img
													src="https://picsum.photos/1400/600?random=<c:out value="${ article.noArticle }" />"
													class="card-img-top" alt="Image">
												<div class="card-body">

													<h3 class="text-end">
														<c:out value="${ article.prixVente }" />
														€
													</h3>
													<h5 class="card-title">
														<c:out value="${ article.nomArticle }" />
													</h5>
												</div>

												<!-- AFFICHAGE DE L'ETAT DE LA VENTE -->
												<c:if test="${ article.etatVente =='EN_COURS' }">
													<div class="card-footer border-success">
														<small class="text-body-secondary">Vente en cours</small>
													</div>
													<div class="position-absolute top-0 end-0">
														<span class="badge text-bg-success m-2">Vente en
															cours</span>
													</div>
												</c:if>
												<c:if test="${ article.etatVente =='TERMINEE' }">
													<div class="card-footer border-danger">
														<small class="text-body-secondary">Vente terminée</small>
													</div>
													<div class="position-absolute top-0 end-0">
														<span class="badge text-bg-danger m-2">Vente
															terminée</span>
													</div>
												</c:if>

												<!-- AFFICHAGE ETAT ENCHERE -->
											
												<c:if test="${ enchere.etatEnchere =='MEILLEUR' }">
													<div id="iconWin" class="position-absolute top-0 start-0">
														<svg xmlns="http://www.w3.org/2000/svg" width="30"
															height="30" fill="yellow" class="bi bi-trophy-fill m-2"
															viewBox="0 0 16 16">
  <path
																d="M2.5.5A.5.5 0 0 1 3 0h10a.5.5 0 0 1 .5.5c0 .538-.012 1.05-.034 1.536a3 3 0 1 1-1.133 5.89c-.79 1.865-1.878 2.777-2.833 3.011v2.173l1.425.356c.194.048.377.135.537.255L13.3 15.1a.5.5 0 0 1-.3.9H3a.5.5 0 0 1-.3-.9l1.838-1.379c.16-.12.343-.207.537-.255L6.5 13.11v-2.173c-.955-.234-2.043-1.146-2.833-3.012a3 3 0 1 1-1.132-5.89A33.076 33.076 0 0 1 2.5.5zm.099 2.54a2 2 0 0 0 .72 3.935c-.333-1.05-.588-2.346-.72-3.935zm10.083 3.935a2 2 0 0 0 .72-3.935c-.133 1.59-.388 2.885-.72 3.935z" />
</svg>
													</div>
												</c:if>
												<c:if test="${ enchere.etatEnchere =='PERDU' }">
													<div id="iconFail" class="position-absolute top-0 start-0">
														<svg xmlns="http://www.w3.org/2000/svg" width="30"
															height="30" fill="red"
															class="bi bi-exclamation-octagon-fill m-2"
															viewBox="0 0 16 16">
  <path
																d="M11.46.146A.5.5 0 0 0 11.107 0H4.893a.5.5 0 0 0-.353.146L.146 4.54A.5.5 0 0 0 0 4.893v6.214a.5.5 0 0 0 .146.353l4.394 4.394a.5.5 0 0 0 .353.146h6.214a.5.5 0 0 0 .353-.146l4.394-4.394a.5.5 0 0 0 .146-.353V4.893a.5.5 0 0 0-.146-.353L11.46.146zM8 4c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995A.905.905 0 0 1 8 4zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
</svg>
													</div>
												</c:if>
											</div>
										</a>
									</div>
									<c:set var="breakLoop" value="false" />
								</c:if>

							</c:forEach>
						</c:forEach>
					</div>
				</div>
			</c:if>


		</div>
	</div>

	<!-- FOOTER -->

	<%@ include file="../outils/footer.jspf"%>

</body>
</html>