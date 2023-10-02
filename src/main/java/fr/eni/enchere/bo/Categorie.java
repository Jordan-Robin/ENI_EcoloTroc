package fr.eni.enchere.bo;

//Classe définissant à quelle catégorie appartient un article
public class Categorie {
	
	private Integer noCategorie;
	private String libelle;
	
	public Categorie() {}
	
	public Categorie( Integer noCategorie, String libelle ) {
		this.setNoCategorie(noCategorie);
		this.setLibelle(libelle);
	}
	
	public Categorie( String libelle ) {
		this( null, libelle );
	}

	public Integer getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie( Integer noCategorie ) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle( String libelle ) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
}
