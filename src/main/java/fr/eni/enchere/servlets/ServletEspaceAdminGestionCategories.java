package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.Categorie;

/**
 * Servlet implementation class ServletEspaceAdminGestionCategories
 */
@WebServlet("/espace_admin/gestion_categories")
public class ServletEspaceAdminGestionCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEspaceAdminGestionCategories() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/espace_admin/gestion_categories.jsp");
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		
		request.setAttribute("categories", categorieManager.getAllCategories());
		
		HttpSession session = request.getSession();
		
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        // Définir l'encodage des caractères UTF-8
        request.setCharacterEncoding("UTF-8");
		
		CategorieManager categorieManager = CategorieManager.getInstance();
		
		
    	Categorie categorie = new Categorie();
    		
    	String idCategorieStr = request.getParameter("noCategorie");
    	if (idCategorieStr != null) {
    	    try {
    	        int idUser = Integer.parseInt(idCategorieStr);
    	        categorie.setNoCategorie(idUser);
    	    } catch (NumberFormatException e) {
    	        // Gérer l'exception si la conversion échoue (par exemple, enregistrez l'erreur dans les journaux)
    	    }
    	}
    	
    	categorie.setLibelle(request.getParameter("libelle"));
    	
    	
    	String createAction = request.getParameter("createOneCategory");
        String modifyAction = request.getParameter("modifyOneCategory");
        String deleteAction = request.getParameter("deleteOneCategory");

        if (createAction != null) {
        	categorieManager.createOneCategory(categorie);
        } else if (modifyAction != null) {
        	categorieManager.modifyOneCategory(categorie);
        } else if (deleteAction != null) {
        	categorieManager.deleteOneCategory(categorie);
        }
    	
    	
    	request.setAttribute("categories", categorieManager.getAllCategories());
    	
    	HttpSession session = request.getSession();
	    this.getServletContext().getRequestDispatcher("/WEB-INF/espace_admin/gestion_categories.jsp").forward(request, response);
		
	}

}
