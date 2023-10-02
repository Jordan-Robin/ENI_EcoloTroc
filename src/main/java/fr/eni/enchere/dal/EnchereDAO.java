/**
 * 
 */
package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatEnchere;

public interface EnchereDAO {
	
	public List<Enchere> selectAllEnchere();

	public List<Enchere> afficherListeEnchere(int noArticle);
	
	public void encherir(Enchere enchere);
	
	public void updateEnchere(int noEnchere, EtatEnchere etatEnchere);
	
	public void deleteOneEnchere(Enchere enchere);
	
	public void updateActivateAllEnchersByArticle(Enchere enchere, ArticleVendu article);
	
	public void deleteAllEnchersByArticle(ArticleVendu article);
	
}
