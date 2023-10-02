<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%@ include file="../outils/entete.jspf"%>
	
<body>

	<%@ include file="../outils/menu.jsp"%>
	
	<!-- Confirmation éventuelle de modification de l'article si l'utilisateur vient d'en modifier un -->
	<c:if test="${ confirmation }">
		<div class="alert alert-success my-2" role="alert" style="text-align: center">
			Votre article a bien été modifié !		
		</div>
	</c:if>

<main class="container row d-flex">

	<div class="col-4">	</div>

	<div class="col-8 mx-0 mt-2">
	
		<h1 class="m-3">Modifier un article</h1>
		
		<div class="row d-flex py-3 bg-light m-3">
		
			<form id="formulaire">
			
				<div class="form-group">
					<label for="nomArticle">Nom de l'article :</label>
					<input type="text" name="nomArticle" id="nomArticle" class="form-control"
							maxlength="50" value="${nomArticle}" required>
				</div>
				
				<div class="form-group my-2">
					<label for="description">Description :</label>
					<textarea id="description" name="description" maxlength="800" class="form-control"
								 required style="height: 150px"> <c:out value="${description}"/> </textarea>
				</div>
				
				<div class="form-group col-6 my-2">
					<label for="libelleCategorie">Choisir une catégorie :</label>
					<select name="libelleCategorie" id="libelleCategorie" class="form-select">
						<c:forEach var="categorie" items="${ listeCategories }">
							<option value="${categorie.libelle}"
							<c:if test="${ categorie.libelle == categoriePrecise }">selected</c:if> >
							${ categorie.getLibelle() }</option>
						</c:forEach>
					</select>						
				</div>
				
				<div class="form-group col-4 my-2">
					<label for="miseAPrix">Mise à prix :</label>
					<input type="number" name="miseAPrix" id="miseAPrix" min=0 class="form-control"
							 value="${miseAPrix}" required>					
				</div>
		  		
		  		<!-- Message d'erreur si la date de fin des enchères est antérieure à celle de début -->
		  		<c:if test="${ erreurDates }">
		  			<div class="alert alert-danger my-2" role="alert">
		  				La date de fin des enchères doit être postérieure à la date de début des enchères !
		  				 Recommencez la saisie !
		  			</div>
		  		</c:if>
				
				<!-- Début regroupement des dates de début et fin d'enchères -->
				<div class="column d-flex my-2">	
					<div class="form-group col-7">
						<label for="dateDebutEncheres">Début de l'enchère</label>
						<div class="col-6">
							<input type="date" name="dateDebutEncheres" id="dateDebutEncheres"  
									value="${dateDebutEncheres}" min="" class="form-control" required><br>
						</div>
					</div>				
					<!-- mise en place des valeurs par défaut et minimum pour la date de début des enchères -->
					<script>
			       		const dateActuelle = new Date().toISOString().split('T')[0];
				        const dateDebutEncheres = document.getElementById("dateDebutEncheres");
				        dateDebutEncheres.min = dateActuelle;
			  		</script>
			  		
			  		<div class="form-group col-7">
						<label for="dateFinEncheres">Fin de l'enchère</label>
						<div class="col-6">
							<input type="date" name="dateFinEncheres" id="dateFinEncheres"
									value="${dateFinEncheres}" min="" class="form-control" required><br>
						</div>
					</div>
					<script>
				        const dateFinEncheres = document.getElementById("dateFinEncheres");
				        dateFinEncheres.min = dateActuelle;
			  		</script>
		  		</div>
		  		<!-- Fin regroupement des dates de début et fin d'enchères -->
		  		
				<fieldset class="border border-3 p-2 mb-3" style="border-radius: 15px">
				
					<legend>Retrait</legend>
					
					<div class="form-group col-10 my-2">
						<label for="rue">Rue :</label>
						<input class="form-control" type="text" name="rue" id="rue" maxlength="50" 
								value="${rue}" required>
					</div>
					
					<!-- début regroupement du code postal et de la ville sur une même ligne -->
					<div class="row">
					
						<div class="col-2">
							<label for=codePostal class="col-form-label">Code postal :</label>
						</div>
						<div class="col-2">
							<input class="form-control" type="text" name="codePostal" id="codePostal" 
									maxlength="6" value="${codePostal}" required>
						</div>
						<div class="col-1"></div>						
						<div class="col-1 px-0 mx-0">
							<label for="ville" class="col-form-label">Ville :</label>
						</div>
						<div class="col-4">
							<input class="form-control" type="text" name="ville" id="ville" maxlength="30" 
									value="${ville}" required>
						</div>
						
					</div>
					<!-- fin regroupement du code postal et de la ville sur une même ligne -->
					
				</fieldset>	
				
				<input type="hidden" name="noArticle" value="${noArticle}">
				<input type="hidden" name="noRetrait" value="${noRetrait}">							
					
				<input type="submit" class="btn btn-success" value="Valider" formaction="ServletModifierArticle"
						formmethod="POST">
				
				<input type="submit" class="btn btn-danger" value="Annuler" formmethod="GET"
						formaction="<%=request.getContextPath()%>/article" formnovalidate>
						
			</form>
		
		</div>
		
	</div>

</main>
	
		<!-- FOOTER -->

	<%@ include file="../outils/footer.jspf"%>

</body>

</html>