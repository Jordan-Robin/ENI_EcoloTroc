package fr.eni.enchere.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.dal.jdbc.UtilisateurDAOJdbcImpl;

@WebServlet("/TestUtilisateurs")
public class TestUtilisateurs extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UtilisateurDAO utilisateurDao;

    @Override
    public void init() throws ServletException {
        super.init();
        utilisateurDao = new UtilisateurDAOJdbcImpl(); // ou utilisez la classe d'implémentation que vous avez créée

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/tests/utilisateurs.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("nom"));
        utilisateur.setPrenom(request.getParameter("prenom"));
        utilisateur.setMotDePasse(request.getParameter("mot de passe"));
        
        utilisateurDao.insertOneUser(utilisateur);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/tests/utilisateurs.jsp").forward(request, response);
    }
}
