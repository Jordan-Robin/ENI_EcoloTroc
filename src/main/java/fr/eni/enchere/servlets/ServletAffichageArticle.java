package fr.eni.enchere.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatEnchere;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/article")
public class ServletAffichageArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager = ArticleManager.getInstance();
	private EnchereManager enchereManager = EnchereManager.getInstance();
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

       
    public ServletAffichageArticle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("article",	articleManager.selectById(Integer.valueOf(request.getParameter("noArticle"))));
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionArticles/detail_article.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int montant = Integer.valueOf(request.getParameter("montantEnchere"));
		System.out.println("Nouvelle enchère : " + montant);
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");
		
		ArticleVendu article = articleManager.selectById(Integer.valueOf(request.getParameter("noArticle")));
		
		if (utilisateurActif.getCredit() >= montant) {
			
			LocalDate date = LocalDate.now();
			
			Enchere enchere = new Enchere();
			enchere.setActivate(true);
			enchere.setArticleVendu(articleManager.selectById(Integer.valueOf(request.getParameter("noArticle"))));
			enchere.setDateEnchere(date);
			enchere.setMontantEnchere(montant);
			enchere.setUtilisateurAcheteur(utilisateurActif);
			
			enchereManager.encherir(enchere);
			
			utilisateurManager.updateCredit(utilisateurActif, - montant);
			
			utilisateurActif = utilisateurManager.selectById(utilisateurActif.getIdentifiant());
            session.setAttribute("user", utilisateurActif);
	        request.setAttribute("EnchereMise", true);
	        
	        // Update de la dernière enchère
	        
	        articleManager.updateEnchereArticle(article, utilisateurActif.getIdentifiant() , montant);
	        
	        List<Enchere> listeEncheres = new ArrayList<>();
	        listeEncheres = enchereManager.afficherListeEnchere(article.getNoArticle());
	        
	        if (listeEncheres.size()>1) {
	        	
	        	// VERIFIER ANCIEN ENCHERISSEUR
	        	int noAncienEncherisseur = enchereManager.afficherListeEnchere(article.getNoArticle()).get(1).getNoUtilisateur();
	        	int ancienMontant = enchereManager.afficherListeEnchere(article.getNoArticle()).get(1).getMontantEnchere();
	        	Utilisateur AncienEncherisseur = utilisateurManager.selectById(noAncienEncherisseur);
	        	utilisateurManager.updateCredit(AncienEncherisseur, ancienMontant);
	        	
	        	// CHANGER L'UTILISATEUR ACHETEUR DE L'ARTICLE
	        	
	        	int noAncienneEnchere = enchereManager.afficherListeEnchere(article.getNoArticle()).get(1).getNoEnchere();
	        	enchereManager.updateEnchere(noAncienneEnchere, EtatEnchere.PERDU);
	        
	        }
	        
		} else {
			System.out.println("Crédits insuffisants");
			request.setAttribute("CreditsInsuffisants", true);
		}		
		
		
		request.setAttribute("article",	articleManager.selectById(Integer.valueOf(request.getParameter("noArticle"))));
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionArticles/detail_article.jsp").forward(request, response);
	}

}
