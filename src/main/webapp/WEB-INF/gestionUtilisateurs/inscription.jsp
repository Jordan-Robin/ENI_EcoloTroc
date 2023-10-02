<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	

<%@ include file="../outils/entete.jspf"%>

<body>
	<%@ include file="../outils/menu.jsp"%>

	<div class="container">
	
		<h1 class="my-2">Formulaire d'inscription</h1>
		
		<c:if test="${not empty requestScope.ErreurInscription}">
			<div class="alert alert-danger">Une erreur est survenue lors de votre inscription, veuillez réessayer.</div>
		</c:if>

		<div class="row d-flex justify-content-center py-3">

			<div class="col-md-8 bg-light m-3">

					<div>
						<p style="color: #ff0000">
							<strong>${erreur}</strong>
						</p>
					</div>
			
				<form method="post" action="<%=request.getContextPath()%>/inscription" class="row needs-validation" novalidate>
				
						<div class="form-group col-6">
							<label for="pseudo" class="form-label">Pseudo</label>
							<input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="Pseudo" value="${pseudo}" required>
							<div class="invalid-feedback">
      							Veuillez choisir un pseudo.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="nom" class="form-label">Nom</label>
							<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="${nom}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre nom nom.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="prenom" class="form-label">Prénom</label>
							<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" value="${prenom}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre prénom.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="email" class="form-label">Email</label>
							<input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${email}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre adresse mail.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="telephone" class="form-label">Téléphone</label>
							<input type="tel" class="form-control" id="telephone" name="telephone" placeholder="Téléphone" value="${telephone}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre numéro de téléphone.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="rue" class="form-label">Rue</label>
							<input type="text" class="form-control" id="rue" name="rue"	placeholder="Rue" value="${rue}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre rue.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="codePostal" class="form-label">Code postal</label>
							<input type="number" class="form-control" id="codePostal" name="codePostal" placeholder="Code postal" value="${codePostal}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer le code postal de votre ville.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="ville" class="form-label">Ville</label>
							<input type="text" class="form-control" id="ville" name="ville" placeholder="Ville" value="${ville}" required>
							<div class="invalid-feedback">
      							Veuillez indiquer votre ville.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="mot de passe" class="form-label">Mot de Passe</label>
							<input type="password" class="form-control" id="mot de passe" name="mot de passe" placeholder="Mot de passe" required>
							<div class="invalid-feedback">
      							Veuillez choisir un mot de passe.
    						</div>
						</div>
						
						<div class="form-group col-6">
							<label for="confirmation" class="form-label">Confirmation</label>
							<input type="password" class="form-control" id="confirmation" name="confirmation" placeholder="Confirmation" required>
							<div class="invalid-feedback">
      							Veuillez confirmer votre mot de passe.
    						</div>
						</div>
						
						<div class="my-4 text-center">
						
							<div class="col-4 mx-auto">
								<button type="submit" class="btn btn-primary mr-2">S'inscrire</button>
							</div>
							
						</div>
					

				</form>


			</div>
		</div>
	</div>
	
	<script>
	(() => {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  const forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.from(forms).forEach(form => {
		    form.addEventListener('submit', event => {
		      if (!form.checkValidity()) {
		        event.preventDefault()
		        event.stopPropagation()
		      }

		      form.classList.add('was-validated')
		    }, false)
		  })
		})()
	</script>





	<!-- FOOTER -->

	<%@ include file="../outils/footer.jspf"%>

</body>
</html>