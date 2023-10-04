package ma.fstt.dao;

import ma.fstt.ConnectionJDBC;
import ma.fstt.entity.Produit;
import ma.fstt.entity.Commande;
import ma.fstt.entity.LignedeCommande;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

  private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produits";
  private static final String INSERT_PRODUIT = "INSERT INTO produits (nom, description, prix) VALUES (?, ?, ?)";
  private static final String UPDATE_PRODUIT = "UPDATE produits SET nom=?, description=?, prix=? WHERE id=?";
  private static final String DELETE_PRODUIT = "DELETE FROM produits WHERE id=?";
  private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produits WHERE id=?";
  private static final String SELECT_LIGNES_FOR_PRODUIT = "SELECT * FROM lignesdecommande WHERE produit_id=?";
  private Connection connection;
  private CommandeDAO commandeDAO;

  public ProduitDAO() throws ClassNotFoundException, SQLException {
    this.connection = ConnectionJDBC.getInstance();
    this.commandeDAO = new CommandeDAO();
  }

  public List<Produit> getAllProduits() throws SQLException {
    List<Produit> produits = new ArrayList<>();

    PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_PRODUITS);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String nom = resultSet.getString("nom");
      String description = resultSet.getString("description");
      double prix = resultSet.getDouble("prix");

      Produit produit = new Produit(id, nom, description, prix);

      produits.add(produit);
    }

    return produits;
  }

  public void addProduit(Produit produit) throws SQLException {
    PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_PRODUIT);
    preparedStatement.setString(1, produit.getNom());
    preparedStatement.setString(2, produit.getDescription());
    preparedStatement.setDouble(3, produit.getPrix());

    preparedStatement.executeUpdate();
  }

  public void updateProduit(Produit produit) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUIT);
    preparedStatement.setString(1, produit.getNom());
    preparedStatement.setString(2, produit.getDescription());
    preparedStatement.setDouble(3, produit.getPrix());
    preparedStatement.setInt(4, produit.getId());

    preparedStatement.executeUpdate();
  }

  public void deleteProduit(int produitId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUIT);
    preparedStatement.setInt(1, produitId);

    preparedStatement.executeUpdate();
  }

  public Produit getProduitById(int produitId) throws SQLException {
    Produit produit = null;

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUIT_BY_ID);
    preparedStatement.setInt(1, produitId);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      String nom = resultSet.getString("nom");
      String description = resultSet.getString("description");
      double prix = resultSet.getDouble("prix");

      produit = new Produit(produitId, nom, description, prix);
    }

    return produit;
  }

  public List<LignedeCommande> getLignesForProduit(int produitId) throws SQLException {
    List<LignedeCommande> lignes = new ArrayList<>();

    PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_LIGNES_FOR_PRODUIT);
    preparedStatement.setInt(1, produitId);

    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      int quantite = resultSet.getInt("quantite");
      int commandeId = resultSet.getInt("commande_id");

      Commande commande = commandeDAO.getCommandeById(commandeId);
      Produit produit = getProduitById(produitId);
      LignedeCommande ligne = new LignedeCommande(id, quantite, produit, commande);

      lignes.add(ligne);
    }

    return lignes;
  }

}
