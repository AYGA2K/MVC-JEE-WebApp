package ma.fstt.dao;

import ma.fstt.ConnectionJDBC;
import ma.fstt.entity.Commande;
import ma.fstt.entity.LignedeCommande;
import ma.fstt.entity.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LignedeCommandeDAO {

  private static final String SELECT_LIGNES_FOR_COMMANDE = "SELECT * FROM lignesdecommande WHERE commande_id=?";
  private static final String INSERT_LIGNE = "INSERT INTO lignesdecommande (quantite, produit_id, commande_id) VALUES (?, ?, ?)";
  private static final String UPDATE_LIGNE = "UPDATE lignesdecommande SET quantite=?, produit_id=?, commande_id=? WHERE id=?";
  private static final String DELETE_LIGNE = "DELETE FROM lignesdecommande WHERE id=?";
  private static final String SELECT_LIGNE_BY_ID = "SELECT * FROM lignesdecommande WHERE id=?";

  private Connection connection;
  private ProduitDAO produitDAO;
  private CommandeDAO commandeDAO;

  public LignedeCommandeDAO() throws ClassNotFoundException, SQLException {
    this.connection = ConnectionJDBC.getInstance();
    this.produitDAO = new ProduitDAO();
    this.commandeDAO = new CommandeDAO();
  }

  public List<LignedeCommande> getLignesForCommande(int commandeId) throws SQLException {
    List<LignedeCommande> lignes = new ArrayList<>();

    PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_LIGNES_FOR_COMMANDE);
    preparedStatement.setInt(1, commandeId);

    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int quantite = resultSet.getInt("quantite");
      int produitId = resultSet.getInt("produit_id");

      Produit produit = produitDAO.getProduitById(produitId);

      LignedeCommande ligne = new LignedeCommande(id, quantite, produit, null);

      lignes.add(ligne);
    }

    return lignes;
  }

  public void addLigne(LignedeCommande ligne) throws SQLException {
    PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_LIGNE);
    preparedStatement.setInt(1, ligne.getQuantite());
    preparedStatement.setInt(2, ligne.getProduit().getId());
    preparedStatement.setInt(3, ligne.getCommande().getId());

    preparedStatement.executeUpdate();
  }

  public void updateLigne(LignedeCommande ligne) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LIGNE);
    preparedStatement.setInt(1, ligne.getQuantite());
    preparedStatement.setInt(2, ligne.getProduit().getId());
    preparedStatement.setInt(3, ligne.getCommande().getId());
    preparedStatement.setInt(4, ligne.getId());

    preparedStatement.executeUpdate();
  }

  public void deleteLigne(int ligneId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LIGNE);
    preparedStatement.setInt(1, ligneId);

    preparedStatement.executeUpdate();
  }

  public LignedeCommande getLigneById(int ligneId) throws SQLException {
    LignedeCommande ligne = null;

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIGNE_BY_ID);
    preparedStatement.setInt(1, ligneId);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      int quantite = resultSet.getInt("quantite");
      int produitId = resultSet.getInt("produit_id");
      int commandeId = resultSet.getInt("commande_id");

      Produit produit = produitDAO.getProduitById(produitId);
      Commande commande = commandeDAO.getCommandeById(commandeId);
      ligne = new LignedeCommande(ligneId, quantite, produit, commande);
    }

    return ligne;
  }

}
