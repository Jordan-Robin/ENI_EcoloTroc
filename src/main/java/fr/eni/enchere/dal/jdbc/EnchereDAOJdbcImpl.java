/**
 * 
 */
package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatEnchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.EnchereDAO;

/**
 * @author user
 *
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SELECT_ALL_USER = "SELECT * from Enchere;";
	private static final String SELECT_BY_ARTICLE = "SELECT * from Enchere WHERE noArticle=? ORDER BY montantEnchere DESC";
	private static final String INSERT_ENCHERE = "INSERT INTO Enchere (noUtilisateur, noArticle, dateEnchere, montantEnchere, activate, etatEnchere) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_ETATENCHERE = "UPDATE Enchere SET etatEnchere=? WHERE noEnchere=?";
	private static final String DELETE_ONE_ENCHERE = "DELETE FROM Enchere WHERE noEnchere=?";
	private static final String DELETE_ALL_ENCHERES_BY_ARTICLE = "DELETE FROM Enchere WHERE noArticle=?";
	private static final String UPDATE_ACTIVATE_ALL_ENCHERES_BY_ARTICLE = "UPDATE Enchere SET activate=? WHERE noArticle=?";
	
	@Override
	public List<Enchere> selectAllEnchere() {
		
		List<Enchere> encheres = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL_USER);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				UtilisateurDAOJdbcImpl daoUtilisateur = new UtilisateurDAOJdbcImpl();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdentifiant(rs.getInt("noUtilisateur"));
				utilisateur.setPseudo(daoUtilisateur.selectById(utilisateur.getIdentifiant()).getPseudo());
				
				Enchere enchere = new Enchere();
				
				enchere.setNoEnchere(rs.getInt("noEnchere"));
				enchere.setUtilisateurAcheteur(utilisateur);
				enchere.setDateEnchere(rs.getDate("dateEnchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
				enchere.setActivate(rs.getBoolean("activate"));
				enchere.setEtatEnchere(EtatEnchere.valueOf(rs.getString("etatEnchere")));
				enchere.setNoArticle(rs.getInt("noArticle"));
				
				encheres.add(enchere);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encheres;
		
	}
	@Override
	public List<Enchere> afficherListeEnchere(int noArticle) {
		List<Enchere> encheres = new ArrayList<Enchere>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				UtilisateurDAOJdbcImpl daoUtilisateur = new UtilisateurDAOJdbcImpl();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdentifiant(rs.getInt("noUtilisateur"));
				utilisateur.setPseudo(daoUtilisateur.selectById(utilisateur.getIdentifiant()).getPseudo());
//				
//				ArticleDAOJdbcImpl daoArticle = new ArticleDAOJdbcImpl();
//				ArticleVendu article = new ArticleVendu();
//				article.setNoArticle(rs.getInt("noArticle"));
//				article = daoArticle.selectById(article.getNoArticle());
				
//				Enchere enchere = new Enchere(
//						rs.getInt("noEnchere"),
//						utilisateur,
//						rs.getDate("dateEnchere").toLocalDate(),
//						rs.getInt("montantEnchere"),
//						article,
//						rs.getBoolean("activate"));

				Enchere enchere = new Enchere();
				enchere.setNoEnchere(rs.getInt("noEnchere"));
				enchere.setNoUtilisateur(utilisateur.getIdentifiant());
				enchere.setPseudoUtilisateur(utilisateur.getPseudo());
				enchere.setDateEnchere(rs.getDate("dateEnchere").toLocalDate());
				enchere.setMontantEnchere(rs.getInt("montantEnchere"));
				enchere.setNoArticle(rs.getInt("noArticle"));
				enchere.setActivate(rs.getBoolean("activate"));
				enchere.setEtatEnchere(EtatEnchere.valueOf(rs.getString("etatEnchere")));
				
				encheres.add(enchere);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encheres;
	}


	@Override
	public void encherir(Enchere enchere) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, enchere.getUtilisateurAcheteur().getIdentifiant());
			pstmt.setInt(2, enchere.getArticleVendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.setBoolean(5, enchere.getActivate());
			pstmt.setString(6, EtatEnchere.MEILLEUR.toString());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				enchere.setNoEnchere(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void updateEnchere(int noEnchere, EtatEnchere etatEnchere) {
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_ETATENCHERE)) {
			stmt.setString( 1, etatEnchere.toString());
			stmt.setInt(2, noEnchere);
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteOneEnchere(Enchere enchere) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_ONE_ENCHERE);

			stmt.setInt(1, enchere.getNoEnchere());
			
			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void deleteAllEnchersByArticle(ArticleVendu article) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_ALL_ENCHERES_BY_ARTICLE);

			stmt.setInt(1, article.getNoArticle());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void updateActivateAllEnchersByArticle(Enchere enchere, ArticleVendu article) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ACTIVATE_ALL_ENCHERES_BY_ARTICLE);

			stmt.setBoolean(1, enchere.getActivate());
			stmt.setInt(2, article.getNoArticle());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
