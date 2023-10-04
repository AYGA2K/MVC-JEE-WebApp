package ma.fstt.dao;

import ma.fstt.ConnectionJDBC;
import ma.fstt.entity.Commande;
import ma.fstt.entity.Client;
import ma.fstt.entity.LignedeCommande;
import ma.fstt.entity.Produit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

  private static final String SELECT_LIGNES_FOR_COMMANDE = "SELECT * FROM lignesdecommande WHERE commande_id=?";
  private static final String SELECT_ALL_COMMANDES = "SELECT * FROM commandes";
  private static final String UPDATE_COMMANDE = "UPDATE commandes SET date=?, client_id=? WHERE id=?";
  private static final String DELETE_COMMANDE = "DELETE FROM commandes WHERE id=?";
  private static final String SELECT_COMMANDE_BY_ID = "SELECT * FROM commandes WHERE id=?";

  private Connection connection;
  private ClientDAO clientDAO;

  public CommandeDAO() throws ClassNotFoundException, SQLException {
    this.connection = ConnectionJDBC.getInstance();
    this.clientDAO = new ClientDAO();
  }

  public List<Commande> getAllCommandes() throws SQLException {
    List<Commande> commandes = new ArrayList<>();

    PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_COMMANDES);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      Date date = resultSet.getDate("date");
      int clientId = resultSet.getInt("client_id");

      Client client = clientDAO.getClientById(clientId); // Retrieve client using ClientDAO
      List<LignedeCommande> lignes = this.getLignesForCommande(id);

      Commande commande = new Commande(id, date, client);
      commande.setLignesdeCommande(lignes);

      commandes.add(commande);
    }
    return commandes;
  }

  public Commande addCommande(Commande commande) throws SQLException {
    Statement statement = this.connection.createStatement();
    statement.executeUpdate("INSERT INTO commandes (date, client_id) VALUES ('" + commande.getDate() + "', "
        + commande.getClient().getId() + ");", Statement.RETURN_GENERATED_KEYS);

    ResultSet resultSet = statement.getGeneratedKeys();

    if (resultSet.next()) {
      int generatedId = resultSet.getInt(1);
      // Use the generated ID as needed
      commande.setId(generatedId); // Assuming you have a setId method in your Commande class
    }
    return commande;
  }

  public void updateCommande(Commande commande) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMANDE);
    preparedStatement.setDate(1, commande.getDate());
    preparedStatement.setInt(2, commande.getClient().getId());
    preparedStatement.setInt(3, commande.getId());

    preparedStatement.executeUpdate();
  }

  public void deleteCommande(int commandeId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMANDE);
    preparedStatement.setInt(1, commandeId);

    preparedStatement.executeUpdate();
  }

  public Commande getCommandeById(int commandeId) throws SQLException {
    Commande commande = null;

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMANDE_BY_ID);
    preparedStatement.setInt(1, commandeId);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      Date date = resultSet.getDate("date");
      int clientId = resultSet.getInt("client_id");

      Client client = clientDAO.getClientById(clientId); // Retrieve client using ClientDAO
      List<LignedeCommande> lignes = this.getLignesForCommande(commandeId);

      commande = new Commande(commandeId, date, client);
      commande.setLignesdeCommande(lignes);
    }

    return commande;
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
      ProduitDAO produitDAO;
      try {
        produitDAO = new ProduitDAO();
        Produit produit = produitDAO.getProduitById(produitId);

        LignedeCommande ligne = new LignedeCommande(id, quantite, produit, null);

        lignes.add(ligne);

      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return lignes;
  }
}
