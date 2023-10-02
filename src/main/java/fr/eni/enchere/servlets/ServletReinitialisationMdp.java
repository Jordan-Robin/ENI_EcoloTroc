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
 * Servlet implementation class ServletReinitialisationMdp
 */
@WebServlet("/reinitialisation_mdp")
public class ServletReinitialisationMdp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReinitialisationMdp() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/gestionUtilisateurs/reinitialisation_mdp.jsp");
		
		HttpSession session = request.getSession();
		
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
        // Définir l'encodage des caractères UTF-8
        request.setCharacterEncoding("UTF-8");
		
    	UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
    	
    	boolean actionOk = false;
    	
    	
    	String reinitMdpAction = request.getParameter("reinitMdp");
    	String goToHome = request.getParameter("goToHome");
    	
    	String idUserStr = request.getParameter("numid");
    	
    	if (idUserStr != null && reinitMdpAction != null) {
    		
    	    try {
    	    	
    	    	
    	    	Utilisateur utilisateur = new Utilisateur();
    	    	
    	        int idUser = Integer.parseInt(idUserStr);
    	        utilisateur.setIdentifiant(idUser);

	        	utilisateur.setMotDePasse(request.getParameter("newPwd"));
	        	utilisateurManager.updateMdp(utilisateur, utilisateur.getIdentifiant());
	        	
	        	
	        	Utilisateur utilisateur2 = new Utilisateur();
	        	
	        	utilisateur2 = utilisateurManager.selectById(utilisateur.getIdentifiant());
	        	
	        	request.setAttribute("idUser", utilisateur2.getIdentifiant());
	        	request.setAttribute("pseudoUser", utilisateur2.getPseudo());
	        	request.setAttribute("mdpUser", utilisateur2.getMotDePasse());
	        	
	        	
	        	actionOk = true;

    	        
    	    } catch (NumberFormatException e) {
    	        // Gérer l'exception si la conversion échoue (par exemple, enregistrez l'erreur dans les journaux)
    	    } catch (Exception e) {	
    	    	// Gérer l'exception si une exception est levée
    	    }
    	    
    	}
    	
    	if (actionOk) {
    		
    		HttpSession session = request.getSession();

    		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/reinitialisation_mdp_ok.jsp").forward(request, response);
    		
    	}	
    	
    	
    	if (goToHome != null) {
    		
    		String pseudo = request.getParameter("pseudoUser");
    		String motDePasse = request.getParameter("mdpUser");

    		Utilisateur utilisateur = utilisateurManager.connectionUser(pseudo, motDePasse);

    		if (utilisateur != null) {

    			HttpSession session = request.getSession(true);
    			session.setAttribute("user", utilisateur);
    			session.setAttribute("profilConnecte", true);
    			response.sendRedirect(request.getContextPath() + "/ServletDeTest");


    		} else {

    			request.setAttribute("connexionEchouee", true);
    			this.getServletContext().getRequestDispatcher("/WEB-INF/gestionUtilisateurs/connexion.jsp").forward(request, response);

    		}
    		
    	}

    	    	    	
	}

}
