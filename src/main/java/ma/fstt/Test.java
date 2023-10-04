package ma.fstt;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entity.Client;
import ma.fstt.entity.Produit;

public class Test {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    ClientDAO clientDAO = new ClientDAO();

    int clientId = 1;
    Client client = clientDAO.getClientById(clientId);
    ProduitDAO produitDAO = new ProduitDAO();
    List<Produit> produits = produitDAO.getAllProduits();
    System.out.println(produits);
    if (client == null) {
      System.out.println("Client with ID " + clientId + " not found.");
    } else {
      System.out.println("Client Information:");
      System.out.println("ID: " + client.getId());
      System.out.println("Nom: " + client.getNom());
      System.out.println("Prenom: " + client.getPrenom());
      System.out.println("Adresse: " + client.getAdresse());
      System.out.println("Email: " + client.getEmail());
      System.out.println("Telephone: " + client.getTelephone());
    }
  }
}
