package fr.eni.enchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.EtatVente;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;


public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String CREER_ARTICLE = "INSERT INTO ArticleVendu (nom, description, "
			+ "etatVente, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, noCategorie, noUtilisateurVendeur,"
			+ " activate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERER_NUMERO_RETRAIT = "UPDATE ArticleVendu SET NoRetrait =? "
			+ "											WHERE noArticle =?";
	private static final String CREER_POINT_RETRAIT = "INSERT INTO Retrait (rue, ville, codePostal, noArticle) VALUES"
														+ " (?, ?, ?, ?)";
	private static final String SELECT_ALL_ARTICLES = "SELECT * from ArticleVendu;";
	private static final String SELECT_ONE_ARTICLE = "SELECT * from ArticleVendu WHERE noArticle=?;";
	private static final String SELECT_ONE_RETRAIT = "SELECT * FROM Retrait WHERE noRetrait =?";
	private static final String UPDATE_ARTICLE = "UPDATE ArticleVendu SET nom=?, description=?, dateDebutEncheres=?,"
			+ "dateFinEncheres=?, miseAPrix=?, prixVente=?, noCategorie=? WHERE noArticle=?";
	private static final String UPDATE_RETRAIT = "UPDATE Retrait SET rue=?, codePostal=?, ville=? WHERE noRetrait=?";
	private static final String DELETE_ARTICLE = "DELETE FROM ArticleVendu WHERE noArticle=?";
	private static final String DELETE_RETRAIT = "DELETE FROM Retrait WHERE noRetrait=?";
	private static final String UPDATE_ETATVENTE = "UPDATE ArticleVendu SET etatVente=? WHERE noArticle=?";
	private static final String UPDATE_ACTIVATE_ONE_USER = "UPDATE ArticleVendu SET activate=? WHERE noArticle=?;";
	private static final String UPDATE_ENCHERE_ARTICLE = "UPDATE ArticleVendu SET prixVente=?, noUtilisateurAcheteur=? WHERE noArticle=?";
	private static final String SELECT_ALL_ARTICLES_NON_TERMINEE = "SELECT noArticle FROM ArticleVendu WHERE noUtilisateurVendeur=? AND etatVente<>'TERMINEE'";
	private static final String DELETE_ALL_ARTICLES_NON_TERMINEE = "DELETE FROM ArticleVendu WHERE noUtilisateurVendeur=? AND etatVente<>'TERMINEE'";
	private static final String UPDATE_ACIVATE_ALL_ARTICLES_NON_TERMINEE = "UPDATE ArticleVendu set activate=? WHERE noUtilisateurVendeur=? AND etatVente<>'TERMINEE'";
	
	
	@Override
	public void creerArticle( ArticleVendu article ) {
		
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement( CREER_ARTICLE, Statement.RETURN_GENERATED_KEYS ) ) {
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setString(3, article.getEtatVente().toString());
			stmt.setDate(4, Date.valueOf( article.getDateDebutEncheres() ));
			stmt.setDate(5, Date.valueOf( article.getDateFinEncheres() ));
			stmt.setInt(6, article.getMiseAPrix());
			stmt.setInt(7, article.getPrixVente());
			stmt.setInt(8, article.getCategorie().getNoCategorie());
			stmt.setInt(9, article.getVendeur().getIdentifiant());
			stmt.setInt(10, 1);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		/*Une fois que le numéro d'article a été récupéré, insertion du point de retrait dans la bdd avec le noArticle,
		puis récupération du numéro de Retrait pour l'insérer dans l'attribut Retrait de l'Article*/
		int numeroRetrait = creerPointRetrait( article );
		
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement( INSERER_NUMERO_RETRAIT )) {
			stmt.setInt( 1, numeroRetrait);
			stmt.setInt( 2, article.getNoArticle() );
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
	}	

	
	@Override
	public int creerPointRetrait( ArticleVendu article ) {
		
		Integer numeroRetrait = null;
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(CREER_POINT_RETRAIT, Statement.RETURN_GENERATED_KEYS) ) {
			stmt.setString(1, article.getRetrait().getRue());
			stmt.setString(2, article.getRetrait().getVille());
			stmt.setInt(3, article.getRetrait().getCodePostal());
			stmt.setInt(4, article.getNoArticle());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				numeroRetrait = rs.getInt(1);
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return numeroRetrait;
	}
	
	
	@Override
	public Retrait getRetraitById( int retraitId ) {
		
		Retrait retrait = null;
		
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement( SELECT_ONE_RETRAIT ) ) {
			stmt.setInt(1, retraitId);
			ResultSet rs = stmt.executeQuery();
			if ( rs.next() ) {
				retrait = new Retrait( retraitId, rs.getString("rue"), rs.getString("ville"), rs.getInt("codePostal") );
			}
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return retrait;
	}
	

	@Override
	public List<ArticleVendu> getAllArticles() {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArticleVendu article = new ArticleVendu();
				
				// VERIFIER SI ON UTILISE LES JDBCIMPL OU LES MANAGERS
				
				CategorieDAOJdbcImpl daoCategorieDAOJdbcImpl = new CategorieDAOJdbcImpl();
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("noCategorie"));
				categorie = daoCategorieDAOJdbcImpl.selectOneCategoryById(categorie.getNoCategorie());
				
				UtilisateurDAOJdbcImpl daoUtilisateur = new UtilisateurDAOJdbcImpl();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setIdentifiant(rs.getInt("noUtilisateurVendeur"));
				vendeur = daoUtilisateur.selectById(vendeur.getIdentifiant());
				
				Utilisateur acheteur = new Utilisateur();
				acheteur.setIdentifiant(rs.getInt("noUtilisateurAcheteur"));
				acheteur = daoUtilisateur.selectById(acheteur.getIdentifiant());
				
				EnchereDAOJdbcImpl daoEnchere = new EnchereDAOJdbcImpl();
				List<Enchere> encheres = new ArrayList<Enchere>();
				encheres = daoEnchere.afficherListeEnchere(rs.getInt("noArticle"));
				
				Retrait retrait = getRetraitById( rs.getInt("noRetrait") );

				article.setNoArticle(rs.getInt("noArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescription(rs.getString("description"));
				article.setEtatVente(EtatVente.valueOf(rs.getString("etatVente")));
				article.setDateDebutEncheres(rs.getDate("dateDebutEncheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("dateFinEncheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("miseAPrix"));
				article.setPrixVente(rs.getInt("prixVente"));
				article.setCategorie(categorie);
				article.setVendeur(vendeur);
				article.setAcheteur(acheteur);
				article.setActivate(rs.getBoolean("activate"));
				article.setEncheres(encheres);
				article.setRetrait(retrait);
				
				//Mise à jour de l'attribut EtatVente dans la bdd en fonction de la date du jour
				if( article.getDateDebutEncheres().equals( LocalDate.now() ) ) {
					article.setEtatVente( EtatVente.EN_COURS );
					PreparedStatement stmtUpdate = cnx.prepareStatement(UPDATE_ETATVENTE);
					stmtUpdate.setString( 1, "EN_COURS" );
					stmtUpdate.setInt( 2, article.getNoArticle() );
					stmtUpdate.executeUpdate();
				}
				if( article.getDateFinEncheres().equals( LocalDate.now() ) ) {
					article.setEtatVente( EtatVente.TERMINEE );
					PreparedStatement stmtUpdate = cnx.prepareStatement(UPDATE_ETATVENTE);
					stmtUpdate.setString( 1, "TERMINEE" );
					stmtUpdate.setInt( 2, article.getNoArticle() );
					stmtUpdate.executeUpdate();
				}

				articles.add(article);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return articles;
	}


	@Override
	public ArticleVendu selectById(int noArticle) {
		ArticleVendu article = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_ONE_ARTICLE);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				article = new ArticleVendu();
				
				// VERIFIER SI ON UTILISE LES JDBCIMPL OU LES MANAGERS
				
				CategorieDAOJdbcImpl daoCategorieDAOJdbcImpl = new CategorieDAOJdbcImpl();
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("noCategorie"));
				categorie = daoCategorieDAOJdbcImpl.selectOneCategoryById(categorie.getNoCategorie());
				
				UtilisateurDAOJdbcImpl daoUtilisateur = new UtilisateurDAOJdbcImpl();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdentifiant(rs.getInt("noUtilisateurVendeur"));
				utilisateur = daoUtilisateur.selectById(utilisateur.getIdentifiant());
				
				EnchereDAOJdbcImpl daoEnchere = new EnchereDAOJdbcImpl();
				List<Enchere> encheres = new ArrayList<Enchere>();
				encheres = daoEnchere.afficherListeEnchere(noArticle);
				
				Retrait retrait = getRetraitById( rs.getInt("noRetrait") );

				article.setNoArticle(rs.getInt("noArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescription(rs.getString("description"));
				article.setEtatVente(EtatVente.valueOf(rs.getString("etatVente")));
				article.setDateDebutEncheres(rs.getDate("dateDebutEncheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("dateFinEncheres").toLocalDate());
				article.setMiseAPrix(rs.getInt("miseAPrix"));
				article.setPrixVente(rs.getInt("prixVente"));
				article.setCategorie(categorie);
				article.setVendeur(utilisateur);
				article.setActivate(rs.getBoolean("activate"));
				article.setEncheres(encheres);
				article.setRetrait(retrait);
				
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return article;
	}	
	
	
	@Override
	public void updateArticle( ArticleVendu article ) {
		
		//modifications dans la table Article
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_ARTICLE) ) {
			stmt.setString( 1, article.getNomArticle() );
			stmt.setString( 2, article.getDescription() );
			stmt.setDate( 3, Date.valueOf( article.getDateDebutEncheres() ) );
			stmt.setDate( 4, Date.valueOf( article.getDateFinEncheres() ) );
			stmt.setInt( 5, article.getMiseAPrix() );
			stmt.setInt( 6, article.getPrixVente() );
			stmt.setInt( 7, article.getCategorie().getNoCategorie() );
			stmt.setInt( 8, article.getNoArticle() );
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		//modifications dans la table Retrait
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_RETRAIT) ) {
			stmt.setString( 1, article.getRetrait().getRue() );
			stmt.setInt( 2, article.getRetrait().getCodePostal() );
			stmt.setString( 3, article.getRetrait().getVille() );
			stmt.setInt( 4, article.getRetrait().getNoRetrait() );
			stmt.executeUpdate();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteArticle( ArticleVendu article ) {
		
		//suppression de l'article
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(DELETE_ARTICLE) ) {
			stmt.setInt( 1, article.getNoArticle() );
			stmt.executeUpdate();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
		
		//suppression du retrait associé
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(DELETE_RETRAIT) ) {
			stmt.setInt( 1, article.getRetrait().getNoRetrait() );
			stmt.executeUpdate();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateActivateArticle(ArticleVendu article) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(UPDATE_ACTIVATE_ONE_USER);

			stmt.setBoolean(1, article.getActivate());
			stmt.setInt(2, article.getNoArticle());

			// Update
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateEnchereArticle(ArticleVendu article, int noUtilisateurAcheteur ,int montant) {
		try( Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_ENCHERE_ARTICLE)) {
			stmt.setInt( 1, montant );
			stmt.setInt(2, noUtilisateurAcheteur);
			stmt.setInt( 3, article.getNoArticle() );
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteAllArticlesByUser(Utilisateur utilisateur) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
	        // Sélectionnez les identifiants des articles à supprimer
	        PreparedStatement selectStmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_NON_TERMINEE);
	        selectStmt.setInt(1, utilisateur.getIdentifiant());
	        
	        EnchereManager enchereManager = EnchereManager.getInstance();
	        
	        ResultSet resultSet = selectStmt.executeQuery();
	        
	        // Suppressions des enchères correspondant aux articles à supprimer pour les ventes non terminées
	        while (resultSet.next()) {
	        	ArticleVendu article = new ArticleVendu();
	        	article.setNoArticle(resultSet.getInt("noArticle"));
	        	enchereManager.deleteAllEnchersByArticle(article);
	        }
				
	        // Suppression des articles
	        PreparedStatement deleteStmt = cnx.prepareStatement(DELETE_ALL_ARTICLES_NON_TERMINEE);
	        deleteStmt.setInt(1, utilisateur.getIdentifiant());
	        
	        // Exécutez la requête DELETE
	        deleteStmt.executeUpdate();
				
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateActivateAllArticlesByUser(Utilisateur utilisateur, ArticleVendu article) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
	        // Sélectionnez les identifiants des articles à supprimer
	        PreparedStatement selectStmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_NON_TERMINEE);
	        selectStmt.setInt(1, utilisateur.getIdentifiant());
	        
	        EnchereManager enchereManager = EnchereManager.getInstance();
	        
	        ResultSet resultSet = selectStmt.executeQuery();
	        
	        // Désactivation des enchères correspondant aux articles à désactiver pour les ventes non terminées
	        while (resultSet.next()) {
	        	ArticleVendu article2 = new ArticleVendu();
	        	article2.setNoArticle(resultSet.getInt("noArticle"));
	        	if (article.getActivate() == true) {
	        		enchereManager.activateAllEncheresByArticle(article2);
	        	} else {
	        		enchereManager.desactivateAllEncheresByArticle(article2);
	        	}
	        }
				
	        // Désactivation des articles
	        PreparedStatement updateStmt = cnx.prepareStatement(UPDATE_ACIVATE_ALL_ARTICLES_NON_TERMINEE);
	        updateStmt.setBoolean(1, article.getActivate());
	        updateStmt.setInt(2, utilisateur.getIdentifiant());
	        
	        // Exécutez la requête UPDATE
	        updateStmt.executeUpdate();
				
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}

}
