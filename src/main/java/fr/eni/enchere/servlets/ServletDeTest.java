package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletDeTest
 */
@WebServlet("/ServletDeTest")
public class ServletDeTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private UtilisateurDAO utilisateurDao = DAOFactory.getUtilisateurDAO();
	private UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
	private ArticleManager articleManager = ArticleManager.getInstance();
       
    public ServletDeTest() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
        super.init();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/tests/bonjour.jsp");
		request.setAttribute("utilisateurs", utilisateurManager.getAllUsers());
		request.setAttribute("articles", articleManager.getAllArticles());
//		HttpSession session = request.getSession();
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/tests/bonjour.jsp").forward(request, response);

	}

}