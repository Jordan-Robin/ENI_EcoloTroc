<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../outils/entete.jspf"%>

<body>
	<%@ include file="../outils/menu.jsp"%>
	<div class="container">
		<c:choose>
			<c:when test="${not empty sessionScope.profilConnecte}">
				<h1 class="my-2">Profil de l'utilisateur</h1>

				<div class="row d-flex justify-content-center py-3">
					<div class="col-md-8 bg-light m-3">
						<c:if test="${not empty requestScope.username}">
							<c:choose>
								<c:when test="${requestScope.errorPseudo == true}">
									<div class="alert alert-danger">Le pseudo d'utilisateur
										recherché n'a pas pu trouver l'utilisateur.</div>
								</c:when>
								<c:otherwise>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-activity"
												viewBox="0 0 16 16">
  <path fill-rule="evenodd"
													d="M6 2a.5.5 0 0 1 .47.33L10 12.036l1.53-4.208A.5.5 0 0 1 12 7.5h3.5a.5.5 0 0 1 0 1h-3.15l-1.88 5.17a.5.5 0 0 1-.94 0L6 3.964 4.47 8.171A.5.5 0 0 1 4 8.5H.5a.5.5 0 0 1 0-1h3.15l1.88-5.17A.5.5 0 0 1 6 2Z" />
</svg>
											<label for="pseudo"><b>Pseudo:</b></label>
										</div>
										<div class="col-1" style="white-space: nowrap;">
											<span id="pseudo"><%=request.getAttribute("pseudo")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-file-person"
												viewBox="0 0 16 16">
  <path
													d="M12 1a1 1 0 0 1 1 1v10.755S12 11 8 11s-5 1.755-5 1.755V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z" />
  <path d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
</svg>
											<label for="nom"><b>Nom:</b> </label>
										</div>
										<div class="col-1" style="white-space: nowrap;">
											<span id="nom"><%=request.getAttribute("nom")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-file-person"
												viewBox="0 0 16 16">
  <path
													d="M12 1a1 1 0 0 1 1 1v10.755S12 11 8 11s-5 1.755-5 1.755V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z" />
  <path d="M8 10a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
</svg>
											<label for="prenom"><b>Prénom:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="prenom"><%=request.getAttribute("prenom")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-envelope"
												viewBox="0 0 16 16">
  <path
													d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z" />
</svg>
											<label for="email"><b>Email:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="email"><%=request.getAttribute("email")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-telephone"
												viewBox="0 0 16 16">
  <path
													d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z" />
</svg>
											<label for="telephone"><b>Téléphone:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="telephone"><%=request.getAttribute("telephone")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-geo-alt-fill"
												viewBox="0 0 16 16">
  <path
													d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z" />
</svg>
											<label for="rue"><b>Rue:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="rue"><%=request.getAttribute("rue")%></span>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-2">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-building"
												viewBox="0 0 16 16">
  <path fill-rule="evenodd"
													d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6 7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022zM6 8.694 1 10.36V15h5V8.694zM7 15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5V15z" />
  <path
													d="M2 11h1v1H2v-1zm2 0h1v1H4v-1zm-2 2h1v1H2v-1zm2 0h1v1H4v-1zm4-4h1v1H8V9zm2 0h1v1h-1V9zm-2 2h1v1H8v-1zm2 0h1v1h-1v-1zm2-2h1v1h-1V9zm0 2h1v1h-1v-1zM8 7h1v1H8V7zm2 0h1v1h-1V7zm2 0h1v1h-1V7zM8 5h1v1H8V5zm2 0h1v1h-1V5zm2 0h1v1h-1V5zm0-2h1v1h-1V3z" />
</svg>
											<label for="ville"><b>Ville:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="ville"><%=request.getAttribute("ville")%></span>
										</div>
									</div>
									<br>
									<div class="row ">
										<div class="col-2" style="white-space: nowrap;">
											<svg xmlns="http://www.w3.org/2000/svg" width="30"
												height="30" fill="currentColor" class="bi bi-geo-alt-fill"
												viewBox="0 0 16 16">
  <path
													d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10zm0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6z" />
</svg>
											<label for="codePostal"><b>Code postal:</b> </label>
										</div>
										<div class="col-1 " style="white-space: nowrap;">
											<span id="codePostal"><%=request.getAttribute("codePostal")%></span>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:if>
						<br>
						<form action="<%=request.getContextPath()%>/affichageUtilisateur"
							method="get">
							<label for="searchQuery">Rechercher un profil par son
								pseudo :</label> <input type="text" id="searchQuery" name="username">
							<input type="submit" value="Rechercher">
						</form>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger">Pour consulter cette page,
					veuillez vous connecter à votre profil ou vous inscrire.</div>
			</c:otherwise>
		</c:choose>
	</div>
	<%@ include file="../outils/footer.jspf"%>
</body>
</html>