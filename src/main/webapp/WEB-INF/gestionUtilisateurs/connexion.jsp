<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../outils/entete.jspf"%>

<body>
	<%@ include file="../outils/menu.jsp"%>

	<div class="container">
		<h1 class="my-2">Se connecter</h1>

		<c:if test="${not empty requestScope.connexionEchouee}">
			<div class="alert alert-danger">Connexion échouée : Pseudo ou
				mot de passe incorrect.</div>
		</c:if>

		<div class="row d-flex justify-content-center py-3">
			<!-- Formulaire d'inscription -->
			<div class="col-md-8 bg-light m-3">

				<form method="post" action="<%=request.getContextPath()%>/connexion">


					<div class="form-group">
						<label for="nom">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo"
							placeholder="Pseudo">
					</div>
					<div class="form-group">
						<label for="mot de passe">Mot de Passe</label> <input
							type="password" class="form-control" id="mot_de_passe"
							name="mot de passe" placeholder="Mot de passe">
					</div>
					<div class="py-3">
						<button type="submit" class="btn btn-primary">Se
							Connecter</button>
					</div>
				</form>


			</div>
		</div>
	</div>

	<!-- FOOTER -->
	<%@ include file="../outils/footer.jspf"%>

</body>
</html>