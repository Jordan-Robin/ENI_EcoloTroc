package fr.eni.enchere.bo; 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 

//Classe représentant l'offre faite par un utilisateur pour un article donné
public class Enchere {

	private Integer noEnchere;
	private Utilisateur utilisateurAcheteur;
	private ArticleVendu articleVendu;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Boolean activate = false;
	private EtatEnchere etatEnchere; /*3 valeurs possibles définies dans l'Enumeration EtatEnchere*/;
	// Attributs secondaires
	private int noUtilisateur;
	private String pseudoUtilisateur;
	private int noArticle;


	public Enchere( Integer noEnchere, Utilisateur utilisateurAcheteur, ArticleVendu articleVendu, LocalDate dateEnchere,
		int montantEnchere, Boolean activate, EtatEnchere etatEnchere ) {
		this.setNoEnchere(noEnchere);
		this.setUtilisateurAcheteur(utilisateurAcheteur);
		this.setArticleVendu(articleVendu);
		this.setDateEnchere(dateEnchere);
		this.setMontantEnchere(montantEnchere);
		this.setActivate(activate);
		this.setEtatEnchere(etatEnchere);
	}

	public Enchere( Utilisateur utilisateurAcheteur, ArticleVendu articleVendu, LocalDate dateEnchere,
		int montantEnchere, Boolean activate, EtatEnchere etatEnchere ) {
		this( null, utilisateurAcheteur, articleVendu, dateEnchere, montantEnchere, activate, etatEnchere );
	}

	public Enchere() {
	} 

	
	public Integer getNoEnchere() {
		return noEnchere;
	}
	public void setNoEnchere(Integer noEnchere) {
		this.noEnchere = noEnchere;
	}
	public Utilisateur getUtilisateurAcheteur() {
		return utilisateurAcheteur;
	}
	public void setUtilisateurAcheteur(Utilisateur utilisateurAcheteur) {
		this.utilisateurAcheteur = utilisateurAcheteur;
	}
    public LocalDate getDateEnchere() {
        return dateEnchere;
    }
    public String getAffichageDateEnchere() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateEnchere.format(formatter);
    }
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	public Boolean getActivate() {
		return activate;
	}
	public void setActivate(Boolean activate) {
		this.activate = activate;
	}
	public EtatEnchere getEtatEnchere() {
		return etatEnchere;
	}
	public void setEtatEnchere(EtatEnchere etatEnchere) {
		this.etatEnchere = etatEnchere;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	} 
	public String getPseudoUtilisateur() {
		return pseudoUtilisateur;
	}
	public void setPseudoUtilisateur(String pseudoUtilisateur) {
		this.pseudoUtilisateur = pseudoUtilisateur;
	}	
	
	
	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", utilisateurAcheteur=" + utilisateurAcheteur + ", articleVendu="
				+ articleVendu + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", activate="
				+ activate + ", etatEnchere=" + etatEnchere + ", noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + "]";
	}


}