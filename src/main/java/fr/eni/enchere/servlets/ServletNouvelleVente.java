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
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;


@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//Lorsque l'utilisateur connecté clique sur "vendre un article" 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Envoi de l'adresse de l'utilisateur (entrée par défaut lors de la vente d'un article)
		HttpSession session = request.getSession();
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");
		request.setAttribute( "userId", utilisateurActif.getIdentifiant() );
		request.setAttribute( "rueUser", utilisateurActif.getRue() );
		request.setAttribute( "villeUser", utilisateurActif.getVille() );
		request.setAttribute( "codePostalUser", utilisateurActif.getCodePostal() );
		
		//Envoi de la liste des catégories au menu déroulant du choix des Catégories dans la jsp
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		listeCategories = categorieManager.getAllCategories();	
		request.setAttribute( "categories", listeCategories );
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionArticles/nouvelArticle.jsp");
		rd.forward(request, response);
	}
	
	//Envoi du formulaire "nouvel article" : instanciation d'un ArticleVendu pour envoi au ArticleManager
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		ArticleManager instanceManager = ArticleManager.getInstance();
		
		boolean verif = true;
		
		//Vérification que la date de fin des enchères est postérieure à celle de début des enchères
		LocalDate dateDebutEncheres = LocalDate.parse( request.getParameter( "dateDebutEncheres" ) );
		LocalDate dateFinEncheres = LocalDate.parse( request.getParameter( "dateFinEncheres" ) );
		if ( dateFinEncheres.isBefore(dateDebutEncheres) || dateFinEncheres.isEqual(dateDebutEncheres) ) {
			verif = false;
		}
		
		//récupération de l'identifiant de la catégorie
		CategorieManager categorieManager = CategorieManager.getInstance();
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		listeCategories = categorieManager.getAllCategories();
		Categorie categorieAEnvoyer = new Categorie( null, request.getParameter("libelleCategorie") );
		for ( Categorie categorie: listeCategories ) {
			if ( categorie.getLibelle().equals( request.getParameter("libelleCategorie") ) ) {
				categorieAEnvoyer.setNoCategorie( categorie.getNoCategorie() );
				break;
			}
		}
		
		//récupération des autres attributs
		String nomArticle = request.getParameter( "nomArticle" );
		String description = request.getParameter( "description" );
		int miseAPrix = Integer.valueOf( request.getParameter("miseAPrix") );
		int prixVente = miseAPrix;
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");
		List<Enchere> encheres = new ArrayList<>();
		
		//création d'un nouveau point de retrait (relation 1 à 1 entre ArticleVendu et Retrait)
		Retrait retrait = new Retrait( request.getParameter("rue"), request.getParameter("ville"),
										Integer.valueOf( request.getParameter("codePostal") ) );
		
		//etatVente sera mis en place dans la bll (fonction de la date de début des enchères)
		ArticleVendu article = new ArticleVendu( nomArticle, description, null, dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, categorieAEnvoyer, utilisateurActif, null, false, encheres, retrait );
	
		if ( verif ) {//si les dates sont correctement entrées, renvoi sur la page d'insertion avec confirmation
			instanceManager.creerArticle(article);
				
			request.setAttribute( "categories", listeCategories );			
			request.setAttribute( "rueUser", utilisateurActif.getRue() );
			request.setAttribute( "villeUser", utilisateurActif.getVille() );
			request.setAttribute( "codePostalUser", utilisateurActif.getCodePostal() );
			
			request.setAttribute("confirmation", true);//afin d'afficher un message de confirmation
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionArticles/nouvelArticle.jsp");
			rd.forward(request, response);
			
		} else {//renvoi sur la page d'insertion avec un message d'erreur
				
			request.setAttribute( "categories", listeCategories );
			request.setAttribute( "categoriePrecise", request.getParameter("libelleCategorie") );
			request.setAttribute( "nomArticle", nomArticle);
			request.setAttribute( "description", description);
			request.setAttribute( "miseAPrix", miseAPrix);
			
			request.setAttribute( "erreurDates", true );
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionArticles/nouvelArticle.jsp");
			rd.forward(request, response);
		}
		
	}

}
