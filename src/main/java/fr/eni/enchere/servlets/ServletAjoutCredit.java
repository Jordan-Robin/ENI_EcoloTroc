package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/ServletAjoutCredit")
public class ServletAjoutCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	public ServletAjoutCredit() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");
		
		int montant = Integer.valueOf(request.getParameter("credits"));
		utilisateurManager.updateCredit(utilisateurActif, montant);
		
		utilisateurActif = utilisateurManager.selectById(utilisateurActif.getIdentifiant());
		session.setAttribute("user", utilisateurActif);

		request.setAttribute("creditsAjoutes", true);

		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp")
				.forward(request, response);
	}

}
