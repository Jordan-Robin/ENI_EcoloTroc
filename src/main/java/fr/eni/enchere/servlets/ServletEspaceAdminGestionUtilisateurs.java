package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EmailManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletEspaceAdminGestionUtilisateurs
 */
@WebServlet("/espace_admin/gestion_utilisateurs")
public class ServletEspaceAdminGestionUtilisateurs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEspaceAdminGestionUtilisateurs() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/espace_admin/gestion_utilisateurs.jsp");
		
		UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
		
		request.setAttribute("utilisateurs", utilisateurManager.getAllUsers());
		
		HttpSession session = request.getSession();
		
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        // Définir l'encodage des caractères UTF-8
        request.setCharacterEncoding("UTF-8");
		
        // Appel des Services
    	UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
    	ArticleManager articleManager = ArticleManager.getInstance();
    	EmailManager emailmanager = EmailManager.getInstance();
    	
    	
    	String idUserStr = request.getParameter("utilisateurId");
    	
    	if (idUserStr != null) {
    		
    	    try {
    	    	
    	    	Utilisateur utilisateur = new Utilisateur();
    	    	
    	        int idUser = Integer.parseInt(idUserStr);
    	        utilisateur.setIdentifiant(idUser);
    	        
    	    	String adminSwitchValue = request.getParameter("adminSwitch");
    	    	String activateSwitchValue = request.getParameter("activateSwitch");
    	    	String modifyOneUserAction = request.getParameter("modifyOneUser");
    	    	String mdpOublieAction = request.getParameter("mdpOublie");
    	    	String deleteOneUserAction = request.getParameter("deleteOneUser");
    	    	
    	        if (adminSwitchValue != null) {
    	        	 if ("true".equals(adminSwitchValue)) {
    	        		 // La case "adminSwitch" est cochée (ON)
    	        		 utilisateurManager.activateAdmin(utilisateur);
    	        	 } else {
    	        		 // La case "adminSwitch" est décochée (OFF)
    	        		 utilisateurManager.desactivateAdmin(utilisateur);
    	        	 }
    	        	 
    	        }
    	        
    	        if (activateSwitchValue != null) {
    	        	if ("true".equals(activateSwitchValue)) {
    	       		 	// La case "activateSwitch" est cochée (ON)
    	        		articleManager.activateAllArticlesByUser(utilisateur);
    	        		utilisateurManager.activateUser(utilisateur);
    	        	} else {
    	       		 	// La case "activateSwitch" est décochée (OFF)
    	        		articleManager.desactivateAllArticlesByUser(utilisateur);
    	        		utilisateurManager.desactivateUser(utilisateur);
    	       	 	}
    	       	 
    	        }	
    	        
    	        if (modifyOneUserAction != null) {
    	        	
    	    		utilisateur.setPseudo(request.getParameter("pseudo"));
    	            utilisateur.setNom(request.getParameter("nom"));
    	            utilisateur.setPrenom(request.getParameter("prenom"));
    	            utilisateur.setEmail(request.getParameter("email"));
    	            utilisateur.setTelephone(request.getParameter("telephone"));
    	            utilisateur.setRue(request.getParameter("rue"));
    	            utilisateur.setCodePostal(Integer.valueOf(request.getParameter("codePostal")));
    	            utilisateur.setVille(request.getParameter("ville"));
    	            
    	            utilisateurManager.updateOne(utilisateur, utilisateur.getIdentifiant());
    	            
    	        }
    	        
    	        if (mdpOublieAction != null) {
    	        	
    	            utilisateur.setEmail(request.getParameter("email"));
    	            
    	        	String resetPasswordLink = "http://localhost:8080" + request.getContextPath() + "/reinitialisation_mdp?numid=" + utilisateur.getIdentifiant(); // Lien de réinitialisation du Mot de Passe

    	        	boolean sendMailMdpOubliOk = false;
    	        	sendMailMdpOubliOk = emailmanager.envoiMailMdpOubli(utilisateur, resetPasswordLink);
    	        	request.setAttribute("sendMailMdpOubliOk", sendMailMdpOubliOk);
    	        	
    	        }
    	        	
    	        if (deleteOneUserAction != null) {
    	        	articleManager.deleteAllArticlesByUser(utilisateur);
    	        	utilisateurManager.deleteUser(utilisateur);
    	        }
    	        
    	    } catch (NumberFormatException e) {
    	        // Gérer l'exception si la conversion échoue (par exemple, enregistrez l'erreur dans les journaux)
    	    } catch (Exception e) {	
    	    	// Gérer l'exception si une exception est levée
    	    }
    	    
    	}
        
		request.setAttribute("utilisateurs", utilisateurManager.getAllUsers());
		
		HttpSession session = request.getSession();
		
	    this.getServletContext().getRequestDispatcher("/WEB-INF/espace_admin/gestion_utilisateurs.jsp").forward(request, response);
		
	}

}
