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

@WebServlet("/ServletSuppressionUtilisateurActif")
public class ServletSuppressionUtilisateurActif extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	public ServletSuppressionUtilisateurActif() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");

		utilisateurManager.deleteUser(utilisateurActif);
		
		if (session != null) {
			session.setAttribute("profilConnecte", false);
			session.invalidate();
		}
		
		System.out.println("Utilisateur supprimé");
		
		response.sendRedirect(request.getContextPath() + "/connexion");

	}

}
