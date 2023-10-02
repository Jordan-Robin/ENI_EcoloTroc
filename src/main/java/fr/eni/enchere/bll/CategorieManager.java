package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;

public class CategorieManager {
	
	/**
	 * ***************** Début singleton ***************************
	 */

	private static CategorieManager instance;

	private CategorieManager() {
	}

	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}

	/**
	 * ***************** Fin singleton ***************************
	 */
	
	CategorieDAO dao = DAOFactory.getCategorieDAO();
	
	public List<Categorie> getAllCategories() {
		return dao.selectAllCategories();	
	}
	
	public void createOneCategory(Categorie categorie) {
		dao.insertOneCategory(categorie);
	}
	
	public void modifyOneCategory(Categorie categorie) {
		dao.updateOneCategory(categorie);
	}
	
	public void deleteOneCategory(Categorie categorie) {
		dao.deleteOneCategory(categorie);
	}

	public void selectOneCategoryById (int noCategorie) {
		dao.selectOneCategoryById(noCategorie);
	}
}
