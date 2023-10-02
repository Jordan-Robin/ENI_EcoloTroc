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

@WebServlet("/ServletChangementMdp")
public class ServletChangementMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

	public ServletChangementMdp() {
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

		if (request.getParameter("mot de passe").equals(utilisateurActif.getMotDePasse())) {

			if (request.getParameter("nouveau mot de passe").equals(request.getParameter("confirmation"))) {

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setMotDePasse(request.getParameter("nouveau mot de passe"));

				utilisateurManager.updateMdp(utilisateur, utilisateurActif.getIdentifiant());
				utilisateurActif = utilisateurManager.selectById(utilisateurActif.getIdentifiant());

				session.setAttribute("user", utilisateurActif);
				request.setAttribute("MdpChange", true);

			} else {

				System.out.println("Les deux nouveaux mots de passe ne sont pas identiques.");
				request.setAttribute("ErreurConfirmation", true);

			}

		} else {

			System.out.println("Mot de passe actuel erroné");
			request.setAttribute("MdpErrone", true);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp")
				.forward(request, response);
	}

}
