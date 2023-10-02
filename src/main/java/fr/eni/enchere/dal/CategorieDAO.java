package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieDAO {
	
	public List<Categorie> selectAllCategories();
	
	public void insertOneCategory(Categorie categorie);
	
	public void updateOneCategory(Categorie categorie);
	
	public void deleteOneCategory(Categorie categorie);
	
	public Categorie selectOneCategoryById (int noCategorie);

}
