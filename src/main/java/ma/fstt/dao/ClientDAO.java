package ma.fstt.dao;

import ma.fstt.ConnectionJDBC;
import ma.fstt.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

  private static final String SELECT_ALL_CLIENTS = "SELECT * FROM clients";
  private static final String INSERT_CLIENT = "INSERT INTO clients (nom, prenom, adresse, email, telephone) VALUES (?, ?, ?, ?, ?)";
  private static final String UPDATE_CLIENT = "UPDATE clients SET nom=?, prenom=?, adresse=?, email=?, telephone=? WHERE id=?";
  private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id=?";
  private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM clients WHERE id=?";

  private Connection connection;

  public ClientDAO() throws ClassNotFoundException, SQLException {
    this.connection = ConnectionJDBC.getInstance();
  }

  public List<Client> getAllClients() throws SQLException {
    List<Client> clients = new ArrayList<>();

    PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL_CLIENTS);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String nom = resultSet.getString("nom");
      String prenom = resultSet.getString("prenom");
      String adresse = resultSet.getString("adresse");
      String email = resultSet.getString("email");
      String telephone = resultSet.getString("telephone");

      Client client = new Client(id, nom, prenom, adresse, email, telephone);
      clients.add(client);
    }
    return clients;
  }

  public void addClient(Client client) throws SQLException {

    PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_CLIENT);
    preparedStatement.setString(1, client.getNom());
    preparedStatement.setString(2, client.getPrenom());
    preparedStatement.setString(3, client.getAdresse());
    preparedStatement.setString(4, client.getEmail());
    preparedStatement.setString(5, client.getTelephone());

    preparedStatement.executeUpdate();
  }

  public void updateClient(Client client) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
    preparedStatement.setString(1, client.getNom());
    preparedStatement.setString(2, client.getPrenom());
    preparedStatement.setString(3, client.getAdresse());
    preparedStatement.setString(4, client.getEmail());
    preparedStatement.setString(5, client.getTelephone());
    preparedStatement.setInt(6, client.getId());

    preparedStatement.executeUpdate();
  }

  public void deleteClient(int clientId) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
    preparedStatement.setInt(1, clientId);

    preparedStatement.executeUpdate();
  }

  public Client getClientById(int clientId) throws SQLException {
    Client client = null;

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID);
    preparedStatement.setInt(1, clientId);

    ResultSet resultSet = preparedStatement.executeQuery();

    if (resultSet.next()) {
      String nom = resultSet.getString("nom");
      String prenom = resultSet.getString("prenom");
      String adresse = resultSet.getString("adresse");
      String email = resultSet.getString("email");
      String telephone = resultSet.getString("telephone");

      client = new Client(clientId, nom, prenom, adresse, email, telephone);
    }

    return client;
  }
}
