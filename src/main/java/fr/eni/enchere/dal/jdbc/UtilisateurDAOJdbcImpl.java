/**
 * 
 */
package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

/**
 * @author user
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_ONE_USER = "INSERT INTO Utilisateur (pseudo, nom, prenom, email,"
			+ " telephone, rue, codePostal, ville, motDePasse, administrateur)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ALL_USER = "SELECT * from Utilisateur;";
	private static final String SELECT_ONE_USER = "SELECT * from Utilisateur WHERE pseudo=? AND motDePasse=?;";
	private static final String UPDATE_ADMIN_ONE_USER = "UPDATE Utilisateur SET administrateur=? WHERE noUtilisateur=?;";
	private static final String UPDATE_ACTIVATE_ONE_USER = "UPDATE Utilisateur SET activate=? WHERE noUtilisateur=?;";
	private static final String DELETE_ONE_USER = "DELETE FROM Utilisateur WHERE noUtilisateur=?;";
	private static final String UPDATE_ONE = "UPDATE Utilisateur SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, codePostal = ?, ville = ? WHERE noUtilisateur = ?;";
	private static final String SELECT_BY_ID = "SELECT * from Utilisateur WHERE noUtilisateur=?;";
	private static final String UPDATE_MDP = "UPDATE Utilisateur SET motDePasse = ? WHERE noUtilisateur = ?;";
	private static final String UPDATE_CREDIT = "UPDATE Utilisateur SET credit = ? WHERE noUtilisateur = ?;";
	private static final String SELECT_BY_PSEUDO = "SELECT * from Utilisateur WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL = "SELECT * from Utilisateur WHERE pseudo=?";

	@Override
	public void insertOneUser(Utilisateur utilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ONE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setInt(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setBoolean(10, utilisateur.getAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				utilisateur.setIdentifiant(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Utilisateur> getAllUsers() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL_USER);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Utilisateur utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"),
						rs.getString("rue"), rs.getString("ville"), rs.getInt("codePostal"), rs.getInt("credit"),
						rs.getString("motDePasse"), rs.getBoolean("administrateur"), rs.getBoolean("activate"));

				utilisateurs.add(utilisateur);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return utilisateurs;

	}

	@Override
	public Utilisateur connectionUser(String pseudo, String motDePasse) {
		Utilisateur user = null;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(SELECT_ONE_USER)) {

			stmt.setString(1, pseudo);
			stmt.setString(2, motDePasse);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				user = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("ville"), rs.getInt("codePostal"), rs.getInt("credit"), rs.getString("motDePasse"),
						rs.getBoolean("administrateur"), rs.getBoolean("activate"));

			} else {
				user = null;
//				throw new RuntimeException( "Pseudo ou mot de passe incorrect" );
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;

	}

	@Override
	public void update(Utilisateur utilisateur, int noUtilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ONE);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setInt(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setInt(9, noUtilisateur);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur selectById(int noIdentifiant) {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, noIdentifiant);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("ville"), rs.getInt("codePostal"), rs.getInt("credit"), rs.getString("motDePasse"),
						rs.getBoolean("administrateur"), rs.getBoolean("activate"));
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public void updateMdp(Utilisateur utilisateur, int noUtilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_MDP);
			pstmt.setString(1, utilisateur.getMotDePasse());
			pstmt.setInt(2, noUtilisateur);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateAdminUser(Utilisateur utilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ADMIN_ONE_USER);

			stmt.setBoolean(1, utilisateur.getAdministrateur());
			stmt.setInt(2, utilisateur.getIdentifiant());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateActivateUser(Utilisateur utilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ACTIVATE_ONE_USER);

			stmt.setBoolean(1, utilisateur.getActivate());
			stmt.setInt(2, utilisateur.getIdentifiant());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOneUser(Utilisateur utilisateur) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(DELETE_ONE_USER);

			stmt.setInt(1, utilisateur.getIdentifiant());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("ville"), rs.getInt("codePostal"), rs.getInt("credit"), rs.getString("motDePasse"),
						rs.getBoolean("administrateur"), rs.getBoolean("activate"));
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public Utilisateur selectByEmail(String email) {

		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("ville"), rs.getInt("codePostal"), rs.getInt("credit"), rs.getString("motDePasse"),
						rs.getBoolean("administrateur"), rs.getBoolean("activate"));
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	@Override
	public void updateCredit(Utilisateur utilisateur, int montant) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT);
			int montantAAjouter = montant + Integer.valueOf(utilisateur.getCredit());
			pstmt.setInt(1, montantAAjouter);
			pstmt.setInt(2, utilisateur.getIdentifiant());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
