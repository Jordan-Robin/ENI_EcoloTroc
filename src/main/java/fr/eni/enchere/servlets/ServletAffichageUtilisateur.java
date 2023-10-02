package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/affichageUtilisateur")
public class ServletAffichageUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		Utilisateur utilisateur = utilisateurManager.selectByPseudo(username);

		if (username != null) {
			if (utilisateur == null) {
				request.setAttribute("errorPseudo", true);
			} else {
				request.setAttribute("pseudo", utilisateur.getPseudo());
				request.setAttribute("nom", utilisateur.getNom());
				request.setAttribute("prenom", utilisateur.getPrenom());
				request.setAttribute("email", utilisateur.getEmail());
				request.setAttribute("rue", utilisateur.getRue());
				request.setAttribute("codePostal", utilisateur.getCodePostal());
				request.setAttribute("ville", utilisateur.getVille());
			}
			request.setAttribute("username", "0");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/affichageUtilisateur.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
