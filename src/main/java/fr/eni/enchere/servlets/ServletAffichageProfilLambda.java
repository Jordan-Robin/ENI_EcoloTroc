package fr.eni.enchere.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.UtilisateurManager;

@WebServlet("/ServletAffichageProfilLambda")
public class ServletAffichageProfilLambda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

    public ServletAffichageProfilLambda() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("utilisateurLambda",	utilisateurManager.selectById(Integer.valueOf(request.getParameter("noUtilisateur"))));
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilVisiteur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
