package fr.eni.enchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;

/**
 * Modification d'un article en vente par son vendeur (possible uniquement avant que les enchères n'aient commencé)
 */
@WebServlet("/ServletModifierArticle")
public class ServletModifierArticle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	ArticleManager articleManager = ArticleManager.getInstance();
	CategorieManager categorieManager = CategorieManager.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticle = Integer.valueOf( request.getParameter("idArticleAModifier") );
		ArticleVendu articleAModifier = articleManager.selectById(idArticle);
		
		//Envoi de tous les attributs actuels de l'article en vente
		request.setAttribute( "nomArticle", articleAModifier.getNomArticle() );
		request.setAttribute( "description", articleAModifier.getDescription() );
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		listeCategories = categorieManager.getAllCategories();	
		request.setAttribute( "listeCategories", listeCategories );
		request.setAttribute( "categoriePrecise" , articleAModifier.getCategorie().getLibelle() );
		request.setAttribute( "miseAPrix" , articleAModifier.getMiseAPrix() );
		
		//DATES NON RECUPERABLES DANS LA JSP
		request.setAttribute( "dateDebutEncheres" , articleAModifier.getDateDebutEncheres() );
		request.setAttribute( "dateFinEncheres" , articleAModifier.getDateFinEncheres() );
		
		request.setAttribute( "rue" , articleAModifier.getRetrait().getRue() );
		request.setAttribute( "codePostal" , articleAModifier.getRetrait().getCodePostal() );
		request.setAttribute( "ville" , articleAModifier.getRetrait().getVille() );
		
		/*
		 * VOIR SI L ETAPE CI DESSOUS PEUT PAS ETRE FAIT PAR LA SESSION
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */		
		/*On envoit également les numéros d'article et de retrait dans la requête, pour les récupérer
		  dans la méthode post*/
		request.setAttribute("noArticle", articleAModifier.getNoArticle() );
		request.setAttribute( "noRetrait" , articleAModifier.getRetrait().getNoRetrait() );
		
		RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/gestionArticles/modifierArticle.jsp" );
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
	
		//Vérification que la date de fin des enchères est postérieure à celle de début des enchères
		boolean verif = true;
		LocalDate dateDebutEncheres = LocalDate.parse( request.getParameter( "dateDebutEncheres" ) );
		LocalDate dateFinEncheres = LocalDate.parse( request.getParameter( "dateFinEncheres" ) );
		if ( dateFinEncheres.isBefore(dateDebutEncheres) || dateFinEncheres.isEqual(dateDebutEncheres) ) {
			verif = false;
		}
		
		//récupération de l'identifiant de la catégorie
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		listeCategories = categorieManager.getAllCategories();
		Categorie categorieAEnvoyer = new Categorie( null, request.getParameter("libelleCategorie") );
		for ( Categorie categorie: listeCategories ) {
			if ( categorie.getLibelle().equals( request.getParameter("libelleCategorie") ) ) {
				categorieAEnvoyer.setNoCategorie( categorie.getNoCategorie() );
				break;
			}
		}
		
		//récupération du point de retrait
		int idRetraitAModifier = Integer.valueOf( request.getParameter( "noRetrait" ) );
		Retrait retrait = new Retrait( idRetraitAModifier, request.getParameter("rue"), request.getParameter("ville"),
										Integer.valueOf( request.getParameter("codePostal") ) );
		
		//récupération des autres attributs
		int idArticleAModifier = Integer.valueOf( request.getParameter( "noArticle" ) );
		String nomArticle = request.getParameter( "nomArticle" );
		String description = request.getParameter( "description" );
		int miseAPrix = Integer.valueOf( request.getParameter("miseAPrix") );
		int prixVente = miseAPrix;
		
		ArticleVendu articleMisAJour = new ArticleVendu( idArticleAModifier, nomArticle, description, null,
						dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, categorieAEnvoyer, null,
						null, null, null, retrait);
		
		if ( verif ) {//si les dates sont correctement entrées, renvoi sur la page d'affichage de l'article
			
			articleManager.updateArticle(articleMisAJour);
			
			//on récupère l'article mis à jour avec tous les attributs, pour renvoi à la page d'affichage
			articleManager.selectById( idArticleAModifier );
			request.setAttribute("article",	articleManager.selectById(idArticleAModifier) );			
			request.setAttribute( "confirmationModification", true );//affiche un message de confirmation
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionArticles/detail_article.jsp");
			rd.forward(request, response);	
	
		} else {//renvoi sur la page d'insertion avec un message d'erreur et les attributs modifiés
			
			//Envoi de tous les attributs modifiés de l'article en vente
			request.setAttribute("noArticle", articleMisAJour.getNoArticle() );
			request.setAttribute( "noRetrait" , articleMisAJour.getRetrait().getNoRetrait() );
			request.setAttribute( "nomArticle", articleMisAJour.getNomArticle() );
			request.setAttribute( "description", articleMisAJour.getDescription() );	
			request.setAttribute( "listeCategories", listeCategories );
			request.setAttribute( "categoriePrecise" , articleMisAJour.getCategorie().getLibelle() );
			request.setAttribute( "miseAPrix" , articleMisAJour.getMiseAPrix() );
			request.setAttribute( "rue" , articleMisAJour.getRetrait().getRue() );
			request.setAttribute( "codePostal" , articleMisAJour.getRetrait().getCodePostal() );
			request.setAttribute( "ville" , articleMisAJour.getRetrait().getVille() );
			
			//DATES NON RECUPERABLES DANS LA JSP
			request.setAttribute( "dateDebutEncheres" , articleMisAJour.getDateDebutEncheres() );
			request.setAttribute( "dateFinEncheres" , articleMisAJour.getDateFinEncheres() );			
			
			request.setAttribute( "erreurDates", true );
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionArticles/modifierArticle.jsp");
			rd.forward(request, response);
		}
		
	}

}
