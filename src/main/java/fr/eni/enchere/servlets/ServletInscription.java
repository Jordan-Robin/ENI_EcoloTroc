package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	public ServletInscription() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		try {
			utilisateur.setCodePostal(Integer.valueOf(request.getParameter("codePostal")));
		} catch (NumberFormatException ex) {
			utilisateur.setCodePostal(0);
		}
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setMotDePasse(request.getParameter("mot de passe"));
		String confirmation = request.getParameter("confirmation");
		String verif = utilisateurManager.checkRegistration(utilisateur, confirmation);
		
		//pour sauvegarder les réponses
		request.setAttribute("pseudo", request.getParameter("pseudo"));
		request.setAttribute("nom", request.getParameter("nom"));
		request.setAttribute("prenom", request.getParameter("prenom"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("telephone", request.getParameter("telephone"));
		request.setAttribute("rue", request.getParameter("rue"));
		request.setAttribute("codePostal", request.getParameter("codePostal"));
		request.setAttribute("ville", request.getParameter("ville"));
		
		if (verif != null) {
			request.setAttribute("erreur", verif);
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/inscription.jsp")
					.forward(request, response);
		} else {
			utilisateurManager.insertOneUser(utilisateur);
			System.out.println(utilisateur.toString());
			HttpSession session = request.getSession();
			session.setAttribute("profilConnecte", true);
			request.setAttribute( "user", utilisateur );
			this.getServletContext().getRequestDispatcher("/WEB-INF/tests/bonjour.jsp").forward(request, response);
		}

	}
	
}
