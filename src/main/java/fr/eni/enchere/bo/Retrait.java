package fr.eni.enchere.bo;

//Classe définissant l'adresse de retrait d'un article à vendre
public class Retrait {
	
	private String rue, ville;
	private int codePostal;
	private Integer noRetrait;
	
	public Retrait( Integer noRetrait, String rue, String ville, int codePostal ) {
		this.setNoRetrait(noRetrait);
		this.setRue(rue);
		this.setVille(ville);
		this.setCodePostal(codePostal);
	}
	
	public Retrait(String rue, String ville, int codePostal) {
		this.setRue(rue);
		this.setVille(ville);
		this.setCodePostal(codePostal);
	}
	
	public Retrait() {}
	
	public Integer getNoRetrait() {
		return noRetrait;
	}

	public void setNoRetrait(Integer noRetrait) {
		this.noRetrait = noRetrait;
	}

	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", ville=" + ville + ", codePostal=" + codePostal + "]";
	}
	
	
}
