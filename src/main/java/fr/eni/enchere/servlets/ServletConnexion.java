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

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();


	public ServletConnexion() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/connexion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("mot de passe");

		Utilisateur utilisateur = utilisateurManager.connectionUser(pseudo, motDePasse);

		if (utilisateur != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", utilisateur);
			session.setAttribute("profilConnecte", true);
			response.sendRedirect(request.getContextPath() + "/ServletDeTest");

		} else {
			request.setAttribute("connexionEchouee", true);
			this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/connexion.jsp").forward(request,
					response);
		}

		
	}

}
