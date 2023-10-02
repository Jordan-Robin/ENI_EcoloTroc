package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {

	/**
	 * ***************** Début singleton ***************************
	 */

	private static UtilisateurManager instance;

	private UtilisateurManager() {
	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	/**
	 * ***************** Fin singleton ***************************
	 */

	UtilisateurDAO dao = DAOFactory.getUtilisateurDAO();

	public void insertOneUser(Utilisateur utilisateur) {
		dao.insertOneUser(utilisateur);
	}

	public List<Utilisateur> getAllUsers() {
		return dao.getAllUsers();
	}

	public Utilisateur connectionUser(String pseudo, String motDePasse) {
		return dao.connectionUser(pseudo, motDePasse);
	}

	public void updateOne(Utilisateur utilisateur, int noUtilisateur) {
		dao.update(utilisateur, noUtilisateur);
	}

	public Utilisateur selectById(int noIdentifiant) {
		return dao.selectById(noIdentifiant);
	}

	public void updateMdp(Utilisateur utilisateur, int noUtilisateur) {
		dao.updateMdp(utilisateur, noUtilisateur);
	}
	
	public void activateAdmin(Utilisateur utilisateur) {
		utilisateur.setAdministrateur(true);
		dao.updateAdminUser(utilisateur);
	}
	
	public void desactivateAdmin(Utilisateur utilisateur) {
		utilisateur.setAdministrateur(false);
		dao.updateAdminUser(utilisateur);
	}

	public void activateUser(Utilisateur utilisateur) {
		utilisateur.setActivate(true);
		dao.updateActivateUser(utilisateur);
	}
	
	public void desactivateUser(Utilisateur utilisateur) {
		utilisateur.setActivate(false);
		dao.updateActivateUser(utilisateur);
	}

	public void deleteUser(Utilisateur utilisateur) {
		dao.deleteOneUser(utilisateur);
	}
	
	public void updateCredit(Utilisateur utilisateur, int montant) {
		dao.updateCredit(utilisateur, montant);
	}
	
	public Utilisateur selectByPseudo(String pseudo) {
		return dao.selectByPseudo(pseudo);
	}

	/**
	 * ***************** Vérifications ***************************
	 */

	public String checkRegistration(Utilisateur utilisateur, String confirmation) {

		if (dao.selectByPseudo(utilisateur.getPseudo()) != null) {
			return "Ce pseudo est pris!";
		}

		if (utilisateur.getPseudo().isEmpty() || !utilisateur.getPseudo().matches("^[a-zA-Z0-9]*$")) {
			return "Le pseudo ne peut pas être vide et n’accepte que des caractères alphanumériques!";
		}

		if (utilisateur.getNom().isEmpty()) {
			return "Entrez s'il vous plait votre nom.";
		}

		if (utilisateur.getPrenom().isEmpty()) {
			return "Entrez s'il vous plait votre prénom.";
		}

		if (dao.selectByEmail(utilisateur.getEmail()) != null) {
			return "Ce e-mail est pris!";
		}

		if (utilisateur.getEmail().isEmpty() || !utilisateur.getEmail().contains("@")) {
			return "Entrez s'il vous plait l'e-mail.";
		}

		if (utilisateur.getTelephone().isEmpty()) {
			return "Entrez s'il vous plait votre téléphone.";
		}

		if (utilisateur.getRue().isEmpty()) {
			return "Entrez s'il vous plait votre rue.";
		}

		if (utilisateur.getCodePostal() == 0) {
			return "Entrez s'il vous plait votre code postal.";
		}

		if (utilisateur.getVille().isEmpty()) {
			return "Entrez s'il vous plait votre ville.";
		}

		if (utilisateur.getMotDePasse().isEmpty()) {
			return "Entrez s'il vous plait votre mot de passe.";
		}

		if (! confirmation.equals(utilisateur.getMotDePasse())) {
			return "La confirmation du mot de passe doit correspondre.";
		}

		return null;
	}

}
