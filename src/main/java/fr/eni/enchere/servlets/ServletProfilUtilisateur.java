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
 * Servlet implementation class ServletProfilUtilisateur
 */
@WebServlet("/ServletProfilUtilisateur")
public class ServletProfilUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();

       
    public ServletProfilUtilisateur() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurActif = (Utilisateur) session.getAttribute("user");
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(request.getParameter("pseudo"));
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        utilisateur.setEmail(request.getParameter("email"));
        utilisateur.setTelephone(request.getParameter("telephone"));
        utilisateur.setRue(request.getParameter("rue"));
        utilisateur.setCodePostal(Integer.valueOf(request.getParameter("codePostal")));
        utilisateur.setVille(request.getParameter("ville"));
        
        utilisateurManager.updateOne(utilisateur, utilisateurActif.getIdentifiant());
        utilisateurActif = utilisateurManager.selectById(utilisateurActif.getIdentifiant());
                
        session.setAttribute("user", utilisateurActif);
        request.setAttribute("InformationsProfilModifiees", true);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/profilUtilisateur.jsp").forward(request, response);
	}

}
