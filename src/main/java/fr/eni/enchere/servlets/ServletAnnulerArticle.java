package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;

/**
 * Annulation de la vente d'un article par son vendeur (possible uniquement avant que les enchères n'aient commencé)
 */
@WebServlet("/ServletAnnulerArticle")
public class ServletAnnulerArticle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
	private ArticleManager articleManager = ArticleManager.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idArticleASupprimer = Integer.valueOf( request.getParameter("idArticleASupprimer") );
		ArticleVendu articleASupprimer = articleManager.selectById( idArticleASupprimer ) ;
		articleManager.deleteArticle(articleASupprimer);
		
		//pour afficher un message de confirmation de suppression
		request.setAttribute( "confirmationSuppression", true );

		request.setAttribute("utilisateurs", utilisateurManager.getAllUsers());
		request.setAttribute("articles", articleManager.getAllArticles());		
		RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/tests/bonjour.jsp" );
		rd.forward(request, response);
		
	}

}
