package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public interface ArticleDAO {
	
	public void creerArticle( ArticleVendu article );
	
	public int creerPointRetrait( ArticleVendu article );
	
	public void updateArticle( ArticleVendu article );

	public List<ArticleVendu> getAllArticles();
	
	public ArticleVendu selectById(int noArticle);
	
	public Retrait getRetraitById( int retraitId );
	
	public void deleteArticle( ArticleVendu article );
	
	public void updateActivateArticle(ArticleVendu article);
	
	public void updateEnchereArticle(ArticleVendu article, int noUtilisateurAcheteur, int montant);
	
	public void deleteAllArticlesByUser(Utilisateur utilisateur);
	
	public void updateActivateAllArticlesByUser(Utilisateur utilisateur, ArticleVendu article);
	
}
