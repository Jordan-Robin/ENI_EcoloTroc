/**
 * 
 */
package fr.eni.enchere.dal;

import fr.eni.enchere.dal.jdbc.UtilisateurDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.enchere.dal.jdbc.ArticleDAOJdbcImpl;


public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
}
