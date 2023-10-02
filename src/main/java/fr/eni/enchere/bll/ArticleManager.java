package fr.eni.enchere.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
	
	/**
	 * ***************** Début singleton ***************************
	 */
	
	public static ArticleManager instanceArticleManager;
	
	private ArticleManager() {		
	}
	
	public static ArticleManager getInstance() {
		if ( instanceArticleManager == null ) {
			instanceArticleManager = new ArticleManager();
		}
		return instanceArticleManager;
	}
	
	/**
	 * ***************** Fin singleton ***************************
	 */
	
	private ArticleDAO instanceArticleDAO = DAOFactory.getArticleDAO();
	
	
	//Création d'un article en vente par un utilisateur
	public void creerArticle( ArticleVendu article ) {		
		//si la date de début d'enchère est fixée à aujourd'hui, etatVente est à ENCOURS, sinon il est à NONDEBUTEE
		EtatVente etatVente = EtatVente.NON_DEBUTEE;
		if ( article.getDateDebutEncheres().equals(LocalDate.now()) ) {
			etatVente = EtatVente.EN_COURS;
		}
		article.setEtatVente(etatVente);
		
		instanceArticleDAO.creerArticle(article);
	}	
	
	public void updateArticle( ArticleVendu article ) {
		instanceArticleDAO.updateArticle(article);
	}
	
	public void deleteArticle( ArticleVendu article ) {
		instanceArticleDAO.deleteArticle(article);
	}
	
	public List<ArticleVendu> getAllArticles() {
		return instanceArticleDAO.getAllArticles();
	}
	
	public ArticleVendu selectById(int noArticle) {
		return instanceArticleDAO.selectById(noArticle);
	}
	
	public void activateArticle(ArticleVendu article) {
		article.setActivate(true);
		instanceArticleDAO.updateActivateArticle(article);
	}
	
	public void desactivateArticle(ArticleVendu article) {
		article.setActivate(false);
		instanceArticleDAO.updateActivateArticle(article);
	}
	
	public void updateEnchereArticle(ArticleVendu article, int noUtilisateurAcheteur, int montant) {
		instanceArticleDAO.updateEnchereArticle(article, noUtilisateurAcheteur, montant);
	}
	
	public void deleteAllArticlesByUser(Utilisateur utilisateur) {
		instanceArticleDAO.deleteAllArticlesByUser(utilisateur);
	}
	
	public void activateAllArticlesByUser(Utilisateur utilisateur) {
		ArticleVendu article = new ArticleVendu();
		article.setActivate(true);
		instanceArticleDAO.updateActivateAllArticlesByUser(utilisateur, article);
	}
	
	public void desactivateAllArticlesByUser(Utilisateur utilisateur) {
		ArticleVendu article = new ArticleVendu();
		article.setActivate(false);
		instanceArticleDAO.updateActivateAllArticlesByUser(utilisateur, article);
	}

}
